package instructions.isa;

import assembly.Assembler;
import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class Sw extends Instruction {

	public Sw(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.IFormat, parameters, types, new int[] {4, 8, 4});
		opcode = "101011";
		rs = parameters[0];
		rt = parameters[2];
		immediate = Assembler.assembleIntegral(parameters[1], 16);
	}

}
