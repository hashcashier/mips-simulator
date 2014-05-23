package alu.command;

import alu.Operation;
import alu.Result;

public class AddCommand implements Command {

	private Operation o;
	
	public AddCommand(Operation o) {
		this.o = o;
	}
	
	public Result execute() {
		return o.add();
	}	
}
