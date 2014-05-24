package peripherals;

import java.math.BigInteger;

public class AndGate {
	public static String and(String A, String B) {
		String result = (new BigInteger(A, 2)).and(new BigInteger(B, 2)).toString(2);
		while(result.length() < Math.max(A.length(), B.length()))
			result = "0" + result;
		return result;
	}
}
