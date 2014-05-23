package peripherals;

public class Mux {
	String select;
	String [] input;

	public Mux(String select, String [] input) {
		this.select = select;
		this.input = input;
	}

	public String getOutput() {
		try {
			int position = Integer.parseInt(select, 2);
			if (input[position] != null)
				return input[position];
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public void setSelect(String newSelect) {
		select = newSelect;
	}
}
