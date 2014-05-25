package instructions.isa;

import assembly.Assembler;
import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class Srl extends Instruction {

	public Srl(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.RFormat, parameters, types, new int[] {4, 4, 8});
		opcode = "000000";
		rs = parameters[1];
		rt = "00000";
		rd = parameters[0];
		shamt = Assembler.assembleIntegral(parameters[2], 5);
		funct = "000010";
	}

}
