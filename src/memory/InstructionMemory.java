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
			long address = 4*i + offset;
			hardwareMemory.write(address, instructions[i]);
		}
	}

	public String instructionRead(String address)
			throws AddressNotFoundException {
		long addressKey = Long.parseLong(address, 2);
		if (address.length() > 32)
			throw new AddressNotFoundException();
		return hardwareMemory.read(addressKey);

	}

	public Hashtable<Long, String> getMemoryContents() {
		Hashtable<Long, String> result = new Hashtable<Long, String>();
		for (Entry<Long, String> entry : hardwareMemory.getAll().entrySet())
			if ((entry.getKey() & Assembler.DM_OFFSET) == 0)
				result.put(entry.getKey(), entry.getValue());
		return result;
	}
	
	public Long getLastInstructionAddress() {
		Hashtable<Long, String> instructions = getMemoryContents();
		Long res = (long) 0;
		for(Entry<Long, String> entry : instructions.entrySet())
			res = Math.max(res, entry.getKey());
		return res;
	}
}
