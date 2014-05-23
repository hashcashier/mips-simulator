package datapath.implementation.pipelined.registers;

import java.util.Hashtable;

public class IFID extends AbstractPipelineRegister {

	private static final String[] inputs 	= {"PC", "Instruction"};
	private static final String[] outputs 	= {"PC", "Instruction"};
	
	public IFID() {
		super(inputs, outputs, "IF/ID");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(Hashtable<String, String> ht) {
		for (int i=0; i<inputs.length; i++) {
			this.setInputValue(inputs[i], ht.get(inputs[i]));
		}
	}

}
