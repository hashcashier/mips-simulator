package instructions;



public abstract class Instruction {
	protected String[] params;
	protected int[] paramTypes;
	protected InstructionType instructionType;
	
	protected Instruction(InstructionType type, String[] parameters, int[] types, int[] expectedTypes, int expectedCount) throws InvalidParameterException {
		instructionType = type;
		
		if(parameters.length != expectedCount)
			throw new InvalidParameterException("Illegal number of parameters.");
		
		params = parameters;
		for(int i = 0; i < params.length; i++)
			if((types[i]&expectedTypes[i]) == 0)
				throw new InvalidParameterException("Illegal parameter type.");
			else
				params[i] = params[i].trim();
	}
	
	public abstract String getBits();
}
