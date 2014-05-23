package instructions;

import java.util.Hashtable;


public class InstructionMemory {
	Hashtable<Integer, String> memory;
	public InstructionMemory(String[] instructions, int offset) {
		memory = new Hashtable<Integer, String>();
		for(int i = 0; i < instructions.length; i++) {
			int address = i + offset;
			memory.put(address, instructions[i]);
		}
	}
}
