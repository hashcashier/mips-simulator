package instructions;

import registers.RegisterManager;



public abstract class Instruction {
	protected String[] params;
	protected int[] paramTypes;
	protected InstructionType instructionType;
	protected String opcode, rs, rt, rd, shamt, funct, immediate, address;
	
	protected Instruction(InstructionType type, String[] parameters, int[] types, int[] expectedTypes)
			throws InvalidParameterException {
		instructionType = type;
		
		if(parameters.length != expectedTypes.length)
			throw new InvalidParameterException("Illegal number of parameters.");
		
		params = parameters;
		for(int i = 0; i < params.length; i++)
			if((types[i]&expectedTypes[i]) == 0)
				throw new InvalidParameterException("Illegal parameter type.");
			else
				params[i] = params[i].trim();
	}
	
	public String getBits() {
		switch(instructionType) {
			case RFormat:
				return opcode + rs + rt + rd + shamt + funct;
			case IFormat:
				return opcode + rs + rt + immediate;
			case JFormat:
				return opcode + address;
			default:
				return RegisterManager.zeros32();
		}
	}
}
