package alu;

import instructions.isa.Instruction;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import alu.command.AddCommand;
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
	
	public HashMap<String, String> execute() {
		Class<?> commandClass = null;
		Constructor<?> constructor = null;
		Command result = null;
		Operation o = new Operation(inputA, inputB);
		String op = control.getOperation();
		String className = "alu.command." + op.substring(0, 0).toUpperCase() + op.substring(1).toLowerCase() + "Command";
		try {
			commandClass = Class.forName(className);
			constructor = commandClass.getConstructor(String.class);
			result = (Command) constructor.newInstance(o);
			return result.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
