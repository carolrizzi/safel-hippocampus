package uk.ac.kent.cs.model.event;

public abstract class Event implements Comparable<Event>{

	//TODO: change features' type to byte
//	private int [] features;
	private long timestamp;
	
	public Event() {
		timestamp = System.nanoTime();
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public int compareTo(Event event) {
		return (new Long(this.timestamp - event.getTimestamp())).intValue();
//		(new EminentDangerSituationRuleTest()).
	}
	
	//TODO: I may need it in the future
//	@Override
//	public boolean equals(Object obj) {
//		if(obj instanceof Event){
//			Event event = (Event) obj;
//			if (Arrays.equals(this.features, event.getFeatures())){
//				return true;
//			}
//		}
//		return false;
//	}
}
