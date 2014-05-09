package instructions;

public class InstructionMemory {
	private Instruction[] instructions;
	private int instructionCount;
	
	public InstructionMemory(String[] instructionLines) {
		boolean reading = false;
		int lines = instructionLines.length;
		instructions = new Instruction[lines];
		instructionCount = 0;
		for(int i = 0; i < lines; i++) {
			String instructionLine = instructionLines[i].trim();
			if(instructionLine.contains(".text")) {
				reading = true;
				instructionLine.replace(".text", "");
			} else if(instructionLine.contains(".data")) {
				reading = false;
			}
			
			if(reading) {
				try {
					instructions[instructionCount++] = new Instruction(instructionLine);
				} catch (UnkownInstructionException e) {
					// Throw more stuff
				}
			}
		}
	}
	
	public int getInstructionCount() {
		return instructionCount;
	}
	
	public Instruction getInstruction(int number) {
		return instructions[number];
	}
}
