package peripherals;

import java.math.BigInteger;

import alu.Operation;

public class Adder {
	public static String add(String a, String b) {
		BigInteger ai = Operation.sign(a), bi = Operation.sign(b);
		BigInteger res = ai.add(bi);
		String result = res.toString(2);
		while(result.length() < Math.max(a.length(), b.length()))
			result = (res.compareTo(BigInteger.ZERO) >= 0 ? "0" : "1") + result;
//		System.out.println("Branch: " + ai + ' ' + bi + ' ' + result);
		return result.substring(0, Math.min(32, result.length()));
	}
}
