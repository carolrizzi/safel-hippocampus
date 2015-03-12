package uk.ac.kent.cs.model.situation;

import java.util.ArrayList;

import uk.ac.kent.cs.model.event.EnvironmentalCue;

public class GenericSituation extends Situation {

	private static final long serialVersionUID = 1L;
	
	public GenericSituation (ArrayList<EnvironmentalCue> events) {
		super(events);
		EnvironmentalCue endEvent = this.cues.get(this.cues.size() - 1);
		this.terminate(endEvent);
	}
	
}
