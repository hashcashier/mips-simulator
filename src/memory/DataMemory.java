package memory;

public class DataMemory {
	Memory hardwareMemory;

	public DataMemory(Memory hardwareMemory, String[] data) {
		this.hardwareMemory = hardwareMemory;
		for(int i = 0; i < data.length; i++) {
			int address = i + (1<<31);
			hardwareMemory.write(address, data[i]);
		}
	}
}
