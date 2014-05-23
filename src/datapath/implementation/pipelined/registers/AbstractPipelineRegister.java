package datapath.implementation.pipelined.registers;

import java.util.Hashtable;

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
	
	public void setInputValue(String inputName, String value) {
		for(int i = 0; i < inputs.length; i++)
			if(inputNames[i].equals(inputName))
				inputs[i] = value;
	}

	public abstract void process(Hashtable<String, String> ht);
}
