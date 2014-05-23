package alu.command;

import alu.Operation;
import alu.Result;

public class NorCommand implements Command {

	private Operation o;
	
	public NorCommand(Operation o) {
		this.o = o;
	}
	
	public Result execute() {
		return o.nor();
	}	
}
