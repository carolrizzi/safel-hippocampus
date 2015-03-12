package main.java.model.event;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.kent.cs.model.event.EnvironmentalCue;

public class EnvironmentalCueTest {

	@Test
	public final void testEnvironmentalCue() {
		byte [] features = new byte [] {3,6,9};
		EnvironmentalCue cue = new EnvironmentalCue(features);
		assertEquals("Incorrect set of features for EnvironmentalCue object.", features, cue.getFeatures());
	}

}
