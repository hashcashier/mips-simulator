package registers;
public class Register {

	private String value;
	private String title;

	private boolean type[] = new boolean[12];

	static int TYPE_ZERO = 0;
	static int TYPE_ARGUMENT = 1;
	static int TYPE_RESULT_VALUE = 2;
	static int TYPE_TEMPORARY = 3;
	static int TYPE_SAVED = 4;
	static int TYPE_GLOBAL_POINTER = 5;
	static int TYPE_STACK_POINTER = 6;
	static int TYPE_FRAME_POINTER = 7;
	static int TYPE_RETURN_ADDRESS = 8;
	static int TYPE_KERNEL = 9;
	static int TYPE_ASSEMBLER_TEMPORARY = 10;
	static int TYPE_PIPELINE = 11;

	/*
	 * e.g. new Register("1010101010...", "$s0", Register.TYPE_TEMPORARY)
	 */

	public Register(String value, String title, int type) {
		this.value = value;
		this.title = title;
		this.type[type] = true;
	}

	public String getTitle() {
		return title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return title + " :=  " + value;
	}
}
