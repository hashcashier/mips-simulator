package assembly;

import java.util.Hashtable;

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
			labelType.put(label, 4);
			labelValue.put(label, i);
		}
	}
	
	public Integer getLabelValue(String label) {
		return labelValue.get(label);
	}
	
	public void setLabel(String key, Integer value, Integer type) {
		labelValue.put(key, value);
		labelType.put(key, type);
	}
	
	public boolean containsLabel(String label) {
		return labelValue.contains(label);
	}
	
}
