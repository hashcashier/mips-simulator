package instructions.pseudo;

import instructions.Instruction;
import instructions.InvalidParameterException;
import instructions.PseudoInstruction;
import instructions.isa.Add;

public class Move extends PseudoInstruction {

	protected Move(String[] parameters, int[] types)
			throws InvalidParameterException {
		super(parameters, types, new int[] { 4, 4 });
		instructionReplacement = new Instruction[1];
		instructionReplacement[0] = new Add(new String[] { parameters[0],
				parameters[1], "00000" }, new int[] { 4, 4, 4 });
	}

}
