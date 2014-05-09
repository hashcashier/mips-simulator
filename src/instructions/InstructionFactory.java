package instructions;

import instructions.isa.Instruction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InstructionFactory {
	public static Instruction createInstruction(String name, String parameters) throws UnkownInstructionException {
		Class<?> instructionClass = null;
		Constructor<?> constructor = null;
		Instruction result = null;
		try {
			instructionClass = Class.forName(name);
			constructor = instructionClass.getConstructor(String[].class);
			String[] params = parameters.split(" ");
			result = (Instruction) constructor.newInstance(params);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
