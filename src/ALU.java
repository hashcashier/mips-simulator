public class ALU {
	
	private String inputA, inputB;
	
	public ALU(String inputA, String inputB, String operation) {
		this.inputA = inputA;
		this.inputB = inputB;
		this.operation = operation;
	}

	public String getInputA() {
		return inputA;
	}

	public void setInputA(String inputA) {
		this.inputA = inputA;
	}

	public String getInputB() {
		return inputB;
	}

	public void setInputB(String inputB) {
		this.inputB = inputB;
	}
	
	public String execute(String operation) {
		if (operation == "ADD") {
			
		}
		else if (operation == "ADDI") {
			
		}
		else if (operation == "SUB") {
			
		}
		return "";
	}
}