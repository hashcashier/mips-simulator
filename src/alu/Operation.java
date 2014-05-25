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
	
	public static String rev(String input) {
		String tmp = "";
		for(int i = 0; i < input.length(); i++)
			if(input.charAt(i) == '0')
				tmp += '1';
			else
				tmp += '0';
		return tmp;
	}
	
	public static BigInteger sign(String input) {
		if (input.length() == SIZE && input.charAt(0) == '1') {
			String tmp = rev(input);
			BigInteger val = new BigInteger(tmp, 2).add(BigInteger.ONE);
			return val.negate();
		}
		return new BigInteger(input, 2);
	}
	
	public static String wellFormedWord(BigInteger input) {
		String res = Long.toBinaryString(input.longValue());
		while (res.length() < SIZE)
			res = (input.compareTo(BigInteger.ZERO) >= 0 ? "0" : "1") + res;
		while (res.length() > SIZE)
			res = res.substring(1);
		return res;
	}

	public static String wellFormedWord(BigInteger input, int size) {
		String res = Long.toBinaryString(input.longValue());
		while (res.length() < size)
			res = (input.compareTo(BigInteger.ZERO) >= 0 ? "0" : "1") + res;
		while (res.length() > size)
			res = res.substring(1);
//		System.out.println("Well forming: " + input + " -> " + res);
		return res;
	}

	private Result retRes(BigInteger c) {
		return new Result(wellFormedWord(c), c.compareTo(BigInteger.ZERO) == 0);
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
