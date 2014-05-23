package datapath.implementation.pipelined.registers;

import java.util.Hashtable;

public class IDEX extends AbstractPipelineRegister {

	private static final String[] inputs = { "PC", "RegWrite", "RegDst",
			"ALUOp", "ALUSrc", "Branch", "MemWrite", "MemRead", "MemtoReg",
			"RegisterRt", "RegisterRd", "Rs", "Rt", "JumpAddress" };
	private static final String[] outputs = { "PC", "RegWrite", "RegDst",
			"ALUOp", "ALUSrc", "Branch", "MemWrite", "MemRead", "MemtoReg",
			"RegisterRt", "RegisterRd", "Rs", "Rt", "JumpAddress" };

	public IDEX() {
		super(inputs, outputs, "ID/EX");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(Hashtable<String, String> ht) {
		// TODO Auto-generated method stub
		
	}

}
