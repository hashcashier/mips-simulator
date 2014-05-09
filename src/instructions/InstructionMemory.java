package instructions;

import java.util.Hashtable;

public class InstructionMemory {
	private Instruction[] instructions;
	private int instructionCount;
	Hashtable<String, Integer> labelTable;
	
	
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
					if(instructionLine.contains(":")) {
						String[] instructionSplit = instructionLine.split(":");
						String label = instructionSplit[0];
						instructionLine = instructionSplit[1];
						if(labelTable.contains(label)) {
							
						}
					}
					if(instructionLine.matches("(\\w*)[:]")) {
						
					}
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
