package datapath.implementation.pipelined.stages;

import memory.AddressNotFoundException;
import memory.DataMemory;
import memory.InstructionMemory;
import peripherals.Adder;
import peripherals.AndGate;
import peripherals.Mux;
import peripherals.ProgramCounter;
import registers.RegisterManager;
import control.ControlUnit;
import datapath.implementation.pipelined.registers.AbstractPipelineRegister;

public class IF extends Stage {

	@Override
	public void execute(DataMemory dm, InstructionMemory im,
			RegisterManager rm, ProgramCounter pc, AbstractPipelineRegister[] pr, ControlUnit cu) {
		AbstractPipelineRegister MEMWB = pr[3], EXMEM = pr[2], IDEX = pr[1], IFID = pr[0];
		// Calculate Branch
		String branch = AndGate.and(EXMEM.getOutputValue("Zero"), EXMEM.getOutputValue("Branch"));
		String PC4 = Adder.add("100", pc.getCounterBits());
		String PCBranchResult = Mux.multiplex(new String[] {PC4, EXMEM.getOutputValue("BranchAddress")}, branch);
		// Calculate Jump
		String jump = IDEX.getOutputValue("JumpReg") + cu.getJump();
		String jumpTarget = PC4.substring(0, 4) + IFID.getOutputValue("Instruction").substring(6, 32) + "00"; 
		String PCJumpResult = Mux.multiplex(new String [] {PCBranchResult, jumpTarget, IDEX.getOutputValue("Rs")}, jump);
		System.out.println("JUMP RESULT:" + PCJumpResult);
		// Fetch Instruction
		try {
			IFID.setInputValue("Instruction", im.instructionRead(pc.getCounterBits()));
		} catch (AddressNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IFID.setInputValue("PC", PC4);
		// Update Counter
		pc.setCounter(Long.parseLong(PCJumpResult, 2));
	}

}
