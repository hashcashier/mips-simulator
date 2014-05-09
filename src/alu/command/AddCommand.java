package alu.command;

import java.util.HashMap;

import alu.Process;

public class AddCommand implements Command {

	private Process o;
	
	public AddCommand(Process o) {
		this.o = o;
	}
	
	public HashMap<String, String> execute() {
		return o.add();
	}	
}
