package memory;

import java.util.Hashtable;

public class Memory {
	Hashtable<Integer, String> memory;
	
	public Memory() {
		memory = new Hashtable<Integer, String>(); 
	}
	
	public String read(Integer address) {
		String read = memory.get(address);
		if(read == null)
			return "00000000000000000000000000000000";
		return read;
	}
	
	public String write(Integer address, String contents) {
		return memory.put(address, contents);
	}
	
	public Hashtable<Integer, String> getAll() {
		return memory;
	}

}
