package datapath.implementation.pipelined.registers;

public abstract class AbstractPipelineRegister {
	String inputs[], outputs[], inputNames[], outputNames[], name;
	
	public AbstractPipelineRegister(String[] inputNames, String[] outputNames, String registerName) {
		int inputCount = inputNames.length, outputCount = outputNames.length;
		inputs = new String[inputCount];
		outputs = new String[outputCount];
		name = registerName;
	}
	
	public String getOutputValue(String outputName) {
		for(int i = 0; i < outputs.length; i++)
			if(outputNames[i].equals(outputName))
				return outputs[i];
		return null;
	}
}
