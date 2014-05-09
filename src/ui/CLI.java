package ui;
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
	}

}
