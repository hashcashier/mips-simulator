package alu.command;

import alu.Operation;
import alu.Result;

public class SllCommand implements Command {

	private Operation o;
	
	public SllCommand(Operation o) {
		this.o = o;
	}
	
	public Result execute() {
		return o.slt();
	}	
}
