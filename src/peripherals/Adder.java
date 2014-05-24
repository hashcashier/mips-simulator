package peripherals;

import java.math.BigInteger;

public class Adder {
	public static String add(String a, String b) {
		BigInteger ai = new BigInteger(a, 2), bi = new BigInteger(b, 2);
		System.out.println(ai);
		System.out.println(bi);
		BigInteger res = ai.add(bi);
		String result = res.toString(2);
		while(result.length() < Math.max(a.length(), b.length()))
			result = (res.compareTo(BigInteger.ZERO) >= 0 ? "0" : "1") + result;
		return result.substring(0, Math.min(32, result.length()));
	}
}
