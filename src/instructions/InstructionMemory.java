package instructions;

import instructions.isa.Instruction;

public class InstructionMemory {
	private Instruction[] instructions;
	private int instructionCount;
	
	public InstructionMemory(String[] instructionLines) {
		int lines = instructionLines.length;
		instructions = new Instruction[lines];
		instructionCount = 0;
		for(int i = 0; i < lines; i++) {
			String instructionLine = instructionLines[i].trim();
			instructionLine.replace(".text", "");
			
			try {
				String[] instruction = instructionLine.split(" ");
				String name = instruction[0].trim(), params = "";
				for(int j = 1; j < instruction.length; j++)
					params += instruction[j];
				instructions[instructionCount++] = InstructionFactory.createInstruction(name, params);
			} catch (UnkownInstructionException e) {
				// Throw more stuff
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
