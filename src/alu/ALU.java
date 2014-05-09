package alu;

import java.lang.reflect.Constructor;
import java.util.HashMap;

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

	public HashMap<String, String> execute(String inputA, String inputB,
			ALUControl control) {
		Process p = new Process(inputA, inputB);
		String operation = control.getOperation();
		String className = "alu.command."
				+ operation.substring(0, 0).toUpperCase()
				+ operation.substring(1).toLowerCase() + "Command";
		try {
			Class<?> commandClass = Class.forName(className);
			Constructor<?> constructor = commandClass.getConstructor(String.class);
			Command result = (Command) constructor.newInstance(p);
			return result.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
