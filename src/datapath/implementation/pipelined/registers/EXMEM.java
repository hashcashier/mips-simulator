package datapath.implementation.pipelined.registers;

import registers.RegisterManager;


public class EXMEM extends AbstractPipelineRegister {

	private static final String[] inputs = { "PC", "RegWrite", "Branch",
			"MemWrite", "MemRead", "MemToReg", "RegisterRd", "Rt", "Address",
			"BranchAddress", "Zero" };
	private static final String[] outputs = { "PC", "RegWrite", "Branch",
			"MemWrite", "MemRead", "MemToReg", "RegisterRd", "Rt", "Address",
			"BranchAddress", "Zero" };
	private static final String[] initial = { RegisterManager.zeros32(), "0",
		"0", "0", "0", "0", "00000", RegisterManager.zeros32(), RegisterManager.zeros32(), RegisterManager.zeros32(), "0"};

	public EXMEM() {
		super(inputs, outputs, "EX/MEM");
		for(int i = 0; i < initial.length; i++) {
			super.inputs[i] = initial[i];
			super.outputs[i] = initial[i];
		}
	}

}
