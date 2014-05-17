package instructions.isa;

import assembly.Assembler;
import instructions.Instruction;
import instructions.InstructionType;
import instructions.InvalidParameterException;

public class Beq extends Instruction {

	public Beq(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.IFormat, parameters, types, new int[] {4, 4, 2});
		opcode = "000100";
		rs = parameters[0];
		rt = parameters[1];
		
		String extendedParams[] = parameters[2].split(",");
		int branchTarget = Integer.parseInt(extendedParams[0]);
		int currentPosition = Integer.parseInt(extendedParams[1]);
		String deltaValue = Integer.toString(branchTarget - currentPosition - 1);
		
		immediate = Assembler.assembleIntegral(deltaValue, 16);
	}

}
