package alu;

import java.util.HashMap;

import alu.command.AddCommand;

public class ALU {
	
	private String inputA, inputB;
	private ALUControl control;
	
	public ALU(String inputA, String inputB, ALUControl control) {
		this.inputA = inputA;
		this.inputB = inputB;
		this.control = control;
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
	
	public ALUControl getControl() {
		return control;
	}
	
	public void setControl(ALUControl control) {
		this.control = control;
	}
	
	public HashMap<String, String> execute() {
		Operation o = new Operation(inputA, inputB);
		String op = control.getOperation();
		if (op == "add") {
			AddCommand c = new AddCommand(o);
			return c.execute();
		}
		return null;
	}
}
