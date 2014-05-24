package datapath.implementation.pipelined.stages;

import memory.DataMemory;
import memory.InstructionMemory;
import peripherals.ProgramCounter;
import peripherals.SignExtender;
import registers.RegisterManager;
import control.ControlUnit;
import datapath.implementation.pipelined.registers.AbstractPipelineRegister;

public class ID extends Stage {

	@Override
	public void execute(DataMemory dm, InstructionMemory im,
			RegisterManager rm, ProgramCounter pc, AbstractPipelineRegister[] pr, ControlUnit cu) {
		AbstractPipelineRegister MEMWB = pr[3], EXMEM = pr[2], IDEX = pr[1], IFID = pr[0];
		String Instruction = IFID.getOutputValue("Instruction"), PC = IFID.getOutputValue("PC");
		// Update PC
		IDEX.setInputValue("PC", PC);
		// Set Control Signals "RegWrite", "RegDst", "ALUOp", "ALUSrc", "Branch", "MemWrite", "MemRead", "MemToReg", "JumpReg"
		cu.setInput(Instruction);
		IDEX.setInputValue("RegWrite", cu.getRegWrite());
		IDEX.setInputValue("RegDst", cu.getRegDst());
		IDEX.setInputValue("ALUOp", cu.getALUOp());
		IDEX.setInputValue("ALUSrc", cu.getALUSrc());
		IDEX.setInputValue("Branch", cu.getBranch());
		IDEX.setInputValue("MemWrite", cu.getMemWrite());
		IDEX.setInputValue("MemRead", cu.getMemRead());
		IDEX.setInputValue("MemToReg", cu.getMemToReg());
		IDEX.setInputValue("JumpReg", cu.getJumpReg());
		// Read Registers
		String RS = Instruction.substring(6, 11), RT = Instruction.substring(11, 16), RD = Instruction.substring(16, 21);
		String read1 = rm.getRegisterValue(Integer.parseInt(RS, 2)), read2 = rm.getRegisterValue(Integer.parseInt(RT, 2));
		IDEX.setInputValue("Rs", read1);
		IDEX.setInputValue("Rt", read2);
		// Immediate Operand
		String Immediate = Instruction.substring(16, 32);
		Immediate = SignExtender.signExtend(Immediate);
		IDEX.setInputValue("Immediate", Immediate);
		// RS/RT
		IDEX.setInputValue("RegisterRt", RT);
		IDEX.setInputValue("RegisterRd", RD);
		
	}

}
