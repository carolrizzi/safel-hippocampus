package main.java.model.situation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.event.EnvironmentalCue;
import uk.ac.kent.cs.model.event.Event;
import uk.ac.kent.cs.model.situation.DangerSituation;
import uk.ac.kent.cs.model.situation.Situation;

public class SituationTest {

	@Test
	public final void testSituationArrayListOfEnvironmentalCue() {
		class SituationExtension extends Situation {
			public SituationExtension(ArrayList<EnvironmentalCue> events) {
				super(events);
			}
		}
		
		EnvironmentalCue e1 = new EnvironmentalCue(new int [] {1,2,3}, "e1");
		EnvironmentalCue e2 = new EnvironmentalCue(new int [] {4,5,6}, "e2");
		EnvironmentalCue e3 = new EnvironmentalCue(new int [] {7,8,9}, "e3");
		
		ArrayList<EnvironmentalCue> cues = new ArrayList<EnvironmentalCue>();
		cues.add(e2);
		cues.add(e3);
		cues.add(e1);
		
		Situation situation = new SituationExtension(cues);
		Collections.sort(cues);
		assertEquals("Start event is incorrect for Situation.", e1, situation.getStartEvent());
		assertEquals("List of environmental cues is incorrect for Situation.", cues, situation.getEnvironmentalCues());
		assertNotNull("Situation's list of adrenalines cannot be null", situation.getAdrenalines());
		assertTrue("Situation's list of adrenalines should be empty", situation.getAdrenalines().isEmpty());
		assertEquals("Situation's status is incorrect.", Situation.Status.PRESENT, situation.getStatus());
	}

	@Test
	public final void testSituationEvent() {
		class SituationExtension extends Situation {
			public SituationExtension(Event event) {
				super(event);
			}
			
			public void callAsserts (Event event) {
				assertEquals("Start event is incorrect for Situation.", event, this.getStartEvent());
				assertNotNull("Situation's list of environmental cues cannot be null", this.getEnvironmentalCues());
				assertTrue("Situation's list of environmental cues should be empty.", this.getEnvironmentalCues().isEmpty());
				assertNotNull("Situation's list of adrenalines cannot be null", this.getAdrenalines());
				assertTrue("Situation's list of adrenalines should be empty", this.getAdrenalines().isEmpty());
				assertEquals("Situation's status is incorrect.", Situation.Status.PRESENT, this.getStatus());
			}
		}
		
		EnvironmentalCue e1 = new EnvironmentalCue(new int [] {1,2,3}, "e1");
		SituationExtension situation = new SituationExtension(e1);
		situation.callAsserts(e1);
		
		Adrenaline adr = new Adrenaline(0);
		situation = new SituationExtension(adr);
		situation.callAsserts(adr);
	}

	@Test
	public final void testTerminate() {
		Situation situation = new DangerSituation(new Adrenaline(0));
		assertEquals("Status of Situation is incorrect.", Situation.Status.PRESENT, situation.getStatus());
		Adrenaline adr = new Adrenaline(10);
		situation.terminate(adr);
		assertEquals("Status of Situation is incorrect.", Situation.Status.PAST, situation.getStatus());
		assertEquals("End event of Situation is incorrect.", adr, situation.getEndEvent());
	}

	@Test
	public final void testAddAdrenalineAdrenaline() {
		Situation situation = new DangerSituation(new Adrenaline(0));
		situation.addAdrenaline(new Adrenaline(3));
		situation.addAdrenaline(new Adrenaline(7));
		situation.addAdrenaline(new Adrenaline(234));
		Integer [] adr = new Integer [] {3,7,234};
		ArrayList<Integer> adrenalines = situation.getAdrenalines();
		assertNotNull("Situation's list of adrenalines cannot be null", adrenalines);
		for (int count = 0; count < 3; count ++){
			assertEquals("Situation's list of adrenalines is incorrect", adr[count], adrenalines.get(count));
		}
	}

}
