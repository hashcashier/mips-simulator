package datapath.implementation.pipelined.registers;

import registers.RegisterManager;


public class MEMWB extends AbstractPipelineRegister {

	private static final String[] inputs = { "RegWrite", "MemToReg",
			"RegisterRd", "Rs", "Rt" };
	private static final String[] outputs = { "RegWrite", "MemToReg",
			"RegisterRd", "Rs", "Rt" };
	private static final String[] initial	= {"0", "0", "00000", RegisterManager.zeros32(), RegisterManager.zeros32()}; 
	
	public MEMWB() {
		super(inputs, outputs, "MEM/WB");
		for(int i = 0; i < initial.length; i++) {
			super.inputs[i] = initial[i];
			super.outputs[i] = initial[i];
		}
	}

}
