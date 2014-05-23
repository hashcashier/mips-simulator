package datapath.implementation;

import java.util.Hashtable;

import datapath.AbstractDatapath;
import datapath.implementation.pipelined.registers.*;

public class Pipelined extends AbstractDatapath {
	AbstractPipelineRegister[] pipelineRegisters;

	Pipelined(String[] instructions, String[] data, int programOffset) {
		super(instructions, data, programOffset);
		pipelineRegisters = new AbstractPipelineRegister[4];
		pipelineRegisters[0] = new IFID();
		pipelineRegisters[1] = new IDEX();
		pipelineRegisters[2] = new EXMEM();
		pipelineRegisters[3] = new MEMWB();
	}

	@Override
	public boolean nextStep() {
		// TODO Auto-generated method stub
		return false;
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
