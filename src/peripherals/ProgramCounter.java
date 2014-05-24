package peripherals;

import assembly.Assembler;

public class ProgramCounter {
	private long counter;
	
	public ProgramCounter() {
		counter = 0;
	}
	
	public void setCounter(long count) {
		counter = count;
	}
	
	public long getCounter() {
		return counter;
	}
	
	public String getCounterBits() {
		return Assembler.assembleIntegral(Long.toString(counter), 32);
	}
}
