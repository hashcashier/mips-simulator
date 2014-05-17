package instructions.isa;

import assembly.Assembler;
import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class Jal extends Instruction {

	public Jal(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.JFormat, parameters, types, new int[] {2});
		opcode = "000011";
		
		String extendedParams[] = parameters[0].split(",");
		int jumpTarget = Integer.parseInt(extendedParams[0]);
		String deltaValue = Integer.toString(jumpTarget);
		
		address = Assembler.assembleIntegral(deltaValue, 26);
	}

}
