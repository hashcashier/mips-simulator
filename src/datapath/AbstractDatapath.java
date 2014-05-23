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
	
	public AbstractDatapath(String[] instructions, String[] data) {
		
	}
	
	public abstract void nextStep();
	public abstract void previousStep();
	
	public Hashtable<String, String> getRegisterContents() {
		Hashtable<String, String> result = new Hashtable<String, String>();
		return result;
	}

	public Hashtable<String, String> getDataMemoryContents() {
		Hashtable<String, String> result = new Hashtable<String, String>();
		return result;
	}

	public Hashtable<String, String> getInstructionMemoryContents() {
		Hashtable<String, String> result = new Hashtable<String, String>();
		return result;
	}
}
