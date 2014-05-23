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
		String result = "";
		boolean zero = true;
		for (int i=SIZE-1; i>=0; i--) {
			int firstBit = inputA.charAt(i) - '0';
			int secondBit = inputB.charAt(i) - '0';
			int resultBit = firstBit & secondBit;
			if (resultBit == 1) {
				zero = false;
			}
			result = (resultBit + '0') + result;
		}
		return new Result(result, zero);
	}
	
	public Result nor() {
		Result or = this.or();
		return Operation.not(or.getResult());
	}

	public Result or() {
		String result = "";
		boolean zero = true;
		for (int i=SIZE-1; i>=0; i--) {
			int firstBit = inputA.charAt(i) - '0';
			int secondBit = inputB.charAt(i) - '0';
			int resultBit = firstBit | secondBit;
			if (resultBit == 1) {
				zero = false;
			}
			result = (resultBit + '0') + result;
		}
		return new Result(result, zero);
	}
	
	public Result slt() {
		String result = Operation.getZero();
		boolean zero = true;
		for (int i=0; i<SIZE; i++) {
			int firstBit = inputA.charAt(i) - '0';
			int secondBit = inputB.charAt(i) - '0';
			if (firstBit == secondBit) continue;
			else if (firstBit < secondBit) {
				result = Operation.getOne();
				zero = false;
				return new Result(result, zero);
			}
			else {
				return new Result(result, zero);
			}
		}
		return new Result(result, zero);
	}
	
	public Result sub() {
		String newInputB = Operation.twoComplement(inputB);
		Operation op = new Operation(inputA, newInputB);
		return op.add();
	}

	private static String twoComplement(String number) {
		String result = Operation.not(number).getResult();
		String one = Operation.getOne();
		Operation op = new Operation(result, one);
		return op.add().getResult();
	}

	private static Result not(String number) {
		String result = "";
		boolean zero = true;
		for (int i=SIZE-1; i>=0; i--) {
			int digit = number.charAt(i) - '0';
			digit = 1 - digit;
			result = (digit + '0') + result;
			if (digit == 1) {
				zero = false;
			}
		}
		return new Result(result, zero);
	}
	
	private static String getOne() {
		String one = "";
		for (int i=SIZE-1; i>=0; i--) {
			if (i == SIZE-1) one = "1" + one;
			else one = "0" + one;
		}
		return one;
	}
	
	private static String getZero() {
		String zero = "";
		for (int i=SIZE-1; i>=0; i--) {
			zero = "0" + zero;
		}
		return zero;
	}
}
