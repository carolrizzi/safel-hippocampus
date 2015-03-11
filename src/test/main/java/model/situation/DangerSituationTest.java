package main.java.model.situation;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.situation.DangerSituation;
import uk.ac.kent.cs.model.situation.Situation;

public class DangerSituationTest {

	@Test
	public final void testDangerSituation() {
		Adrenaline adr = new Adrenaline(0);
		DangerSituation situation = new DangerSituation(adr);
		
		assertEquals("Start event is incorrect for Situation.", adr, situation.getStartEvent());
		assertNotNull("Situation's list of environmental cues cannot be null", situation.getEnvironmentalCues());
		assertTrue("Situation's list of environmental cues should be empty.", situation.getEnvironmentalCues().isEmpty());
		assertNotNull("Situation's list of adrenalines cannot be null", situation.getAdrenalines());
		assertTrue("Situation's list of adrenalines should be empty", situation.getAdrenalines().isEmpty());
		assertEquals("Situation's status is incorrect.", Situation.Status.PRESENT, situation.getStatus());
	}

}
