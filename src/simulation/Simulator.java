package simulation;

import instructions.UnkownInstructionException;

import java.io.FileNotFoundException;

import assembly.ASMReader;
import assembly.Assembler;
import assembly.DuplicateLabelException;
import assembly.UnkownLabelException;

public class Simulator {
	public Simulator(String filePath) throws FileNotFoundException, DuplicateLabelException {
		ASMReader assemblyFileReader = new ASMReader(filePath);
		String[] rawInstructions = assemblyFileReader.getInstructions(), rawData = assemblyFileReader.getData();
		try {
			Assembler assembler = new Assembler(rawInstructions, rawData);
		} catch (UnkownLabelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnkownInstructionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
