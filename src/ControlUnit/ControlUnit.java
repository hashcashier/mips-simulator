package ControlUnit;

import java.util.Hashtable;

public class ControlUnit {
	private static String opCode;
	private static String RegDst = "0";
	private static String Jump = "0";
	private static String Branch = "0";
	private static String MemRead = "0";
	private static String MemToReg = "0";
	private static String ALUOp = "00";
	private static String MemWrite = "0";
	private static String ALUSrc = "0";
	private static String RegWrite = "0";
	private static String Zero = "0";
	private static String PCSrc = "0";	
	
	private final static String X = "0";
	
	public static String getRegDst() {
		return RegDst;
	}

	public static void setRegDst(String regDst) {
		RegDst = regDst;
	}

	public static String getBranch() {
		return Branch;
	}

	public static void setBranch(String branch) {
		Branch = branch;
	}

	public static String getMemRead() {
		return MemRead;
	}

	public static void setMemRead(String memRead) {
		MemRead = memRead;
	}

	public static String getMemToReg() {
		return MemToReg;
	}

	public static void setMemToReg(String memToReg) {
		MemToReg = memToReg;
	}

	public static String getALUOp() {
		return ALUOp;
	}

	public static void setALUOp(String aLUOp) {
		ALUOp = aLUOp;
	}

	public static String getMemWrite() {
		return MemWrite;
	}

	public static void setMemWrite(String memWrite) {
		MemWrite = memWrite;
	}

	public static String getALUSrc() {
		return ALUSrc;
	}

	public static void setALUSrc(String aLUSrc) {
		ALUSrc = aLUSrc;
	}

	public static String getRegWrite() {
		return RegWrite;
	}

	public static void setRegWrite(String regWrite) {
		RegWrite = regWrite;
	}

	public static String getPCSrc() {
		return PCSrc;
	}

	public static void setPCSrc(String pCSrc) {
		PCSrc = pCSrc;
	}
	
	public static Hashtable<String, String> getControlSignals(String opcode){
		if(opcode.equals("000000")){ // R-Format
			setALUOp("10");
			setRegDst("1");
			setBranch("0");
			setMemRead("0");
			setMemToReg("0");
			setMemWrite("0");
			setALUSrc("0");
			setRegWrite("1");
		}
		else if(opcode.equals("011100")){ // lw
			setALUOp("00");
			setRegDst("0");
			setBranch("0");
			setMemRead("1");
			setMemToReg("1");
			setMemWrite("0");
			setALUSrc("1");
			setRegWrite("1");
		}
	}
	
}
