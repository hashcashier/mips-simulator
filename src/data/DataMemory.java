package data;

import java.util.Hashtable;

public class DataMemory {
	String address,writeData,readData;
	static Hashtable<Integer,String> memory = new Hashtable<Integer,String>();
	
	public DataMemory(String address, String writeData)
		{
			this.address = address;
			this.writeData = writeData;
		}
	
	public void dotdataLines(String [] dataLines)
		{
			int lines = dataLines.length;
			for(int i = 0; i < lines; i++) {
				String dataLine = dataLines[i].trim();
				dataLine.replace(".data", "");
				if(dataLine.contains(".asciiz") || dataLine.contains(".ascii"))
				{
					int start = dataLine.indexOf("\"") + 1;
					int end = dataLine.lastIndexOf("\"");
					dataLine = dataLine.substring(start, end);
					int size = memory.size();
					memory.put(size, dataLine);
				}
				if(dataLine.contains(".word"))
				{
					dataLine.replace(" ", "");
					int start = dataLine.indexOf(",") - 1;
					int end = dataLine.lastIndexOf(",") + 2;
					dataLine = dataLine.substring(start, end);
					String [] array = dataLine.split(",");
					int size = memory.size();
					for(i = 0; i<array.length ; i++)
						{
							memory.put(size, dataLine);
							size++;
						}
					
					
				}
			}	
			
		}
}
