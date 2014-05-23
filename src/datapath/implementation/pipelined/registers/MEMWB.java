package datapath.implementation.pipelined.registers;

import java.util.Hashtable;

public class MEMWB extends AbstractPipelineRegister {

	private static final String[] inputs = { "RegWrite", "MemtoReg",
			"RegisterRd", "Rs", "Rt" };
	private static final String[] outputs = { "RegWrite", "MemtoReg",
			"RegisterRd", "Rs", "Rt" };
	
	public MEMWB() {
		super(inputs, outputs, "MEM/WB");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hashtable<String, String> process(Hashtable<String, String> ht) {
		// TODO Auto-generated method stub
		return null;
	}

}
