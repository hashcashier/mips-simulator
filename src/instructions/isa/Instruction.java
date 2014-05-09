package instructions.isa;


public abstract class Instruction {
	protected String[] params;
	protected int paramCount;
	
	protected Instruction(String parameters, int expected) throws InvalidParameterException {
		params = parameters.split(",");
		for(int i = 0; i < params.length; i++)
			params[i] = params[i].trim();
		paramCount = params.length;
		if(paramCount != expected)
			throw new InvalidParameterException();
	}
	
	public abstract String getBits();
}
