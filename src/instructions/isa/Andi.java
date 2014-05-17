package instructions.isa;

import assembly.Assembler;
import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class Andi extends Instruction {

	public Andi(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.IFormat, parameters, types, new int[] {4, 4, 8});
		opcode = "100001";
		rs = parameters[1];
		rt = parameters[0];
		immediate = Assembler.assembleIntegral(parameters[2], 16);
	}

}
