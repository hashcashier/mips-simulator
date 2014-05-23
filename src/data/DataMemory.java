package data;

import java.util.Hashtable;

public class DataMemory {
	Hashtable<Integer, String> memory;

	public DataMemory(String[] data) {
		memory = new Hashtable<Integer, String>();
		for(int i = 0; i < data.length; i++) {
			int address = i + (1<<31);
			memory.put(address, data[i]);
		}
	}
}
