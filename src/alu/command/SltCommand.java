package alu.command;

import alu.Operation;
import alu.Result;

public class SltCommand implements Command {

	private Operation o;
	
	public SltCommand(Operation o) {
		this.o = o;
	}
	
	public Result execute() {
		return o.slt();
	}	
}
