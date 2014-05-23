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

	public String memoryAction(String memWrite, String memRead, String address,
			String writeData) throws AddressMoreThan32BitsException,
			WriteDataMoreThan32BitsException {
		int read = Integer.parseInt(memRead, 2);
		int write = Integer.parseInt(memWrite, 2);
		int addressKey = Integer.parseInt(address, 2);
		if (address.length() > 32)
			throw new AddressMoreThan32BitsException();
		if (writeData.length() > 32)
			throw new WriteDataMoreThan32BitsException();
		if (read == 1 && write == 0) {
			return hardwareMemory.read(addressKey);
		}
		if (write == 1 && read == 0) {
			hardwareMemory.write(addressKey, writeData);
		}
		return null;
	}

	public Hashtable<Integer, String> getMemoryContents() {
		Hashtable<Integer, String> result = new Hashtable<Integer, String>();
		for (Entry<Integer, String> entry : hardwareMemory.getAll().entrySet())
			if ((entry.getKey() & Assembler.DM_OFFSET) == Assembler.DM_OFFSET)
				result.put(entry.getKey(), entry.getValue());
		return result;
	}

}
