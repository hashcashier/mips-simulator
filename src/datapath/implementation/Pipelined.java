package datapath.implementation;

import java.util.Hashtable;
import java.util.LinkedList;

import alu.ALU;
import alu.ALUControl;
import alu.InvalidOperationException;
import alu.Result;
import datapath.AbstractDatapath;
import datapath.implementation.pipelined.registers.AbstractPipelineRegister;
import datapath.implementation.pipelined.registers.EXMEM;
import datapath.implementation.pipelined.registers.IDEX;
import datapath.implementation.pipelined.registers.IFID;
import datapath.implementation.pipelined.registers.MEMWB;
import datapath.implementation.pipelined.stages.EX;
import datapath.implementation.pipelined.stages.ID;
import datapath.implementation.pipelined.stages.IF;
import datapath.implementation.pipelined.stages.MEM;
import datapath.implementation.pipelined.stages.Stage;
import datapath.implementation.pipelined.stages.WB;

public class Pipelined extends AbstractDatapath {
	AbstractPipelineRegister[] pipelineRegisters;
	Stage[] stages;
	LinkedList<Hashtable<String, String>> processes;

	public Pipelined(String[] instructions, String[] data, int programOffset) {
		super(instructions, data, programOffset);
		pipelineRegisters = new AbstractPipelineRegister[4];
		pipelineRegisters[0] = new IFID();
		pipelineRegisters[1] = new IDEX();
		pipelineRegisters[2] = new EXMEM();
		pipelineRegisters[3] = new MEMWB();
		stages = new Stage[5];
		stages[4] = new IF();
		stages[3] = new ID();
		stages[2] = new EX();
		stages[1] = new MEM();
		stages[0] = new WB();
	}

	@Override
	public boolean nextStep() {
		// DON'T FORGET TERMINATION
		for (int i = 0; i < stages.length; i++)
			stages[i].execute(dataMemory, instructionMemory, registerManager,
					pc, pipelineRegisters, cu);
		for (int i = 0; i < pipelineRegisters.length; i++)
			pipelineRegisters[i].transferValues();
		System.out.println(pc.getCounter() + "::" + instructionMemory.getLastInstructionAddress());
		return pc.getCounter() <= instructionMemory.getLastInstructionAddress();
	}

	@Override
	public boolean previousStep() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Hashtable<String, String> getPipelineRegistersContents() {
		Hashtable<String, String> result = new Hashtable<String, String>();
		for (int i = 0; i < pipelineRegisters.length; i++) {
			String[] outputs = pipelineRegisters[i].getOutputNames();
			String name = pipelineRegisters[i].getName();
			for (int j = 0; j < outputs.length; j++) {
//				System.out.println(name);
//				System.out.println(i);
//				System.out.println(outputs[j]);
//				System.out.println(pipelineRegisters[i].getOutputValue(outputs[j]));
				result.put(name + "." + outputs[j],
						pipelineRegisters[i].getOutputValue(outputs[j]));
			}
		}
		return result;
	}

}
