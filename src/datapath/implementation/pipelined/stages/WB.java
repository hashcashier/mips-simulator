package datapath.implementation.pipelined.stages;

import memory.DataMemory;
import memory.InstructionMemory;
import peripherals.Mux;
import peripherals.ProgramCounter;
import registers.RegisterManager;
import registers.RegisterOperationNotAllowedException;
import control.ControlUnit;
import datapath.implementation.pipelined.registers.AbstractPipelineRegister;

public class WB extends Stage {

	@Override
	public void execute(DataMemory dm, InstructionMemory im,
			RegisterManager rm, ProgramCounter pc, AbstractPipelineRegister[] pr, ControlUnit cu) {
		AbstractPipelineRegister MEMWB = pr[3], EXMEM = pr[2], IDEX = pr[1], IFID = pr[0];
		//Write to register
		String RegWrite = MEMWB.getOutputValue("RegWrite");
		if(RegWrite.equals("1")) {
			String RD = MEMWB.getOutputValue("RegisterRd"), RT = MEMWB.getOutputValue("Rt"), RS = MEMWB.getOutputValue("Rs");
			String data = Mux.multiplex(new String[] {RT, RS}, MEMWB.getOutputValue("MemToReg"));
			try {
				rm.setRegisterValue(Integer.parseInt(RD, 2), data);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RegisterOperationNotAllowedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}