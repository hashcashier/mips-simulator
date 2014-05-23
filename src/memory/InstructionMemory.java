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
			int address = i + offset;
			hardwareMemory.write(address, instructions[i]);
		}
	}

	public String instructionRead(String address)
			throws AddressNotFoundException {
		int addressKey = Integer.parseInt(address, 2);
		if (address.length() > 32)
			throw new AddressNotFoundException();
		return hardwareMemory.read(addressKey);

	}

	public Hashtable<Integer, String> getMemoryContents() {
		Hashtable<Integer, String> result = new Hashtable<Integer, String>();
		for (Entry<Integer, String> entry : hardwareMemory.getAll().entrySet())
			if ((entry.getKey() & Assembler.DM_OFFSET) == 0)
				result.put(entry.getKey(), entry.getValue());
		return result;
	}
}
