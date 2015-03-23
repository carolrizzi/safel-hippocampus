package uk.ac.kent.cs.model.situation;

import org.drools.definition.type.ClassReactive;

import uk.ac.kent.cs.model.event.Adrenaline;

@ClassReactive
public class DangerSituation extends Situation {

	private static final long serialVersionUID = 1L;
	
	public DangerSituation(Adrenaline adrenaline) {
		super(adrenaline);
	}
	
}
