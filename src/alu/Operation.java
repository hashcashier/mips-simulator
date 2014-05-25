package alu;

import java.math.BigInteger;

public class Operation {
	public String inputA, inputB;
	public BigInteger A, B;
	private static final int SIZE = 32;

	public Operation(String inputA, String inputB) {
		this.inputA = inputA;
		this.inputB = inputB;
		A = sign(inputA);
		B = sign(inputB);
	}

	public static BigInteger sign(String input) {
		if (input.length() == SIZE && input.charAt(0) == '1') {
			System.out.println("SigN: " + input + ' '
					+ new BigInteger('-' + input.substring(1), 2));
			return new BigInteger(input, 2).subtract(BigInteger.ONE).not();
		}
		System.out.println("Sign: " + input + ' ' + new BigInteger(input, 2).toString(2));
		return new BigInteger(input, 2);
	}

	private Result retRes(BigInteger c) {
		boolean zero = c.compareTo(BigInteger.ZERO) == 0;

		String result = c.toString(2);
		if(result.charAt(0) == '-')
			result = result.substring(1);
		while (result.length() < SIZE)
			result = (c.compareTo(BigInteger.ZERO) >= 0 ? "0" : "1") + result;
		
		System.out.println("RAW RES: " + result);
		return new Result(result.substring(0, SIZE), zero);
	}

	public Result add() {
		return retRes(A.add(B));
	}

	public Result and() {
		return retRes(A.and(B));
	}

	public Result nor() {
		return retRes(A.or(B).not());
	}

	public Result or() {
		return retRes(A.or(B));
	}

	public Result slt() {
		return retRes(A.compareTo(B) < 0 ? BigInteger.ONE : BigInteger.ZERO);
	}

	public Result sltu() {
		BigInteger ua = new BigInteger(inputA, 2), ub = new BigInteger(inputB,
				2);
		return retRes(ua.compareTo(ub) < 0 ? BigInteger.ONE : BigInteger.ZERO);
	}

	public Result sub() {
		return retRes(A.subtract(B));
	}

	public Result sll() {
		return retRes(A.shiftLeft(B.intValue()));
	}

	public Result srl() {
		return retRes(A.shiftRight(B.intValue()));
	}
}
