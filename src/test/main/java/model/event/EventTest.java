package main.java.model.event;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.event.EnvironmentalCue;
import uk.ac.kent.cs.model.event.Event;

public class EventTest {

	@Test
	public final void testCompareTo() throws InterruptedException {
		Event e1 = new Adrenaline(0);
		Thread.sleep(10);
		Event e2 = new Adrenaline(1);
		Thread.sleep(10);
		Event e3 = new EnvironmentalCue(new byte [] {1,2,3});
		Thread.sleep(10);
		Event e4 = new EnvironmentalCue(new byte [] {4,5,6});
		Thread.sleep(10);
		Event e5 = new Adrenaline(2);
		
		ArrayList<Event> events = new ArrayList<Event>();
		events.add(e2);
		events.add(e5);
		events.add(e3);
		events.add(e4);
		events.add(e1);
		
		assertEquals("Incorrect sort for collection of events.", e2, events.get(0));
		assertEquals("Incorrect sort for collection of events.", e5, events.get(1));
		assertEquals("Incorrect sort for collection of events.", e3, events.get(2));
		assertEquals("Incorrect sort for collection of events.", e4, events.get(3));
		assertEquals("Incorrect sort for collection of events.", e1, events.get(4));
		
		Collections.sort(events);
		
		assertEquals("Incorrect sort for collection of events.", e1, events.get(0));
		assertEquals("Incorrect sort for collection of events.", e2, events.get(1));
		assertEquals("Incorrect sort for collection of events.", e3, events.get(2));
		assertEquals("Incorrect sort for collection of events.", e4, events.get(3));
		assertEquals("Incorrect sort for collection of events.", e5, events.get(4));
		
		
	}

}
