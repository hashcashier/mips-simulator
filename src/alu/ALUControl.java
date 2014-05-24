package alu;

public class ALUControl {
	private String operation;
	
	public ALUControl(String op, String funct) throws InvalidOperationException {
		if (op.equals("000")) this.operation = "0010";
		else if (op.equals("001")) this.operation = "0110";
		else if (op.equals("010")) this.operation = "0001";
		else if (op.equals("011")) this.operation = "0000";
		else if (op.equals("100")) {
			if (funct.equals("100000")) this.operation = "0010";
			else if (funct.equals("100010")) this.operation = "0110";
			else if (funct.equals("100100")) this.operation = "0000";
			else if (funct.equals("100101")) this.operation = "0001";
			else if (funct.equals("101010")) this.operation = "0111";
			else if (funct.equals("000000")) this.operation = "0011";
			else if (funct.equals("000010")) this.operation = "0100";
			else  {
				System.out.println(funct);
				throw new InvalidOperationException();
			}
		}
		else throw new InvalidOperationException();
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String decodeOperation() throws InvalidOperationException {
		if (operation.equals("0000")) return "And";
		else if (operation.equals("0001")) return "Or";
		else if (operation.equals("0010")) return "Add";
		else if (operation.equals("0011")) return "Sll";
		else if (operation.equals("0100")) return "Srl";
		else if (operation.equals("0110")) return "Sub";
		else if (operation.equals("0111")) return "Slt";
		else if (operation.equals("1100")) return "Nor";
		else throw new InvalidOperationException();
	}
}
