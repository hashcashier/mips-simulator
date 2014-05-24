package datapath.implementation.pipelined.registers;

import registers.RegisterManager;


public class IFID extends AbstractPipelineRegister {

	private static final String[] inputs 	= {"PC", "Instruction"};
	private static final String[] outputs 	= {"PC", "Instruction"};
	private static final String[] initial	= {RegisterManager.zeros32(),RegisterManager.zeros32()}; 
	
	public IFID() {
		super(inputs, outputs, "IF/ID");
		for(int i = 0; i < initial.length; i++) {
			super.inputs[i] = initial[i];
			super.outputs[i] = initial[i];
		}
	}

}
