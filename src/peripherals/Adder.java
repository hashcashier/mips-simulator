package peripherals;

import java.math.BigInteger;

public class Adder {
	public static String add(String a, String b) {
		BigInteger ai = new BigInteger(a, 2), bi = new BigInteger(b, 2);
		String result = ai.add(bi).toString(2); 
		return result.substring(0, Math.min(32, result.length()) - 1);
	}
}
