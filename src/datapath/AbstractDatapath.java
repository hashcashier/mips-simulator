package datapath;

import java.util.Hashtable;

import memory.DataMemory;
import memory.InstructionMemory;
import memory.Memory;
import peripherals.ProgramCounter;
import registers.RegisterManager;
import alu.InvalidOperationException;
import control.ControlUnit;

public abstract class AbstractDatapath {
	protected Memory hardwareMemory;
	protected DataMemory dataMemory;
	protected InstructionMemory instructionMemory;
	protected RegisterManager registerManager;
	protected ProgramCounter pc;
	protected ControlUnit cu;
	
	public AbstractDatapath(String[] instructions, String[] data, int programOffset) {
		hardwareMemory = new Memory();
		instructionMemory = new InstructionMemory(hardwareMemory, instructions, programOffset);
		dataMemory = new DataMemory(hardwareMemory, data);
		pc = new ProgramCounter();
		pc.setCounter(programOffset);
		cu = new ControlUnit();
		registerManager = new RegisterManager();
	}
	
	public Hashtable<String, String> getRegisterContents() {
		return registerManager.getRegisterContents();
	}

	public Hashtable<Long, String> getDataMemoryContents() {
		return dataMemory.getMemoryContents();
	}

	public Hashtable<Long, String> getInstructionMemoryContents() {
		return instructionMemory.getMemoryContents();
	}
	
	public void setMemoryContent(long address, String value) {
		hardwareMemory.write(address, value);
	}
	
	public long getProgramCounterValue() {
		return pc.getCounter();
	}
	
	public RegisterManager getRegisterManager(){
		return registerManager;
	}
	
	public Hashtable<String, String> getControlSignals(){
		return cu.getControlSignals();
	}
	
	public abstract Hashtable<String, String> getPipelineRegistersContents();
	public abstract boolean nextStep() throws InvalidOperationException;
	public abstract boolean previousStep();	
}
