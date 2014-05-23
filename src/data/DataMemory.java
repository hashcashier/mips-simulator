package data;

import java.util.Hashtable;

public class DataMemory {
	String address, writeData, readData;
	static Hashtable<Integer, String> memory = new Hashtable<Integer, String>();

	public DataMemory(String address, String writeData) {
		this.address = address;
		this.writeData = writeData;
	}
}
