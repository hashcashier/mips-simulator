package alu.command;

import alu.Operation;
import alu.Result;

public class OrCommand implements Command {

	private Operation o;
	
	public OrCommand(Operation o) {
		this.o = o;
	}
	
	public Result execute() {
		return o.or();
	}	
}
