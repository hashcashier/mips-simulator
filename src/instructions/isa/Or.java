package instructions.isa;

import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class Or extends Instruction {

	public Or(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.RFormat, parameters, types, new int[] {4, 4, 4}, 3);
		opcode = "000000";
		rs = parameters[1];
		rt = parameters[2];
		rd = parameters[0];
		shamt = "00000";
		funct = "100101";
	}

}
