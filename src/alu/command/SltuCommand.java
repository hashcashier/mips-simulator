package alu.command;

import alu.Operation;
import alu.Result;

public class SltuCommand implements Command {

	private Operation o;
	
	public SltuCommand(Operation o) {
		this.o = o;
	}
	
	public Result execute() {
		return o.sltu();
	}	
}
