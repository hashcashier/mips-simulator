package peripherals;

import java.math.BigInteger;

public class Adder {
	public static String add(String a, String b) {
		BigInteger ai = new BigInteger(a, 2), bi = new BigInteger(b, 2);
		return ai.add(bi).toString(2);
	}
}
