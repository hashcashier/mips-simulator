package datapath.implementation;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

import alu.ALU;
import alu.ALUControl;
import alu.InvalidOperationException;
import alu.Result;
import datapath.AbstractDatapath;
import datapath.implementation.pipelined.registers.*;

public class Pipelined extends AbstractDatapath {
	AbstractPipelineRegister[] pipelineRegisters;
	LinkedList<Hashtable<String, String>> processes;

	Pipelined(String[] instructions, String[] data, int programOffset) {
		super(instructions, data, programOffset);
		pipelineRegisters = new AbstractPipelineRegister[4];
		pipelineRegisters[0] = new IFID();
		pipelineRegisters[1] = new IDEX();
		pipelineRegisters[2] = new EXMEM();
		pipelineRegisters[3] = new MEMWB();
	}

	@Override
	public boolean nextStep() throws InvalidOperationException {
		// DON'T FORGET TERMINATION
		int currentPC = this.getProgramCounterValue();
		String currentInstruction = this.getInstructionMemoryContents()
				.get(currentPC);
		String incrementedPC = Pipelined.incrementPC(currentPC);
		
		Hashtable<String, String> inputIFID = new Hashtable<String, String>();
		Hashtable<String, String> inputIDEX = new Hashtable<String, String>();
		Hashtable<String, String> inputEXMEM = new Hashtable<String, String>();
		Hashtable<String, String> inputMEMWB = new Hashtable<String, String>();
		
		inputIFID.put("PC", incrementedPC);
		inputIFID.put("Instruction", currentInstruction);
		Hashtable<String, String> outputIFID = pipelineRegisters[0].process(inputIFID);
		
		
		return false;
	}
		
	private static String incrementPC(int currentInstructionCounter) throws InvalidOperationException {
		String four = "100";
		for (int i=0; i<29; i++) four = "0" + four;
		ALUControl control = new ALUControl("10", "100000");
		ALU alu = new ALU(currentInstructionCounter+"", four, control);
		Result result = alu.execute();
		return result.getResult();
	}

	@Override
	public boolean previousStep() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Hashtable<String, String> getPipelineRegistersContents() {
		// TODO Auto-generated method stub
		return null;
	}

}
