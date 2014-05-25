package peripherals;

import java.math.BigInteger;

import alu.Operation;

public class LeftShifter {
	public static String shiftLeft(String input, int degree, int size) {
		BigInteger numerical = Operation.sign(input).shiftLeft(degree);
//		System.out.println("SHIFTER: " + input + " -> " + Operation.wellFormedWord(numerical, size) + ":" + numerical);
		return Operation.wellFormedWord(numerical, size);
	}
}
