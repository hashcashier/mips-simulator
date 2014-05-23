package memory;

import java.util.Hashtable;
import java.util.Map.Entry;

import assembly.Assembler;

public class DataMemory {
	Memory hardwareMemory;

	public DataMemory(Memory hardwareMemory, String[] data) {
		this.hardwareMemory = hardwareMemory;
		for (int i = 0; i < data.length; i++) {
			int address = i + Assembler.DM_OFFSET;
			hardwareMemory.write(address, data[i]);
		}
	}

	public Hashtable<Integer, String> getMemoryContents() {
		Hashtable<Integer, String> result = new Hashtable<Integer, String>();
		for(Entry<Integer, String> entry : hardwareMemory.getAll().entrySet())
			if((entry.getKey() & Assembler.DM_OFFSET) == Assembler.DM_OFFSET)
				result.put(entry.getKey(), entry.getValue());
		return result;
	}
}
