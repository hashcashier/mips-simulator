import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class CLI {

	public static void main(String[] args) {
		if(args.length == 0) {
			Scanner commandLineScanner = new Scanner(System.in);
			args = new String[1];
			System.out.print("Enter file path: ");
			args[0] = commandLineScanner.nextLine();
			commandLineScanner.close();
		}
		ArrayList<String> inputFileLines = new ArrayList<String>();
		try {
			inputFileLines = Reader.read(args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("File not found, aborting.");
			return;
		}
		Simulator mips = new Simulator();
		if(mips.syntaxCheck(inputFileLines)) {
			System.out.println("Syntax errors found, aborting.");
			return;
		} else {
			
		}
	}

}
