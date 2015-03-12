package uk.ac.kent.cs.model.event;

public class EnvironmentalCue extends Event{

	private static final long serialVersionUID = 1L;
	private byte [] features;
	
	public EnvironmentalCue(byte [] features) {
		this.features = features;
	}
	
	public byte [] getFeatures() {
		return features;
	}
	
}
