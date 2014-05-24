package datapath.implementation.pipelined.stages;

import java.util.Hashtable;

public interface Stage {
	public Hashtable<String, String> process(Hashtable<String, String> input);
}
