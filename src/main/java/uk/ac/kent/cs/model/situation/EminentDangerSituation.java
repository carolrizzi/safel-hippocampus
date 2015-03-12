package uk.ac.kent.cs.model.situation;

import java.util.ArrayList;

import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.event.EnvironmentalCue;

public class EminentDangerSituation extends Situation {

	private static final long serialVersionUID = 1L;
	
	public EminentDangerSituation (ArrayList<EnvironmentalCue> events, Adrenaline adrenaline) {
		super(events);
		this.terminate(adrenaline);
	}
	
}
