public class RegisterManager {

	private Register registers[] = new Register[32];

	public RegisterManager() {
		init();
	}

	public String getRegisterValue(String regTitle) {
		for (int i = 0; i < 32; i++)
			if (registers[i].getTitle().equals(regTitle))
				return registers[i].getValue();
		return "Register Not Found.";
	}

	public boolean setRegisterValue(String regTitle, String regValue) {
		for (int i = 0; i < 32; i++)
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
		for (int i = 0; i < 32; i++) {
			if (registers[i] != null) {
				System.out.println(i + ". " + registers[i]);
			}
		}
	}

	private String zeros32() {
		return "00000000000000000000000000000000";
	}

	public static void main(String... args) {
		RegisterManager rm = new RegisterManager();
		rm.displayRegisters();
	}
}
