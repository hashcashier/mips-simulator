package instructions;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PseudoInstructionFactory {
	public static PseudoInstruction createInstruction(String name, String[] parameters, int[] types) 
			throws UnkownInstructionException {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		
		Class<?> instructionClass = null;
		Constructor<?> constructor = null;
		PseudoInstruction result = null;
		
		try {
			instructionClass = Class.forName("instructions.pseudo." + name);
			constructor = instructionClass.getConstructor(String[].class, int[].class);
			result = (PseudoInstruction) constructor.newInstance(parameters, types);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new UnkownInstructionException();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			throw new UnkownInstructionException();
		}
		
		return result;
	}
}
