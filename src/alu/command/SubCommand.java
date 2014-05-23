package alu.command;

import alu.Operation;
import alu.Result;

public class SubCommand implements Command {

	private Operation o;
	
	public SubCommand(Operation o) {
		this.o = o;
	}
	
	public Result execute() {
		return o.sub();
	}	
}
