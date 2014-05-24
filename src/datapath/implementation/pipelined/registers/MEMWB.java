package datapath.implementation.pipelined.registers;


public class MEMWB extends AbstractPipelineRegister {

	private static final String[] inputs = { "RegWrite", "MemtoReg",
			"RegisterRd", "Rs", "Rt" };
	private static final String[] outputs = { "RegWrite", "MemtoReg",
			"RegisterRd", "Rs", "Rt" };
	
	public MEMWB() {
		super(inputs, outputs, "MEM/WB");
		// TODO Auto-generated constructor stub
	}

}
