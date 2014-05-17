package instructions.isa;

import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class Jr extends Instruction {

	public Jr(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.RFormat, parameters, types, new int[] {4});
		opcode = "000000";
		rs = parameters[1];
		rt = parameters[2];
		rd = parameters[0];
		shamt = "00000";
		funct = "000001";
	}

}
