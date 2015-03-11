package main.java.model.event;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.kent.cs.model.event.EnvironmentalCue;

public class EnvironmentalCueTest {

	@Test
	public final void testEnvironmentalCue() {
		int [] features = new int [] {3,6,9};
		EnvironmentalCue cue = new EnvironmentalCue(features, "cue1");
		assertEquals("Incorrect set of features for EnvironmentalCue object.", features, cue.getFeatures());
		assertEquals("Incorrect name for EnvironmentalCue object.", "cue1", cue.getName());
	}

}
