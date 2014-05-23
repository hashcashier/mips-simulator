package datapath.implementation;

import java.util.Hashtable;

import datapath.AbstractDatapath;

public class Pipelined extends AbstractDatapath {

	Pipelined(String[] instructions, String[] data, int programOffset) {
		super(instructions, data, programOffset);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextStep() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void previousStep() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Hashtable<String, String> getPipelineRegistersContents() {
		// TODO Auto-generated method stub
		return null;
	}

}
