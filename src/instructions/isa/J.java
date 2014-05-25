package instructions.isa;

import assembly.Assembler;
import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class J extends Instruction {

	public J(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.JFormat, parameters, types, new int[] {2});
		opcode = "000010";
		
		String extendedParams[] = parameters[0].split(",");
		int jumpTarget = Integer.parseInt(extendedParams[2]);
		System.out.println("JUMP: " + jumpTarget);
		String deltaValue = Integer.toString(jumpTarget);
		
		address = Assembler.assembleIntegral(deltaValue, 26);
	}

}
