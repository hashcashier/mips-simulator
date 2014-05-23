package datapath.implementation.pipelined.registers;

import java.util.Hashtable;

public class EXMEM extends AbstractPipelineRegister {

	private static final String[] inputs = { "PC", "RegWrite", "Branch",
			"MemWrite", "MemRead", "MemtoReg", "RegisterRd", "Rs", "Rt",
			"JumpAddress", "Zero" };
	private static final String[] outputs = { "PC", "RegWrite", "Branch",
			"MemWrite", "MemRead", "MemtoReg", "RegisterRd", "Rs", "Rt",
			"JumpAddress", "Zero" };

	public EXMEM() {
		super(inputs, outputs, "EX/MEM");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hashtable<String, String> process(Hashtable<String, String> ht) {
		// TODO Auto-generated method stub
		return null;
	}

}
