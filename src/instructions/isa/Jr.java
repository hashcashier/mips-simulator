package instructions.isa;

import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class Jr extends Instruction {

	public Jr(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.RFormat, parameters, types, new int[] {4});
		opcode = "000000";
		rs = parameters[0];
		rt = "00000";
		rd = "00000";
		shamt = "00000";
		funct = "001000";
	}

}
