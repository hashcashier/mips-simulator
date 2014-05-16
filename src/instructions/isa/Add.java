package instructions.isa;

public class Add extends Instruction {

	public Add(String[] parameters, int[] types) throws InvalidParameterException {
		super(InstructionType.RFormat, parameters, types, new int[] {4, 4, 4}, 3);
	}

	@Override
	public String getBits() {
		// TODO Automate
		return "000000" + params[1] + params[2] + params[0] + "00000" + "000000";
	}

}
