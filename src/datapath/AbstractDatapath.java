package datapath;

import java.util.Hashtable;

import alu.ALU;
import registers.RegisterManager;
import instructions.InstructionMemory;
import data.DataMemory;

public abstract class AbstractDatapath {
	DataMemory dataMemory;
	InstructionMemory instructionMemory;
	RegisterManager registerManager;
	ALU alu;
	
	public AbstractDatapath(String[] instructions, String[] data, int programOffset) {
		instructionMemory = new InstructionMemory(instructions, programOffset);
		dataMemory = new DataMemory(data);
	}
	
	public abstract void nextStep();
	public abstract void previousStep();
	
	public Hashtable<String, String> getRegisterContents() {
		Hashtable<String, String> result = new Hashtable<String, String>();
		return result;
	}

	public Hashtable<Integer, String> getDataMemoryContents() {
		Hashtable<Integer, String> result = new Hashtable<Integer, String>();
		return result;
	}

	public Hashtable<Integer, String> getInstructionMemoryContents() {
		Hashtable<Integer, String> result = new Hashtable<Integer, String>();
		return result;
	}
}
