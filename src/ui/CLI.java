package ui;
import java.math.BigInteger;

public class CLI {
	
	public static String rev(String input) {
		String tmp = "";
		for(int i = 0; i < input.length(); i++)
			if(input.charAt(i) == '0')
				tmp += '1';
			else
				tmp += '0';
		return tmp;
	}

	public static void main(String[] args) {
		System.out.println((rev(Long.toBinaryString((long)-20))));
		BigInteger big = new BigInteger(rev(Long.toBinaryString((long)-20)), 2); 
		System.out.println(big);
//		return;
//		String filePath;
//		if(args.length == 0) {
//			Scanner commandLineScanner = new Scanner(System.in);
//			System.out.print("Enter file path: ");
//			filePath = commandLineScanner.nextLine();
//			commandLineScanner.close();
//		} else {
//			filePath = args[0];
//		}
//		
//		try {
//			Simulator mipsSimulator = new Simulator(filePath, "Pipelined", 0);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DuplicateLabelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnkownInstructionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
