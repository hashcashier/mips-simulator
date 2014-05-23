package datapath.implementation.pipelined.registers;

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

}
