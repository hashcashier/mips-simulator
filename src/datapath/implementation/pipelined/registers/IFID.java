package datapath.implementation.pipelined.registers;

public class IFID extends AbstractPipelineRegister {

	private static final String[] inputs 	= {"PC", "Instruction"};
	private static final String[] outputs 	= {"PC", "Instruction"};
	
	public IFID() {
		super(inputs, outputs, "IF/ID");
		// TODO Auto-generated constructor stub
	}

}
