package memory;

import java.util.Hashtable;

public class Memory {
	Hashtable<Long, String> memory;
	
	public Memory() {
		memory = new Hashtable<Long, String>(); 
	}
	
	public String read(Long address) {
		String read = memory.get(address);
		if(read == null)
			return "00000000000000000000000000000000";
		return read;
	}
	
	public String write(Long address, String contents) {
		return memory.put(address, contents);
	}
	
	public Hashtable<Long, String> getAll() {
		return memory;
	}

}
