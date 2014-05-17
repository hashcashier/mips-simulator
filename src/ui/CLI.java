package ui;
import java.io.FileNotFoundException;
import java.util.Scanner;

import assembly.Assembler;
import assembly.DuplicateLabelException;

import simulation.Simulator;

public class CLI {

	public static void main(String[] args) {
		String filePath;
		if(args.length == 0) {
			Scanner commandLineScanner = new Scanner(System.in);
			System.out.print("Enter file path: ");
			filePath = commandLineScanner.nextLine();
			commandLineScanner.close();
		} else {
			filePath = args[0];
		}
		
		try {
			Simulator mipsSimulator = new Simulator(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateLabelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
