package instructions;


public class InstructionMemory {
	private Instruction[] instructions;
	private int instructionCount;
	
	public InstructionMemory(String[] instructionLines) {
		int lines = instructionLines.length;
		instructions = new Instruction[lines];
		instructionCount = 0;
		
	}
	
	public int getInstructionCount() {
		return instructionCount;
	}
	
	public Instruction getInstruction(int number) {
		return instructions[number];
	}
	
}
