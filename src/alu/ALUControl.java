package alu;

public class ALUControl {
	private String operation;
	
	public ALUControl(String operation) {
		try {
			if (operation == "0000") operation = "And";
			else if (operation == "0001") operation = "Or";
			else if (operation == "0010") operation = "Add";
			else if (operation == "0110") operation = "Sub";
			else if (operation == "0111") operation = "Slt";
			else if (operation == "1100") operation = "Nor";
			else throw new InvalidOperationException();
			this.operation = operation;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}
