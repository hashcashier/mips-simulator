package memory;

import java.util.Hashtable;
import java.util.Map.Entry;

import assembly.Assembler;

public class DataMemory {
	Memory hardwareMemory;

	public DataMemory(Memory hardwareMemory, String[] data) {
		this.hardwareMemory = hardwareMemory;
		for (int i = 0; i < data.length; i++) {
			long address = 4*i + Assembler.DM_OFFSET;
			hardwareMemory.write(address, data[i]);
		}
	}

	public String memoryAction(String memWrite, String memRead, String address,
			String writeData) throws AddressNotFoundException,
			WriteDataMoreThan32BitsException {
		int read = memRead == "" ? 0 : Integer.parseInt(memRead, 2);
		int write = memWrite == "" ? 0 : Integer.parseInt(memWrite, 2);
		long addressKey = Long.parseLong(address, 2);
		if (address.length() > 32)
			throw new AddressNotFoundException();
		if (writeData.length() > 32)
			throw new WriteDataMoreThan32BitsException();
		if (write == 1) {
			hardwareMemory.write(addressKey, writeData);
		}
		if (read == 1) {
			return hardwareMemory.read(addressKey);
		}
		return null;
	}

	public Hashtable<Long, String> getMemoryContents() {
		Hashtable<Long, String> result = new Hashtable<Long, String>();
		for (Entry<Long, String> entry : hardwareMemory.getAll().entrySet())
			if ((entry.getKey() & Assembler.DM_OFFSET) == Assembler.DM_OFFSET)
				result.put(entry.getKey(), entry.getValue());
		return result;
	}

}
