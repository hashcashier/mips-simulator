package assembly;

import instructions.Instruction;
import instructions.InstructionFactory;
import instructions.PseudoInstruction;
import instructions.PseudoInstructionFactory;
import instructions.UnkownInstructionException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

import registers.RegisterManager;

public class Assembler {
	String[] filteredInstructions, filteredData, assembledInstructions, assembledData, parsedData;
	Integer[] parsedDataType;
	Instruction[] parsedInstructions;
	LabelManager labelManager;
	long programOffset;
	public static final long DM_OFFSET = (1<<29);
	
	public Assembler(String[] instructions, String[] data, int offset) 
			throws DuplicateLabelException, UnkownLabelException, UnkownInstructionException {
		labelManager = new LabelManager();
		
		filteredData = data;
		filteredInstructions = instructions;
		this.programOffset = offset;
		
		filterData();
		filterInstructions();
		
		parseData();
		parseInstructions();
		
		assembleData();
		assembleInstructions();
	}
	
	private void filterData() throws DuplicateLabelException {
		for(int i = 0; i < filteredData.length; i++) {
			int idx = filteredData[i].indexOf(':'), idx2 = filteredData[i].indexOf('"');
			if((idx2 != -1 && idx > idx2) || idx == -1) {
				continue;
			} else {
				String label = filteredData[i].substring(0, idx).trim();
				filteredData[i] = filteredData[i].substring(idx+1).trim();
				if(labelManager.containsLabel(label))
					throw new DuplicateLabelException();
				labelManager.setLabel(label, DM_OFFSET + 4*i, 1);
			}
		}
	}
	
	private void filterInstructions() throws DuplicateLabelException {
		for(int i = 0; i < filteredInstructions.length; i++) {
			int idx = filteredInstructions[i].indexOf(':');
			if(idx != -1) {
				String label = filteredInstructions[i].substring(0, idx).trim();
				filteredInstructions[i] = filteredInstructions[i].substring(idx+1).trim();
				if(labelManager.containsLabel(label))
					throw new DuplicateLabelException();
				labelManager.setLabel(label, 4*i + programOffset, 2);
			}
		}
	}
	
	private static String[] divideIntoWords(String line) {
		int length = (int) Math.ceil(line.length()/4.0);
		String[] result = new String[length];
		for(int i = 0; i < length; i++)
			result[i] = line.substring( 4*i, Math.min(4*i + 4, line.length()) );
		while( result[ result.length - 1 ].length() < 4 )
			result[ result.length - 1 ] += '\0';
		return result;
	}
	
	private void parseData() {
		Hashtable<String, Long> shiftBuffer = new Hashtable<String, Long>();
		String[] dataLabels = labelManager.getAllDataLabels();
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<Integer> dataType = new ArrayList<Integer>();
		
		for(int i = 0; i < filteredData.length; i++) {
			for(String dataLabel : dataLabels)
				if(labelManager.getLabelValue(dataLabel) == i)
					shiftBuffer.put(dataLabel, (long)data.size());

			String dataLine = filteredData[i].trim();
			if (dataLine.startsWith(".asciiz ") || dataLine.startsWith(".ascii ")) {
				int start = dataLine.indexOf("\"") + 1;
				int end = dataLine.lastIndexOf("\"");
				if(dataLine.startsWith(".asciiz "))
					dataLine = dataLine.substring(start, end) + '\0';
				else
					dataLine = dataLine.substring(start, end);
				String[] rawData = Assembler.divideIntoWords(dataLine);
				for(String rawLine : rawData) {
					data.add(rawLine);
					// TODO Enumerate data types
					dataType.add(0);
				}
			} else if (dataLine.startsWith(".word ")) {
				int start = dataLine.indexOf(".word") + 5;
				dataLine = dataLine.substring(start);
				String[] array = dataLine.split(",");
				for (i = 0; i < array.length; i++) {
					data.add(array[i].trim());
					// TODO Enumerate data types
					dataType.add(1);
				}
			} else {
				System.out.println("[Warning] Unkown data ignored: " + dataLine);
			}
		}
		
		for(Entry<String, Long> entry : shiftBuffer.entrySet())
			labelManager.setLabel(entry.getKey(), DM_OFFSET + 4*entry.getValue(), 1);
		
		parsedData = data.toArray(new String[0]);
		parsedDataType = dataType.toArray(new Integer[0]);
	}
	
