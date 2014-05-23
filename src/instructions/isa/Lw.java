package instructions.isa;

import assembly.Assembler;
import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class Lw extends Instruction {

	public Lw(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.IFormat, parameters, types, new int[] {4, 8, 4});
		opcode = "100011";
		rs = parameters[2];
		rt = parameters[0];
		immediate = Assembler.assembleIntegral(parameters[1], 16);
	}

}
