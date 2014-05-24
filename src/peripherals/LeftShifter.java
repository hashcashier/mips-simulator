package peripherals;

public class LeftShifter {
	public static String shiftLeft(String input, int degree, int size) {
		String result = input;
		while(degree-- > 0)
			result = result+ "0";
		while(result.length() > size)
			result = result.substring(1);
		return result;
	}
}
