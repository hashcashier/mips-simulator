package datapath.implementation.pipelined.registers;

import registers.RegisterManager;


public class IDEX extends AbstractPipelineRegister {

	private static final String[] inputs = { "PC", "RegWrite", "RegDst",
			"ALUOp", "ALUSrc", "Branch", "MemWrite", "MemRead", "MemToReg",
			"JumpReg", "RegisterRt", "RegisterRd", "Rs", "Rt", "Immediate" };
	private static final String[] outputs = { "PC", "RegWrite", "RegDst",
			"ALUOp", "ALUSrc", "Branch", "MemWrite", "MemRead", "MemToReg",
			"JumpReg", "RegisterRt", "RegisterRd", "Rs", "Rt", "Immediate" };
	private static final String[] initial = { RegisterManager.zeros32(), "0",
			"0", "000", "0", "0", "0", "0", "0", "0", "00000", "00000",
			RegisterManager.zeros32(), RegisterManager.zeros32(),
			RegisterManager.zeros32() };

	public IDEX() {
		super(inputs, outputs, "ID/EX");
		for(int i = 0; i < initial.length; i++) {
			super.inputs[i] = initial[i];
			super.outputs[i] = initial[i];
		}
	}

}
