package assembly;

import java.util.Hashtable;

public class LabelManager {
	private Hashtable<String, Integer> labelType, labelValue;
	
	public LabelManager() {
		labelType = new Hashtable<String, Integer>();
		labelValue = new Hashtable<String, Integer>();
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
