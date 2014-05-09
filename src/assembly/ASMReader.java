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
		this.inputFileLines = inputFileLines.toArray(this.inputFileLines); 
	}
	
	private String[] read(String marker) {
		boolean reading = false;
		ArrayList<String> filtered = new ArrayList<String>();
		for(int i = 0; i < inputFileLines.length; i++) {
			String inputFileLine = inputFileLines[i].trim();
			
			boolean marked = inputFileLine.equals("." + marker);
			if(inputFileLine.charAt(0) == '.')
				reading = marked;
			
			if(reading) {
				filtered.add(inputFileLines[i]);
			}
		}
		
		return filtered.toArray(inputFileLines);
	}

	public String[] getInstructions() {
		if(instructionFile == null)
			instructionFile = read("text");
		return instructionFile;
	}

	public String[] getData() {
		if(dataFile == null)
			dataFile = read("data");
		return dataFile;
	}
}
