package main.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import helper.HippocampusPseudoClock;

import java.util.ArrayList;
import java.util.Arrays;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.After;
import org.junit.Before;

import uk.ac.kent.cs.Hippocampus;
import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.event.EnvironmentalCue;
import uk.ac.kent.cs.model.situation.DangerSituation;
import uk.ac.kent.cs.model.situation.EminentDangerSituation;
import uk.ac.kent.cs.model.situation.Situation;

public abstract class RulesTest {

	protected static HippocampusPseudoClock hippocampus;
	protected static StatefulKnowledgeSession kSession;
	protected static final int threshold = Hippocampus.ADRENALINE_THRESHOLD;
	protected static final int defaultDelay = 3; //seconds
	protected Integer adrLevel = 0;
	
	//	===== SETUP ========================================================= //
	
	@Before
	public void initHippocampus () {
//		System.out.println("Before");
		try{
			hippocampus = new HippocampusPseudoClock();
			kSession = hippocampus.getKnowledgeSession();
			checkAllObjects(0, 0, 0, 0);
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught");
		}
	}
	
	@After
	public void stopHippocampus () {
//		System.out.println("After");
		try{
			hippocampus.disposeSession();
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught");
		}
	}
	
	//	===== PRIVATE ======================================================= //
	
	protected void checkObject(Class<?> objClass) {
		this.checkObject(1, objClass);
	}
	
	protected void checkObject (int amount, Class<?> objClass) {
		int count = 0;
		for(Object obj : kSession.getObjects()){
			if(obj.getClass().equals(objClass)) count++;
		}
		assertEquals("KieBase should contain " + amount + " objects of type " + objClass + ".", amount, count);
	}
	
	protected void checkAllObjects (int amountAdrenaline, int amountEnvironmentalCue, int amountDanger, int amountEminentDanger) {
		checkObject(amountAdrenaline, Adrenaline.class);
		checkObject(amountEnvironmentalCue, EnvironmentalCue.class);
		checkObject(amountDanger, DangerSituation.class);
		checkObject(amountEminentDanger, EminentDangerSituation.class);
	}
	
	protected void checkAllObjects (int amountEnvironmentalCue, int amountDanger, int amountEminentDanger) {
		checkObject(Adrenaline.class);
		checkObject(amountEnvironmentalCue, EnvironmentalCue.class);
		checkObject(amountDanger, DangerSituation.class);
		checkObject(amountEminentDanger, EminentDangerSituation.class);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> ArrayList<T> getObjects (Class<T> objClass) {
		ArrayList<T> objArray = new ArrayList<T>();
		for(Object obj : kSession.getObjects()){
			if(obj.getClass().equals(objClass) || objClass.isInstance(obj) || objClass.isAssignableFrom(obj.getClass()))
				objArray.add((T) obj);
		}
		return objArray;
	}
	
	protected <T> T getObject (Class<T> objClass) {
		ArrayList<T> objArray = this.getObjects(objClass);
		if(objArray.isEmpty()){
			System.err.println("There is no object of type " + objClass + " in the KieBase.");
			return null;
		}
		if(objArray.size() != 1){
			System.err.println("There are more than one object of type " + objClass + " in the KieBase. Returning the first element of the array.");
		}
		return objArray.get(0);
	}
	
	protected void checkDangerSituation (int amount) {
		this.checkObject(amount, DangerSituation.class);
	}
	
	protected DangerSituation getDangerSituation () {
		return this.getObject(DangerSituation.class);
	}
	
	protected <T extends Situation> T checkSituation (Class<T> situationType, int amountEnvironmentalCues, int amountAdrenaline, Integer currentAdrenalineLevel) {
		T situation = this.getObject(situationType);
		checkSituation(situation, amountEnvironmentalCues, amountAdrenaline, currentAdrenalineLevel);
		return situation;
	}
	
	protected Situation checkSituation (int amountEnvironmentalCues, int amountAdrenaline, Integer currentAdrenalineLevel) {
		Situation situation = this.getObject(Situation.class);
		checkSituation(situation, amountEnvironmentalCues, amountAdrenaline, currentAdrenalineLevel);
		return situation;
	}
	
	protected <T extends Situation> void checkSituation (T situation, int amountEnvironmentalCues, int amountAdrenaline, Integer currentAdrenalineLevel) {
		ArrayList<Integer> adrenalines = situation.getAdrenalines();
		int adrSize = adrenalines.size();
		Class<? extends Situation> situationType = situation.getClass(); 
		assertEquals("Situation of type " + situationType + " contains incorrect amount of events.", amountEnvironmentalCues, situation.getEnvironmentalCues().size());
		assertEquals("Situation of type " + situationType + " contains incorrect amount of adrenaline measurements.", amountAdrenaline, adrSize);
		assertEquals("Situation of type " + situationType + " contains incorrect Adrenaline level (most recent measurement).", currentAdrenalineLevel, (adrSize > 0 ? (adrenalines.get(adrSize - 1)) : new Integer(0)));
	}
	
	protected void situationCheckEnvironmentalCues (Situation situation) {
		this.situationCheckEnvironmentalCues(situation, new ArrayList<EnvironmentalCue>());
	}

	protected void situationCheckEnvironmentalCues (Situation situation, EnvironmentalCue[] events){
		ArrayList<EnvironmentalCue> eventsArray = new ArrayList<EnvironmentalCue>(Arrays.asList(events));
		this.situationCheckEnvironmentalCues(situation, eventsArray);
	}
	
	protected void situationCheckEnvironmentalCues (Situation situation, ArrayList<EnvironmentalCue> events) {
		if(events == null || events.isEmpty()){
			assertEquals("Number of EnvironmentalCue objects in Situation is incorrect.", 0, situation.getEnvironmentalCues().size());
			return;
		}
		ArrayList<EnvironmentalCue> test = new ArrayList<EnvironmentalCue>(situation.getEnvironmentalCues());
		assertTrue("EnvironmentalCue objects in Situation are incorrect.", !events.retainAll(test));
		assertTrue("EnvironmentalCue objects in Situation are incorrect.", !test.retainAll(events));
	}
	
	/**
	 * If Situation should contain no Adrenaline objects
	 */
	protected void situationCheckAdrenalines (Situation situation) {
		this.situationCheckAdrenalines(situation, new Integer[]{});
	}

	protected void situationCheckAdrenalines (Situation situation, Integer[] adrenalines){
		ArrayList<Integer> eventsArray = new ArrayList<Integer>(Arrays.asList(adrenalines));
		this.situationCheckAdrenalines(situation, eventsArray);
	}
	
	protected void situationCheckAdrenalines (Situation situation, ArrayList<Integer> adrenalines) {
		if(adrenalines == null || adrenalines.isEmpty()){
			assertEquals("Number of Adrenaline objects in Situation is incorrect.", 0, situation.getAdrenalines().size());
			return;
		}
		ArrayList<Integer> test = new ArrayList<Integer>(situation.getAdrenalines());
		assertTrue("Adrenaline objects in Situation are incorrect.", !adrenalines.retainAll(test));
		assertTrue("Adrenaline objects in Situation are incorrect.", !test.retainAll(adrenalines));
	}
}
