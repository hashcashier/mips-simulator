package datapath;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DatapathFactory {
	public static AbstractDatapath createDatapath(String name, String[] dataMemory, String[] instructionMemory, int programOffset) throws UnkownDatapathException {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		
		Class<?> concreteDatapath = null;
		Constructor<?> constructor = null;
		AbstractDatapath result = null;
		
		try {
			concreteDatapath = Class.forName("datapath.implementation." + name);
			constructor = concreteDatapath.getConstructor(String[].class, String[].class, int.class);
			result = (AbstractDatapath) constructor.newInstance(dataMemory, instructionMemory, programOffset);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new UnkownDatapathException();
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
			throw new UnkownDatapathException();
		}
		
		return result;
	}
}
