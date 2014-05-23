package datapath;

import java.util.Hashtable;

import memory.*;
import registers.RegisterManager;
import alu.ALU;

public abstract class AbstractDatapath {
	Memory hardwareMemory;
	DataMemory dataMemory;
	InstructionMemory instructionMemory;
	RegisterManager registerManager;
	ALU alu;
	
	public AbstractDatapath(String[] instructions, String[] data, int programOffset) {
		hardwareMemory = new Memory();
		instructionMemory = new InstructionMemory(hardwareMemory, instructions, programOffset);
		dataMemory = new DataMemory(hardwareMemory, data);
	}
	
	public abstract void nextStep();
	public abstract void previousStep();
	
	public Hashtable<String, String> getRegisterContents() {
		Hashtable<String, String> result = new Hashtable<String, String>();
		return result;
	}

	public Hashtable<Integer, String> getDataMemoryContents() {
		return dataMemory.getMemoryContents();
	}

	public Hashtable<Integer, String> getInstructionMemoryContents() {
		return instructionMemory.getMemoryContents();
	}
}
