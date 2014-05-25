package instructions.isa;

import assembly.Assembler;
import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class Bne extends Instruction {

	public Bne(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.IFormat, parameters, types, new int[] {4, 4, 2});
		opcode = "000101";
		rs = parameters[0];
		rt = parameters[1];
		
		String extendedParams[] = parameters[2].split(",");
		int branchTarget = Integer.parseInt(extendedParams[0]);
		int currentPosition = Integer.parseInt(extendedParams[1]);
		String deltaValue = Integer.toString(branchTarget - currentPosition - 1);
		System.out.println("BNE: " + extendedParams[0] + ' ' + extendedParams[1] + ' ' + deltaValue);
		
		immediate = Assembler.assembleIntegral(deltaValue, 16);
	}

}
