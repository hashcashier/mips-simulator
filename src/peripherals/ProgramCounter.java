package peripherals;

public class ProgramCounter {
	private int counter;
	
	public ProgramCounter() {
		counter = 0;
	}
	
	public void increment() {
		counter += 4;
	}
	
	public void setCounter(int count) {
		counter = count;
	}
	
	public int getCounter() {
		return counter;
	}
}
