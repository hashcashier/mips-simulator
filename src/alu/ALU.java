package alu;

import java.lang.reflect.Constructor;
import alu.command.Command;

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

	public Result execute() {
		Operation op = new Operation(inputA, inputB);
		// It is assumed that the operator will be in words, i.e: Add
		String operator = control.getOperation();
		String className = "alu.command."
				+ operator.substring(0, 0).toUpperCase()
				+ operator.substring(1).toLowerCase() + "Command";
		try {
			Class<?> commandClass = Class.forName(className);
			Constructor<?> constructor = commandClass.getConstructor(String.class);
			Command result = (Command) constructor.newInstance(op);
			return result.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