	private void parseInstructions() throws UnkownLabelException, UnkownInstructionException {
		ArrayList<Instruction> parsedInstructions = new ArrayList<Instruction>();
		for(int i = 0; i < filteredInstructions.length; i++) {
			String instructionLine = filteredInstructions[i];
			int spaceIndex = instructionLine.indexOf(' ');
			if(spaceIndex == -1) {
				try {
					parsedInstructions.add(InstructionFactory.createInstruction(instructionLine.trim(), new String[0], new int[0]));
				} catch (UnkownInstructionException e) {
					PseudoInstruction temp = PseudoInstructionFactory.createInstruction(instructionLine.trim(), new String[0], new int[0]);
					Instruction[] replacement = temp.getReplacement();
					for(Instruction step : replacement)
						parsedInstructions.add(step);
				}
			} else {
				String name = instructionLine.substring(0, spaceIndex).trim();
				String rawParams = instructionLine.substring(spaceIndex).trim();
				int open = rawParams.indexOf("("), close = rawParams.indexOf(")");
				if(open != -1 && close != -1)
					rawParams = rawParams.substring(0, open) + ", " + rawParams.substring(open+1, close);
				
				String[] params = rawParams.split(",");
				int[] types = new int[params.length];
				for(int j = 0; j < params.length; j++) {
					String trimmedParam = params[j].trim();
					if(trimmedParam.matches("\\d+") || trimmedParam.matches("-\\d+")) {
						types[j] = 8;
						params[j] = trimmedParam;
						
					} else if(!labelManager.containsLabel(trimmedParam)) {
						throw new UnkownLabelException();
					} else {
						types[j] = labelManager.getLabelType(trimmedParam);
						long value = labelManager.getLabelValue(trimmedParam);

						if(types[j] == 1) {// Data label
							params[j] = Long.toString(value);
						} else if(types[j] == 2) {// Instruction label: Target,Current
							params[j] = Long.toString(value)
									+ ","
									+ Integer.toString(parsedInstructions
											.size());
						} else if(types[j] == 4) {// Register label
							params[j] = assembleIntegral(Long.toString(value), 5);
						}
					}
				}
				
				try {
					parsedInstructions.add(InstructionFactory.createInstruction(name, params, types));
				} catch (UnkownInstructionException e) {
					PseudoInstruction temp = PseudoInstructionFactory.createInstruction(name, params, types);
					Instruction[] replacement = temp.getReplacement();
					for(Instruction step : replacement)
						parsedInstructions.add(step);
				}
//				parsedInstructions.add(InstructionFactory.createInstruction(name, params, types));
			}
		}
		this.parsedInstructions = parsedInstructions.toArray(new Instruction[0]);
	}
	
	private void assembleData() {
		assembledData = new String[parsedData.length];
		System.out.println("Data assembly:");
		for(int i = 0; i < parsedData.length; i++) {
			String line = "";
			if(parsedDataType[i] == 0) { // ascii
				for(int j = 0; j < parsedData[i].length(); j++)
					line = line + assembleCharacter(parsedData[i].charAt(j), 8);
			} else if(parsedDataType[i] == 1) { // word
				line = assembleIntegral(parsedData[i], 32);
			}
			
			assembledData[i] = line;
			System.out.println(line);
		}
	}
	
	private void assembleInstructions() {
		assembledInstructions = new String[parsedInstructions.length + 4];
		System.out.println("Instruction assembly:");
		for(int i = 0; i < parsedInstructions.length; i++) {
			assembledInstructions[i] = parsedInstructions[i].getBits();
			System.out.println(assembledInstructions[i]);
		}
		int len = parsedInstructions.length;
		for(int i = 0; i < 4; i++) {
			assembledInstructions[len + i] = RegisterManager.zeros32();
			System.out.println(assembledInstructions[len+i]);
		}
	}
	
	public static String assembleIntegral(String integral, int bits) {
		long numeric = Long.parseLong(integral);
		String result = Long.toBinaryString(numeric);
		
		while(result.length() < bits)
			if(numeric < 0)
				result = "1" + result;
			else
				result = "0" + result;
		
		if(result.length() > bits)
			result = result.substring(result.length() - bits);
		return result;
	}
	
	public static String assembleCharacter(char character, int bits) {
		return assembleIntegral(Integer.toString(character), bits);
	}
	
	public String[] getAssembledInstructions() {
		return assembledInstructions;
	}
	
	public String[] getAssembledData() {
		return assembledData;
	}

	public LabelManager getLabelManager() {
		return labelManager;
	}
}
