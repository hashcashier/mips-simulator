package assembly;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class ASMReader {
	
	private String[] inputFileLines, dataFile, instructionFile;
	
	public ASMReader(String filePath) throws FileNotFoundException {
		ArrayList<String> inputFileLines = new ArrayList<String>();
		System.out.println("Opening file: " + filePath);
		FileReader inputFileReader = new FileReader(filePath);
		Scanner inputFileScanner = new Scanner(inputFileReader);
		while(inputFileScanner.hasNextLine())
			inputFileLines.add(inputFileScanner.nextLine());
		inputFileScanner.close();
		this.inputFileLines = inputFileLines.toArray(new String[0]);
//		for(int i = 0; i < this.inputFileLines.length; i++)
//			System.out.println("Read: " + this.inputFileLines[i]);
	}
	
	public ASMReader(String[] lines) {
		ArrayList<String> inputFileLines = new ArrayList<String>();
		for(int i = 0; i < lines.length; i++)
			if(lines[i].length() != 0)
				inputFileLines.add(lines[i]);
		this.inputFileLines = inputFileLines.toArray(new String[0]);
	}
	
	private String[] read(String marker, String[] safeMarkers) {
		boolean reading = false;
		ArrayList<String> filtered = new ArrayList<String>();
		for(int i = 0; i < inputFileLines.length; i++) {
			String inputFileLine = inputFileLines[i].trim();
			
			if(inputFileLine.charAt(0) == '.') {
				boolean markerMatch = inputFileLine.equals("." + marker);
				boolean safeMarker = false;
				
				for(String safe : safeMarkers)
					safeMarker |= inputFileLine.startsWith("." + safe);
				
				if(markerMatch) {
					reading = true;
					continue;
				} else if(!safeMarker) {
					reading = false;
					continue;
				}
			}
			
			if(reading)
				filtered.add(inputFileLines[i]);
		}
		
		return filtered.toArray(new String[0]);
	}

	public String[] getInstructions() {
		if(instructionFile == null)
			instructionFile = read("text", new String[0]);
		return instructionFile;
	}

	public String[] getData() {
		if(dataFile == null)
			dataFile = read("data", new String[] {"ascii", "asciiz", "word"});
		return dataFile;
	}
}
