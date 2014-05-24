package datapath.implementation.pipelined.stages;

import control.ControlUnit;
import memory.DataMemory;
import memory.InstructionMemory;
import peripherals.ProgramCounter;
import registers.RegisterManager;
import datapath.implementation.pipelined.registers.AbstractPipelineRegister;

public abstract class Stage {
	public abstract void execute(DataMemory dm, InstructionMemory im, RegisterManager rm, ProgramCounter pc, AbstractPipelineRegister[] pr, ControlUnit cu);
}
