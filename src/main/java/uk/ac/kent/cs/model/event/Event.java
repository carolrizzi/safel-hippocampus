package uk.ac.kent.cs.model.event;

import java.io.Serializable;

public abstract class Event implements Serializable, Comparable<Event>{
	
	private static final long serialVersionUID = 1L;
	private long timestamp;
	
	public Event() {
		timestamp = System.nanoTime();
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public int compareTo(Event event) {
		if(event == null) throw new NullPointerException("Event object cannot be null");
		if (this.timestamp < event.getTimestamp()) return -1;
		if (this.timestamp > event.getTimestamp()) return 1;
		return 0;
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
