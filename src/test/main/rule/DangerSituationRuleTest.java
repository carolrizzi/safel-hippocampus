package main.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.event.EnvironmentalCue;
import uk.ac.kent.cs.model.situation.DangerSituation;
import uk.ac.kent.cs.model.situation.Situation;

public class DangerSituationRuleTest extends RulesTest{

	//TODO: Check situation's start/end events
	/**
	 * Verifies creation of DangerSituation when no EnvironmentalCue has been inserted before raising levels of Adrenaline.
	 * <dl>
	 * <dt>Steps:
	 * <dd>1. Insert adrenaline > THRESHOLD
	 * <dt>Expected:
	 * <dd>1. KieBase should contain one DangerSituation
	 * <dd>2. DangerSituation should contain no EnvironmentalCue
	 */
	@Test
	public void dangerSituation1() {
		System.out.println("[JUnit] Starting test 'dangerSituation1'");
		try {
			adrLevel = threshold + 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			checkDangerSituation(1);
			DangerSituation situation = checkSituation(DangerSituation.class, 0, 0, 0);
			assertEquals("Status of danger situation is incorrect", Situation.Status.PRESENT, situation.getStatus());
		
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught");
		}finally{
			System.out.println("[JUnit] Finishing test 'dangerSituation1'");
		}
	}
	
	/**
	 * Verifies creation of DangerSituation when an EnvironmentalCue has been inserted before raising levels of Adrenaline.
	 * <dl>
	 * <dt>Steps:
	 * <dd>1. Insert EnvironmentalCue e1
	 * <dd>2. Insert adrenaline > THRESHOLD
	 * <dt>Expected:
	 * <dd>1. KieBase should contain one DangerSituation
	 * <dd>2. DangerSituation should contain no EnvironmentalCue
	 */
	@Test
	public void dangerSituation2() {
		System.out.println("[JUnit] Starting test 'dangerSituation2'");
		try{
			EnvironmentalCue e1 = new EnvironmentalCue(new int [] {1,2,3}, "e1");
			hippocampus.insert(e1, defaultDelay);
	
			adrLevel = threshold + 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			checkDangerSituation(1);
			DangerSituation situation = checkSituation(DangerSituation.class, 0, 0, 0);
			assertEquals("Status of danger situation is incorrect", Situation.Status.PRESENT, situation.getStatus());
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught");
		}finally{
			System.out.println("[JUnit] Finishing test 'dangerSituation2'");
		}
	}
	
	/**
	 * Verifies creation of DangerSituation when an past DangerSituation already exists.
	 * <dl>
	 * <dt>Steps:
	 * <dd>1. Insert adrenaline > THRESHOLD
	 * <dd>2. Insert adrenaline < THRESHOLD
	 * <dd>3. Insert adrenaline > THRESHOLD
	 * <dt>Expected:
	 * <dd>1. KieBase should contain one DangerSituation
	 * <dd>2. DangerSituation should contain no EnvironmentalCue
	 */
	@Test
	public void dangerSituation3() {
		System.out.println("[JUnit] Starting test 'dangerSituation3'");
		try{
//			kSession.addEventListener( new DebugRuleRuntimeEventListener() );
//			new Debug
			
			adrLevel = threshold + 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			checkDangerSituation(1);
			DangerSituation situation = this.getDangerSituation();
			assertEquals("Status of danger situation is incorrect", Situation.Status.PRESENT, situation.getStatus());

			adrLevel = threshold - 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			checkDangerSituation(1);
			situation = this.getDangerSituation();
			assertEquals("Status of danger situation is incorrect", Situation.Status.PAST, situation.getStatus());
			
			adrLevel = threshold + 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			checkDangerSituation(2);
			
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught");
		}finally{
			System.out.println("[JUnit] Finishing test 'dangerSituation3'");
		}
	}
}
