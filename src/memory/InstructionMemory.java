package memory;

import java.util.Hashtable;
import java.util.Map.Entry;

import assembly.Assembler;

public class InstructionMemory {
	Memory hardwareMemory;

	public InstructionMemory(Memory hardwareMemory, String[] instructions,
			int offset) {
		this.hardwareMemory = hardwareMemory;
		for (int i = 0; i < instructions.length; i++) {
			long address = i + offset;
			hardwareMemory.write(address, instructions[i]);
		}
	}

	public Hashtable<Long, String> getMemoryContents() {
		Hashtable<Long, String> result = new Hashtable<Long, String>();
		for(Entry<Long, String> entry : hardwareMemory.getAll().entrySet())
			if((entry.getKey() & Assembler.DM_OFFSET) == 0)
				result.put(entry.getKey(), entry.getValue());
		return result;
	}
}
