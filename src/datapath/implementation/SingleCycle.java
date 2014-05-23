package datapath.implementation;

import java.util.Hashtable;

import datapath.AbstractDatapath;

public class SingleCycle extends AbstractDatapath {

	public SingleCycle(String[] instructions, String[] data, int programOffset) {
		super(instructions, data, programOffset);
		// TODO Auto-generated constructor stub
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
		return null;
	}

}
