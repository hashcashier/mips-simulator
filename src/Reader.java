import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Reader {

	public static ArrayList<String> read(String filePath) throws FileNotFoundException {
		ArrayList<String> inputFileLines = new ArrayList<String>();
		System.out.println("Opening file: " + filePath);
		FileReader inputFileReader = new FileReader(filePath);
		Scanner inputFileScanner = new Scanner(inputFileReader);
		while(inputFileScanner.hasNextLine())
			inputFileLines.add(inputFileScanner.nextLine());
		inputFileScanner.close();
		return inputFileLines;
	}

}
