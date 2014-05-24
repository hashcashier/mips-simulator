package simulation;

import instructions.UnkownInstructionException;

import java.io.FileNotFoundException;
import java.util.Hashtable;

import alu.InvalidOperationException;
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
			throws FileNotFoundException, DuplicateLabelException,
			UnkownInstructionException {
		assemble(filePath, dataPath, programOffset);
	}

	public Simulator() {

	}

	public void assemble(String filePath, String dataPath, int programOffset)
			throws FileNotFoundException, DuplicateLabelException,
			UnkownInstructionException {
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
		} catch (UnkownDatapathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void step() throws InvalidOperationException {
		if(datapath != null)
			datapath.nextStep();
	}

	public void run() throws InvalidOperationException {
		if(datapath != null)
			while (datapath.nextStep());
	}

	public Hashtable<String, String> getControlSignals() {
		// TODO Wait for Azazy..
		return new Hashtable<String, String>();
	}

	public Hashtable<String, String> getRegisterMemoryContents() {
		if(datapath == null)
			return new Hashtable<String, String>(); 
		return datapath.getRegisterContents();
	}

	public Hashtable<Long, String> getDataMemoryContents() {
		if(datapath == null)
			return new Hashtable<Long, String>();
		return datapath.getDataMemoryContents();
	}

	public Hashtable<Long, String> getInstructionMemoryContents() {
		if(datapath == null)
			return new Hashtable<Long, String>();
		return datapath.getInstructionMemoryContents();
	}

	public Hashtable<String, String> getPipelineRegistersContents() {
		if(datapath == null)
			return new Hashtable<String, String>();
		return datapath.getPipelineRegistersContents();
	}

	public long getProgramCounterValue() {
		if(datapath == null)
			return 0;
		return datapath.getProgramCounterValue();
	}

	public void setMemoryContent(long address, String value) {
		if(datapath != null)
			datapath.setMemoryContent(address, value);
	}
}
