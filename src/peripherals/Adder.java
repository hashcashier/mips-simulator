package peripherals;

import java.math.BigInteger;

public class Adder {
	public static String add(String a, String b) {
		BigInteger ai = new BigInteger(a, 2), bi = new BigInteger(b, 2);
		String result = ai.add(bi).toString(2);
		while(result.length() < Math.max(a.length(), b.length()))
			result = result.charAt(0) + result;
		return result.substring(0, Math.min(32, result.length()));
	}
}
