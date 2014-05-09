package instructions.isa;

public class Add extends Instruction {

	public Add(String parameters) throws InvalidParameterException {
		super(parameters, 3);
	}

	@Override
	public String getBits() {
		// TODO Auto-generated method stub
		return "00000000000000000000000000000000";
	}

}
