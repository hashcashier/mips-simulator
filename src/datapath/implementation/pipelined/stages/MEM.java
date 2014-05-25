package datapath.implementation.pipelined.stages;

import memory.AddressNotFoundException;
import memory.DataMemory;
import memory.InstructionMemory;
import memory.WriteDataMoreThan32BitsException;
import peripherals.ProgramCounter;
import registers.RegisterManager;
import control.ControlUnit;
import datapath.implementation.pipelined.registers.AbstractPipelineRegister;

public class MEM extends Stage {

	@Override
	public void execute(DataMemory dm, InstructionMemory im,
			RegisterManager rm, ProgramCounter pc, AbstractPipelineRegister[] pr, ControlUnit cu) {
		AbstractPipelineRegister MEMWB = pr[3], EXMEM = pr[2], IDEX = pr[1], IFID = pr[0];
		// Forward control signals "MemToReg", "RegWrite", "RegisterRd", "PC"
		MEMWB.setInputValue("MemToReg", EXMEM.getOutputValue("MemToReg"));
		MEMWB.setInputValue("RegWrite", EXMEM.getOutputValue("RegWrite"));
		MEMWB.setInputValue("RegisterRd", EXMEM.getOutputValue("RegisterRd"));
		MEMWB.setInputValue("PC", EXMEM.getOutputValue("PC"));
		// Fetch/Write Data
		String address = EXMEM.getOutputValue("Address"), writeData = EXMEM.getOutputValue("Rt");
		String memWrite = EXMEM.getOutputValue("MemWrite"), memRead = EXMEM.getOutputValue("MemRead");
		String readRes = RegisterManager.zeros32();
		try {
			if(memRead.equals("1"))
				readRes = dm.memoryAction(memWrite, memRead, address, writeData);
			else
				dm.memoryAction(memWrite, memRead, address, writeData);
		} catch (AddressNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteDataMoreThan32BitsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Forward Rt
		MEMWB.setInputValue("Rt", address);
		// Set RS
		MEMWB.setInputValue("Rs", readRes);
	}

}