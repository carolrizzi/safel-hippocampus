package uk.ac.kent.cs.model.event;

public class EnvironmentalCue extends Event{

	private int [] features;
	private String name;
	
	public EnvironmentalCue(int [] features, String name) {
		this.name = name;
		this.features = features;
	}
	
	public int[] getFeatures() {
		return features;
	}
	
	public String getName() {
		return name;
	}
}
