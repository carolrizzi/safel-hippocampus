package main.java.model.situation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.event.EnvironmentalCue;
import uk.ac.kent.cs.model.situation.EminentDangerSituation;
import uk.ac.kent.cs.model.situation.Situation;

public class EminentDangerSituationTest {

	@Test
	public final void testEminentDangerSituation() {
		EnvironmentalCue e1 = new EnvironmentalCue(new byte [] {1,2,3});
		EnvironmentalCue e2 = new EnvironmentalCue(new byte [] {4,5,6});
		EnvironmentalCue e3 = new EnvironmentalCue(new byte [] {7,8,9});
		
		ArrayList<EnvironmentalCue> cues = new ArrayList<EnvironmentalCue>();
		cues.add(e2);
		cues.add(e3);
		cues.add(e1);
		
		Adrenaline adr = new Adrenaline(10);
		
		EminentDangerSituation situation = new EminentDangerSituation(cues, adr);
		Collections.sort(cues);
		
		assertEquals("Start event is incorrect for Situation.", e1, situation.getStartEvent());
		assertEquals("List of environmental cues is incorrect for Situation.", cues, situation.getEnvironmentalCues());
		assertNotNull("Situation's list of adrenalines cannot be null", situation.getAdrenalines());
		assertTrue("Situation's list of adrenalines should be empty", situation.getAdrenalines().isEmpty());
		assertEquals("Situation's status is incorrect.", Situation.Status.PAST, situation.getStatus());
		assertEquals("End event of Situation is incorrect.", adr, situation.getEndEvent());
	}

}
