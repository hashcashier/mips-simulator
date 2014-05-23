package instructions;

public abstract class PseudoInstruction {
	protected String[] params;
	protected int[] paramTypes;
	protected Instruction[] instructionReplacement;
	
	protected PseudoInstruction(String[] parameters, int[] types, int[] expectedTypes)
			throws InvalidParameterException {
		
		if(parameters.length != expectedTypes.length)
			throw new InvalidParameterException("Illegal number of parameters.");
		
		params = parameters;
		for(int i = 0; i < params.length; i++)
			if((types[i]&expectedTypes[i]) == 0)
				throw new InvalidParameterException("Illegal parameter type.");
			else
				params[i] = params[i].trim();
	}
	
	public Instruction[] getReplacement() {
		return instructionReplacement;
	}
}
