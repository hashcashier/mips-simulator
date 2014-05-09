package simulation;

import java.io.FileNotFoundException;

import assembly.ASMReader;
import assembly.Assembler;
import assembly.DuplicateLabelException;

public class Simulator {
	public Simulator(String filePath) throws FileNotFoundException, DuplicateLabelException {
		ASMReader assemblyFileReader = new ASMReader(filePath);
		String[] rawInstructions = assemblyFileReader.getInstructions(), rawData = assemblyFileReader.getData();
		Assembler assembler = new Assembler(rawInstructions, rawData);
		
	}
}
