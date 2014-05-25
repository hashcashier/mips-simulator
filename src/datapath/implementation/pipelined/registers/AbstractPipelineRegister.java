package datapath.implementation.pipelined.registers;

public abstract class AbstractPipelineRegister {
	String inputs[], outputs[], inputNames[], outputNames[], name;
	
	public AbstractPipelineRegister(String[] inputNames, String[] outputNames, String registerName) {
		int inputCount = inputNames.length, outputCount = outputNames.length;
		inputs = new String[inputCount];
		outputs = new String[outputCount];
		name = registerName;
		this.inputNames = inputNames;
		this.outputNames = outputNames;
	}
	
	public String getOutputValue(String outputName) {
		for(int i = 0; i < outputs.length; i++)
			if(outputNames[i].equals(outputName))
				return outputs[i];
		return null;
	}

	public String getInputValue(String inputName) {
		for(int i = 0; i < inputs.length; i++)
			if(inputNames[i].equals(inputName))
				return inputs[i];
		return null;
	}
	
	public void setInputValue(String inputName, String value) {
		for(int i = 0; i < inputs.length; i++)
			if(inputNames[i].equals(inputName))
				inputs[i] = value;
	}
	
	public String[] getOutputNames() {
		return outputNames;
	}
	
	public String getName() {
		return name;
	}

	public void transferValues() {
		System.out.println("-------------------------------");
		for(int i = 0; i < outputNames.length; i++) {
			System.out.println(name + ' ' + outputNames[i] + ' ' + outputs[i] + " <---- " + getInputValue(outputNames[i]));
			outputs[i] = getInputValue(outputNames[i]);
		}
	}
}
