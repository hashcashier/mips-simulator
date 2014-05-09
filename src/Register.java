public class Register {

	private String value;
	private String title;

	private boolean type[] = new boolean[10];

	static int TYPE_ARGUMENT = 0;
	static int TYPE_RESULT_VALUE = 0;
	static int TYPE_TEMPORARY = 0;
	static int TYPE_SAVED = 0;
	static int TYPE_GLOBAL_POINTER = 0;
	static int TYPE_STACK_POINTER = 0;
	static int TYPE_FRAME_POINTER = 0;
	static int TYPE_RETURN_ADDRESS = 0;
	static int TYPE_ZERO = 0;

	/*
	 * e.g. new Register("1010101010...", "$s0", Register.TYPE_TEMPORARY)
	 */

	public Register(String value, String title, int type) {
		this.value = value;
		this.title = title;
		this.type[type] = true;
	}
}
