package alu.command;

import alu.Operation;
import alu.Result;

public class SrlCommand implements Command {

	private Operation o;
	
	public SrlCommand(Operation o) {
		this.o = o;
	}
	
	public Result execute() {
		return o.slt();
	}	
}
