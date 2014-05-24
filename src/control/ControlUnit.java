package control;

public class ControlUnit {
	private String ALUOp, RegDst, ALUSrc, Branch, Jump, JumpReg,
			PCSrc, RegWrite, MemWrite, MemRead, MemToReg;
	
	public ControlUnit() {
		reset();
	}
	
	public void setInput(String input) {
		String opcode = input.substring(0, 6), funct = input.substring(26, 32);
		System.out.println("Control Unit: " + input + " " + opcode + " " + funct);
		reset();
		if(opcode.equals("000000")) {//rtype
			if(funct.equals("001000")) {
				JumpReg = "1";
			} else {
				RegDst = "1";
				RegWrite = "1";
				ALUOp = "100";
			}
		} else if(opcode.equals("100011")) {//lw
			ALUSrc = "1";
			MemToReg = "1";
			RegWrite = "1";
			MemRead = "1";
		} else if(opcode.equals("101011")) {//sw
			ALUSrc = "1";
			MemWrite = "1";
		} else if(opcode.equals("000100")) {//beq
			Branch = "1";
			ALUOp = "001";
		} else if(opcode.equals("001000") || opcode.equals("001100") || opcode.equals("001101")) {
			// addi, andi, ori q
			System.out.println("MATCHED");
			ALUSrc = "1";
			RegWrite = "1";
			if(opcode.equals("001000"))
				ALUOp = "000";
			else if(opcode.equals("001100"))
				ALUOp = "011";
			else if(opcode.equals("001101"))
				ALUOp = "010";
		} else if(opcode.equals("000010") || opcode.equals("000011")) { // j, jal
			Jump = "1";
		} 
	}
	
	private void reset() {
		ALUOp = "000";
		RegDst = "0";
		ALUSrc = "0";
		Branch = "0";
		Jump = "0";
		JumpReg = "0";
		PCSrc = "0";
		RegWrite = "0";
		MemWrite = "0";
		MemRead = "0";
		MemToReg = "0";
	}

	public synchronized String getALUOp() {
		return ALUOp;
	}

	public synchronized String getRegDst() {
		return RegDst;
	}

	public synchronized String getALUSrc() {
		return ALUSrc;
	}

	public synchronized String getBranch() {
		return Branch;
	}

	public synchronized String getJump() {
		return Jump;
	}

	public synchronized String getJumpReg() {
		return JumpReg;
	}

	public synchronized String getPCSrc() {
		return PCSrc;
	}

	public synchronized String getRegWrite() {
		return RegWrite;
	}

	public synchronized String getMemWrite() {
		return MemWrite;
	}

	public synchronized String getMemRead() {
		return MemRead;
	}

	public synchronized String getMemToReg() {
		return MemToReg;
	}
}
