package registers;

public class SignExtender {
	
	public String signExtend(String input) {
		if(input.charAt(0) == '0')
			return "0000000000000000" + input;
		else
			return "1111111111111111" + input;
	}
}
