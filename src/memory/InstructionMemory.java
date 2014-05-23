package memory;

public class InstructionMemory {
	Memory hardwareMemory;
	
	public InstructionMemory(Memory hardwareMemory, String[] instructions, int offset) {
		this.hardwareMemory = hardwareMemory;
		for(int i = 0; i < instructions.length; i++) {
			int address = i + offset;
			hardwareMemory.write(address, instructions[i]);
		}
	}
}
