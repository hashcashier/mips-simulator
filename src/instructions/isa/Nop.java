package instructions.isa;

import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class Nop extends Instruction {

	public Nop(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.RFormat, new String[] {"00000", "00000", "00000"}, new int[] {4, 4, 4}, new int[] {4, 4, 4});
		opcode = "000000";
		rs = "00000";
		rt = "00000";
		rd = "00000";
		shamt = "00000";
		funct = "000000";
	}

}
