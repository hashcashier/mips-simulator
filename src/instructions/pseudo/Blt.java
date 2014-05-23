package instructions.pseudo;

import instructions.Instruction;
import instructions.InvalidParameterException;
import instructions.PseudoInstruction;
import instructions.isa.Bne;
import instructions.isa.Slt;

public class Blt extends PseudoInstruction {

	protected Blt(String[] parameters, int[] types)
			throws InvalidParameterException {
		super(parameters, types, new int[] { 4, 4, 2});
		instructionReplacement = new Instruction[2];
		instructionReplacement[0] = new Slt(
				new String[] { "00001", parameters[0], parameters[1] },
				new int[] { 4, 4, 4 });
		instructionReplacement[1] = new Bne(
				new String[] { "00001", "00000", parameters[2] },
				new int[] { 4, 4, 2 });
	}

}
