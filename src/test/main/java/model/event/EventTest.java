package main.java.model.event;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.event.Event;

public class EventTest {

	@Test
	public final void testCompareTo() {
		Event e1 = new Adrenaline(0);
		Event e2 = new Adrenaline(0);
		Event e3 = new Adrenaline(0);
		
		ArrayList<Event> events = new ArrayList<Event>();
		events.add(e2);
		events.add(e3);
		events.add(e1);
		
		assertEquals("Incorrect sort for collection of events.", e1, events.get(2));
		assertEquals("Incorrect sort for collection of events.", e2, events.get(0));
		assertEquals("Incorrect sort for collection of events.", e3, events.get(1));
		
		Collections.sort(events);
		
		assertEquals("Incorrect sort for collection of events.", e1, events.get(0));
		assertEquals("Incorrect sort for collection of events.", e2, events.get(1));
		assertEquals("Incorrect sort for collection of events.", e3, events.get(2));
	}

}
