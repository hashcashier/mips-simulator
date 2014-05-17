package assembly;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

import registers.RegisterManager;

public class LabelManager {
	private Hashtable<String, Integer> labelType, labelValue;
	
	public LabelManager() {
		labelType = new Hashtable<String, Integer>();
		labelValue = new Hashtable<String, Integer>();
		addPredefinedLabels();
	}
	
	private void addPredefinedLabels() {
		RegisterManager tempRegisterManager = new RegisterManager();
		for(int i = 0; i < 32; i++) {
			String label = tempRegisterManager.getRegisterTitle(i);
			setLabel(label, i, 4);
		}
	}
	
	public Integer getLabelValue(String label) {
		return labelValue.get(label);
	}
	
	public Integer getLabelType(String label) {
		return labelType.get(label);
	}
	
	public void setLabel(String key, Integer value, Integer type) {
		labelValue.put(key, value);
		labelType.put(key, type);
	}
	
	public boolean containsLabel(String label) {
		return labelValue.containsKey(label);
	}
	
	public String[] getAllLabels(int type) {
		ArrayList<String> labels = new ArrayList<String>();
		for(Entry<String, Integer> entry : labelType.entrySet())
			if(entry.getValue() == type)
				labels.add(entry.getKey());

		return labels.toArray(new String[0]);
	}
	
	public String[] getAllDataLabels() {
		return getAllLabels(1);
	}
	
	public String[] getAllInstructionLabels() {
		return getAllLabels(2);
	}
}
