package registers;

import java.util.Hashtable;

public class RegisterManager {

	public static final int REGISTER_COUNT = 32;
	private Register registers[] = new Register[REGISTER_COUNT];

	public RegisterManager() {
		init();
	}

	public String getRegisterValue(int regNumber) {
		return registers[regNumber].getValue();
	}

	public String getRegisterValue(String regTitle) {
		for (int i = 0; i < REGISTER_COUNT; i++)
			if (registers[i].getTitle().equals(regTitle))
				return registers[i].getValue();
		return "Register Not Found.";
	}

	public void setRegisterValue(int regNumber, String regValue)
			throws RegisterOperationNotAllowedException {
		if (regNumber == 0)
			throw new RegisterOperationNotAllowedException(
					"Cannot change value of register $zero");
		registers[regNumber].setValue(regValue);
	}

	public boolean setRegisterValue(String regTitle, String regValue) {
		if (regTitle.equals("$zero"))
			return true;
		for (int i = 0; i < REGISTER_COUNT; i++)
			if (registers[i].getTitle().equals(regTitle)) {
				registers[i].setValue(regValue);
				return true;
			}
		return false;
	}

	private void init() {
		registers[0] = new Register(zeros32(), "$zero", Register.TYPE_ZERO);

		registers[1] = new Register(zeros32(), "$at",
				Register.TYPE_ASSEMBLER_TEMPORARY);

		for (int i = 4; i <= 7; i++) {
			String name = "$a" + (i - 4);
			registers[i] = new Register(zeros32(), name, Register.TYPE_ARGUMENT);
		}

		registers[2] = new Register(zeros32(), "$v0",
				Register.TYPE_RESULT_VALUE);
		registers[3] = new Register(zeros32(), "$v1",
				Register.TYPE_RESULT_VALUE);

		for (int i = 8; i <= 15; i++) {
			String name = "$t" + (i - 8);
			registers[i] = new Register(zeros32(), name,
					Register.TYPE_TEMPORARY);
		}

		registers[24] = new Register(zeros32(), "$t8", Register.TYPE_TEMPORARY);
		registers[25] = new Register(zeros32(), "$t9", Register.TYPE_TEMPORARY);

		for (int i = 16; i <= 23; i++) {
			String name = "$s" + (i - 16);
			registers[i] = new Register(zeros32(), name, Register.TYPE_SAVED);
		}

		registers[26] = new Register(zeros32(), "$k0", Register.TYPE_KERNEL);
		registers[27] = new Register(zeros32(), "$k1", Register.TYPE_KERNEL);

		registers[28] = new Register(zeros32(), "$gp",
				Register.TYPE_GLOBAL_POINTER);

		registers[29] = new Register(zeros32(), "$sp",
				Register.TYPE_STACK_POINTER);

		registers[30] = new Register(zeros32(), "$fp",
				Register.TYPE_FRAME_POINTER);

		registers[31] = new Register(zeros32(), "$ra",
				Register.TYPE_RETURN_ADDRESS);
	}

	public void displayRegisters() {
		for (int i = 0; i < REGISTER_COUNT; i++) {
			if (registers[i] != null) {
				System.out.println(i + ". " + registers[i]);
			}
		}
	}

	public static String zeros32() {
		return "00000000000000000000000000000000";
	}

	public String getRegisterTitle(int regNumber) {
		return registers[regNumber].getTitle();
	}

	public Register getRegister(int regNumber) {
		return registers[regNumber];
	}

	public static void main(String... args) {
		RegisterManager rm = new RegisterManager();

		rm.displayRegisters();
	}

	public String binaryToHex(String bin) {
		return String.format("%X", Integer.parseInt(bin, 2));
	}

	public String formatHex(String hex) {
		switch (hex.length()) {
		case 1:
			return "0x0000000" + hex;
		case 2:
			return "0x000000" + hex;
		case 3:
			return "0x00000" + hex;
		case 4:
			return "0x0000" + hex;
		case 5:
			return "0x000" + hex;
		case 6:
			return "0x00" + hex;
		case 7:
			return "0x0" + hex;
		default:
			return hex;
		}
	}

	public Hashtable<String, String> getRegisterContents() {
		Hashtable<String, String> result = new Hashtable<String, String>();
		for (int i = 0; i < REGISTER_COUNT; i++)
			result.put(getRegisterTitle(i), getRegisterValue(i));
		return result;
	}
}
