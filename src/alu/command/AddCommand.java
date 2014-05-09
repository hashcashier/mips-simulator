package alu.command;

import java.util.HashMap;

import alu.Operation;

public class AddCommand implements Command {

	private Operation o;
	
	public AddCommand(Operation o) {
		this.o = o;
	}
	
	public HashMap<String, String> execute() {
		return o.add();
	}	
}
