package alu;

public class Result {
	private String result;
	private boolean zero;
	
	public Result() {
		
	}
	
	public Result(String result, boolean zero) {
		this.result = result;
		this.zero = zero;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isZero() {
		return zero;
	}

	public void setZero(boolean zero) {
		this.zero = zero;
	}
}
