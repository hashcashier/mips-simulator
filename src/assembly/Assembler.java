package assembly;

import instructions.Instruction;
import instructions.InstructionFactory;
import instructions.UnkownInstructionException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

public class Assembler {
	String[] filteredInstructions, filteredData, assembledInstructions, assembledData, parsedData;
	Instruction[] parsedInstructions;
	LabelManager labelManager;
	
	public Assembler(String[] instructions, String[] data) throws DuplicateLabelException, UnkownLabelException, UnkownInstructionException {
		labelManager = new LabelManager();
		
		filteredData = data;
		filteredInstructions = instructions;
		
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
				labelManager.setLabel(label, i, 1);
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
				labelManager.setLabel(label, i, 2);
			}
		}
	}
	
	private static String[] divideIntoWords(String line) {
		int length = line.length()/4;
		String[] result = new String[length];
		for(int i = 0; i < length; i++)
			result[i] = line.substring(4*i, 4);
		return result;
	}
	
	private void parseData() {
		Hashtable<String, Integer> shiftBuffer = new Hashtable<String, Integer>();
		String[] dataLabels = labelManager.getAllDataLabels();
		ArrayList<String> data = new ArrayList<String>();
		
		for(int i = 0; i < filteredData.length; i++) {
			for(String dataLabel : dataLabels)
				if(labelManager.getLabelValue(dataLabel) == i)
					shiftBuffer.put(dataLabel, data.size());

			String dataLine = filteredData[i].trim();
			if (dataLine.startsWith(".asciiz ") || dataLine.startsWith(".ascii ")) {
				int start = dataLine.indexOf("\"") + 1;
				int end = dataLine.lastIndexOf("\"");
				if(dataLine.startsWith(".asciiz "))
					dataLine = dataLine.substring(start, end) + '\0';
				else
					dataLine = dataLine.substring(start, end);
				String[] rawData = Assembler.divideIntoWords(dataLine);
				for(String rawLine : rawData)
					data.add(rawLine);
			} else if (dataLine.startsWith(".word ")) {
				int start = dataLine.indexOf(".word") + 5;
				dataLine = dataLine.substring(start);
				String[] array = dataLine.split(",");
				for (i = 0; i < array.length; i++)
					data.add(array[i].trim());
			}
		}
		
		for(Entry<String, Integer> entry : shiftBuffer.entrySet())
			labelManager.setLabel(entry.getKey(), entry.getValue(), 1);
		
		parsedData = data.toArray(new String[0]);
	}
	
	private void parseInstructions() throws UnkownLabelException, UnkownInstructionException {
		parsedInstructions = new Instruction[filteredInstructions.length];
		for(int i = 0; i < parsedInstructions.length; i++) {
			String instructionLine = filteredInstructions[i];
			int spaceIndex = instructionLine.indexOf(' ');
			if(spaceIndex == -1) {
				parsedInstructions[i] = InstructionFactory.createInstruction(instructionLine.trim(), new String[0], new int[0]);
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
					if(trimmedParam.matches("\\d+")) {
						types[j] = 8;
						params[j] = trimmedParam;
					} else if(!labelManager.containsLabel(trimmedParam)) {
						throw new UnkownLabelException();
					} else {
						types[j] = labelManager.getLabelType(trimmedParam);
						int value = labelManager.getLabelValue(trimmedParam);

						if(types[j] == 1) {// Data memory label
							//TODO complete method
						} else if(types[j] == 2) {// Instruction memory label
							//TODO complete method
						} else if(types[j] == 4) {// Register label
							params[j] = Integer.toBinaryString(value);
							while(params[j].length() < 5)
								params[j] = "0" + params[j];
						}
					}
				}
				parsedInstructions[i] = InstructionFactory.createInstruction(name, params, types);
			}
		}
			
	}
	
	private void assembleData() {
		assembledData = new String[parsedData.length];
//		for(int i = 0; i < assembledInstructions.length; i++)
//			assembledData[i] = parsedData[i].getBits();
	}
	
	private void assembleInstructions() {
		assembledInstructions = new String[parsedInstructions.length];
		for(int i = 0; i < assembledInstructions.length; i++) {
			assembledInstructions[i] = parsedInstructions[i].getBits();
			System.out.println(assembledInstructions[i]);
		}
	}
	
}
