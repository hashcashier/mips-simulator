package memory;

import java.util.Hashtable;

public class Memory {
	Hashtable<Integer, String> memory;
	
	public Memory() {
		memory = new Hashtable<Integer, String>(); 
	}
	
	public String read(Integer address) {
		return memory.get(address);
	}
	
	public String write(Integer address, String contents) {
		return memory.put(address, contents);
	}
	
	public Hashtable<Integer, String> getAll() {
		return memory;
	}

}
