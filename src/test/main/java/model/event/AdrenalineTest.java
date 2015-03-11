package main.java.model.event;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.kent.cs.model.event.Adrenaline;

public class AdrenalineTest {

	@Test
	public final void testAdrenaline() {
		Adrenaline adrenaline = new Adrenaline(15);
		assertEquals("Incorrect value for adrenaline level.", 15, adrenaline.getLevel());
	}

}
