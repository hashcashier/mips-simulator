package datapath.implementation.pipelined.stages;

import memory.DataMemory;
import memory.InstructionMemory;
import peripherals.Adder;
import peripherals.LeftShifter;
import peripherals.Mux;
import peripherals.ProgramCounter;
import registers.RegisterManager;
import alu.ALU;
import alu.ALUControl;
import alu.InvalidOperationException;
import alu.Operation;
import alu.Result;
import control.ControlUnit;
import datapath.implementation.pipelined.registers.AbstractPipelineRegister;

public class EX extends Stage {

	@Override
	public void execute(DataMemory dm, InstructionMemory im,
			RegisterManager rm, ProgramCounter pc, AbstractPipelineRegister[] pr, ControlUnit cu) {
		AbstractPipelineRegister MEMWB = pr[3], EXMEM = pr[2], IDEX = pr[1], IFID = pr[0];
		// Calculate Branch Target
		String BranchDiff = LeftShifter.shiftLeft(
				IDEX.getOutputValue("Immediate"), 2, 32), BranchTarget = Adder
				.add(BranchDiff, IDEX.getOutputValue("PC"));
		EXMEM.setInputValue("BranchAddress", BranchTarget);
		EXMEM.setInputValue("PC", IDEX.getOutputValue("PC"));

		try {
			// Set ALU Control
			ALUControl ac = new ALUControl(IDEX.getOutputValue("ALUOp"), IDEX.getOutputValue("Immediate").substring(26, 32));
			// Calculate ALU Result
			String input2 = Mux.multiplex(new String[] {IDEX.getOutputValue("Rt"), IDEX.getOutputValue("Immediate")}, IDEX.getOutputValue("ALUSrc"));
			String input1 = IDEX.getOutputValue("Rs");
			ALU alu = new ALU(input1, input2, ac);
			Result res = alu.execute();
			EXMEM.setInputValue("Zero", res.isZero() ? "0" : "1");
//			System.out.println("RES: " + res.getResult());
			EXMEM.setInputValue("Address", res.getResult());
		} catch (InvalidOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Calculate RegDst
		String RD = Mux.multiplex(new String[] {IDEX.getOutputValue("RegisterRt"), IDEX.getOutputValue("RegisterRd")}, IDEX.getOutputValue("RegDst"));
		EXMEM.setInputValue("RegisterRd", RD);
		// Set Rt
		EXMEM.setInputValue("Rt", IDEX.getOutputValue("Rt"));
		// Forward control signals "Branch", "MemWrite", "MemRead", "MemToReg", "RegWrite"
		EXMEM.setInputValue("Branch", IDEX.getOutputValue("Branch"));
		EXMEM.setInputValue("MemWrite", IDEX.getOutputValue("MemWrite"));
		EXMEM.setInputValue("MemRead", IDEX.getOutputValue("MemRead"));
		EXMEM.setInputValue("MemToReg", IDEX.getOutputValue("MemToReg"));
		EXMEM.setInputValue("RegWrite", IDEX.getOutputValue("RegWrite"));
	}

}
