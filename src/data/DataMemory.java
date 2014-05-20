package data;

import java.util.Hashtable;

public class DataMemory {
	String address, writeData, readData;
	static Hashtable<Integer, String> memory = new Hashtable<Integer, String>();

	public DataMemory(String address, String writeData) {
		this.address = address;
		this.writeData = writeData;
	}

	public void dotdataLines(String[] dataLines) {
		int lines = dataLines.length;
		for (int i = 0; i < lines; i++) {
			String dataLine = dataLines[i].trim();
		}

	}
}
