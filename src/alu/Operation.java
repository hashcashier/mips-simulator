package alu;

public class Operation {
	public String inputA, inputB;
	private static final int SIZE = 32;
	
	public Operation(String inputA, String inputB) {
		this.inputA = inputA;
		this.inputB = inputB;
	}
	
	public Result add() {
		int overflow = 0;
		String result = "";
		boolean zero = true;
		for (int i=SIZE-1; i>=0; i--) {
			int firstBit = inputA.charAt(i) - '0';
			int secondBit = inputB.charAt(i) - '0';
			int resultBit = firstBit + secondBit + overflow;
			overflow = 0;
			if (resultBit > 1) {
				resultBit -= 2;
				overflow = 1;
			}
			if (resultBit == 1) {
				zero = false;
			}
			result = (resultBit + '0') + result;
		}
		return new Result(result, zero);
	}
	
	public Result and() {
		return null;
	}
	
	public Result nor() {
		return null;
	}
	
	public Result or() {
		return null;
	}
	
	public Result slt() {
		return null;
	}
	
	public Result sub() {
		return null;
	}
}
