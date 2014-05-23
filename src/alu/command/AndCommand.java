package alu.command;

import alu.Operation;
import alu.Result;

public class AndCommand implements Command {

	private Operation o;
	
	public AndCommand(Operation o) {
		this.o = o;
	}
	
	public Result execute() {
		return o.and();
	}	
}
