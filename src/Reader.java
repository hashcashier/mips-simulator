import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Reader {

	public static ArrayList<String> read(String filePath) {
		ArrayList<String> inputFileLines = new ArrayList<String>();
		try {
			System.out.println("Opening file: " + filePath);
			FileReader inputFileReader = new FileReader(filePath);
			Scanner inputFileScanner = new Scanner(inputFileReader);
			while(inputFileScanner.hasNextLine())
				inputFileLines.add(inputFileScanner.nextLine());
			inputFileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		return inputFileLines;
	}

}
