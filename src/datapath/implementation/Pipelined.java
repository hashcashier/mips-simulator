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
import datapath.implementation.pipelined.stages.*;

public class Pipelined extends AbstractDatapath {
	AbstractPipelineRegister[] pipelineRegisters;
	Stage[] stages;
	LinkedList<Hashtable<String, String>> processes;

	Pipelined(String[] instructions, String[] data, int programOffset) {
		super(instructions, data, programOffset);
		pipelineRegisters = new AbstractPipelineRegister[4];
		stages = new Stage[5];
		pipelineRegisters[0] = new IFID();
		pipelineRegisters[1] = new IDEX();
		pipelineRegisters[2] = new EXMEM();
		pipelineRegisters[3] = new MEMWB();
		stages[0] = new IF();
		stages[1] = new ID();
		stages[2] = new EX();
		stages[3] = new MEM();
		stages[4] = new WB();
	}

	@Override
	public boolean nextStep() throws InvalidOperationException {
		// DON'T FORGET TERMINATION
		LinkedList<Hashtable<String, String>> newProcesses = new LinkedList<Hashtable<String,String>>();
		int currentPC = this.getProgramCounterValue();
		String currentInstruction = this.getInstructionMemoryContents()
				.get(currentPC);
		String incrementedPC = Pipelined.incrementPC(currentPC);
		
		Hashtable<String, String> inputIFID = new Hashtable<String, String>();
		Hashtable<String, String> inputIDEX = new Hashtable<String, String>();
		Hashtable<String, String> inputEXMEM = new Hashtable<String, String>();
		Hashtable<String, String> inputMEMWB = new Hashtable<String, String>();
		
		Hashtable<String, String> outputIFID = new Hashtable<String, String>();
		Hashtable<String, String> outputIDEX = new Hashtable<String, String>();
		Hashtable<String, String> outputEXMEM = new Hashtable<String, String>();
		Hashtable<String, String> outputMEMWB = new Hashtable<String, String>();
		
		inputIFID.put("PC", incrementedPC);
		inputIFID.put("Instruction", currentInstruction);
		outputIFID = pipelineRegisters[0].process(inputIFID);
		newProcesses.add(outputIFID);
		if (processes.size() > 0) {
			inputIDEX = stages[1].process(processes.removeFirst());
			outputIDEX = pipelineRegisters[1].process(inputIDEX);
			newProcesses.add(outputIDEX);
		}
		if (processes.size() > 0) {
			inputEXMEM = stages[2].process(processes.removeFirst());
			outputEXMEM = pipelineRegisters[2].process(inputEXMEM);
			newProcesses.add(outputEXMEM);
		}
		if (processes.size() > 0) {
			inputMEMWB = stages[3].process(processes.removeFirst());
			outputMEMWB = pipelineRegisters[3].process(inputMEMWB);
			newProcesses.add(outputMEMWB);
		}
		if (processes.size() > 0) {
			inputMEMWB = stages[3].process(processes.removeFirst());
			outputMEMWB = pipelineRegisters[3].process(inputMEMWB);
			newProcesses.add(outputMEMWB);
		}
		
		processes = newProcesses;
		newProcesses.clear();
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
