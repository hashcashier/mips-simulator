package simulation;

import instructions.UnkownInstructionException;

import java.io.FileNotFoundException;
import java.util.Hashtable;

import assembly.ASMReader;
import assembly.Assembler;
import assembly.DuplicateLabelException;
import assembly.UnkownLabelException;
import datapath.AbstractDatapath;
import datapath.DatapathFactory;
import datapath.UnkownDatapathException;

public class Simulator {
	AbstractDatapath datapath;

	public Simulator(String filePath, String dataPath, int programOffset)
			throws FileNotFoundException, DuplicateLabelException {
		ASMReader assemblyFileReader = new ASMReader(filePath);
		String[] rawInstructions = assemblyFileReader.getInstructions(), rawData = assemblyFileReader
				.getData();
		try {
			Assembler assembler = new Assembler(rawInstructions, rawData,
					programOffset);
			datapath = DatapathFactory.createDatapath(dataPath,
					assembler.getAssembledInstructions(),
					assembler.getAssembledData(), programOffset);
		} catch (UnkownLabelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnkownInstructionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnkownDatapathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Hashtable<String, String> getRegisterMemoryContents() {
		return datapath.getRegisterContents();
	}

	public Hashtable<Integer, String> getDataMemoryContents() {
		return datapath.getDataMemoryContents();
	}

	public Hashtable<Integer, String> getInstructionMemoryContents() {
		return datapath.getInstructionMemoryContents();
	}
	
	public Hashtable<String, String> getPipelineRegistersContents() {
		return datapath.getPipelineRegistersContents();
	}
	
	public int getProgramCounterValue() {
		return datapath.getProgramCounterValue();
	}
	
	public void setMemoryContent(int address, String value) {
		datapath.setMemoryContent(address, value);
	}
}
