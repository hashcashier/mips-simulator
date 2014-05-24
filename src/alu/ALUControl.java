package alu;

public class ALUControl {
	private String operation;
	
	public ALUControl(String op, String funct) throws InvalidOperationException {
		// lw, sw, addi => 000
		// beq => 001
		// R-format => 010
		// andi => 100
		// ori => 101
		if (op == "000") this.operation = "0010";
		else if (op == "001") this.operation = "0110";
		else if (op == "010") {
			if (funct == "100000") this.operation = "0010";
			else if (funct == "100010") this.operation = "0110";
			else if (funct == "100100") this.operation = "0000";
			else if (funct == "100101") this.operation = "0000";
			else if (funct == "101010") this.operation = "0111";
			else throw new InvalidOperationException();
		}
		else if (op == "100") this.operation = "0000";
		else if (op == "101") this.operation = "0001";
		else throw new InvalidOperationException();
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String decodeOperation() throws InvalidOperationException {
		if (operation == "0000") return "And";
		else if (operation == "0001") return "Or";
		else if (operation == "0010") return "Add";
		else if (operation == "0110") return "Sub";
		else if (operation == "0111") return "Slt";
		else if (operation == "1100") return "Nor";
		else throw new InvalidOperationException();
	}
}
