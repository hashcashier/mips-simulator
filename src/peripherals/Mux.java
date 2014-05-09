package peripherals;
import java.util.ArrayList;


public class Mux {
		String select;
		ArrayList<String> input;
		
	public Mux(String select, ArrayList<String> input)
		{
			this.select = select;
			this.input = input;
		}
	
	public String getOutput()
		{
			try
				{
					int position = Integer.parseInt(select, 2);
					if(input.get(position) !=null)
						return input.get(position);
					return null;
				}
			catch(Exception e)
			{
				return null;
			}
		}
	
	public void setSelect(String newSelect)
		{
			select = newSelect;
		}
}
