package assembly;

import instructions.isa.Instruction;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

public class Assembler {
	String[] filteredInstructions, filteredData, assembledInstructions, assembledData, parsedData;
	Instruction[] parsedInstructions;
	LabelManager labelManager;
	public Assembler(String[] instructions, String[] data) throws DuplicateLabelException {
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
		
		parsedData = data.toArray(parsedData);
	}
	
	private void parseInstructions() {
		
	}
	
	private void assembleData() {
		assembledData = new String[parsedData.length];
//		for(int i = 0; i < assembledInstructions.length; i++)
//			assembledData[i] = parsedData[i].getBits();
	}
	
	private void assembleInstructions() {
		assembledInstructions = new String[parsedInstructions.length];
		for(int i = 0; i < assembledInstructions.length; i++)
			assembledInstructions[i] = parsedInstructions[i].getBits();
	}
	
}
