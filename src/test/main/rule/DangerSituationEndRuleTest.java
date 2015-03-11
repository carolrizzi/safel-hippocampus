package main.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.event.EnvironmentalCue;
import uk.ac.kent.cs.model.situation.DangerSituation;
import uk.ac.kent.cs.model.situation.Situation;

public class DangerSituationEndRuleTest extends RulesTest {

	/**
	 * Verifies that DangerSituation containing no EnvironmentalCue becomes past after reducing Adrenaline levels.
	 * <dl>
	 * <dt>Steps:
	 * <dd>1. Insert adrenaline > THRESHOLD
	 * <dd>2. Insert adrenaline < THRESHOLD
	 * <dt>Expected:
	 * <dd>1. KieBase should contain one DangerSituation
	 * <dd>2. DangerSituation should be past.
	 */
	@Test
	public void dangerSituationEnd1 () {
		System.out.println("[JUnit] Starting test 'dangerSituationEnd1'");
		try {
			adrLevel = threshold + 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			checkDangerSituation(1);
			DangerSituation situation = this.getDangerSituation();
			assertEquals("DangerSituation has incorrect status.", Situation.Status.PRESENT, situation.getStatus());
			
			adrLevel = threshold - 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			checkDangerSituation(1);
			situation = this.getDangerSituation();
			assertEquals("DangerSituation has incorrect status.", Situation.Status.PAST, situation.getStatus());

		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught");
		}finally{
			System.out.println("[JUnit] Finishing test 'dangerSituationEnd1'");
		}
	}

	/**
	 * Verifies that DangerSituation containing EnvironmentalCue objects becomes past after reducing Adrenaline levels.
	 * <dl>
	 * <dt>Steps:
	 * <dd>1. Insert adrenaline > THRESHOLD
	 * <dd>2. Insert two EnvironmentalCue objects: e1 and e2
	 * <dd>3. Insert adrenaline < THRESHOLD
	 * <dt>Expected:
	 * <dd>1. KieBase should contain one DangerSituation
	 * <dd>2. DangerSituation should contain both e1 and e2
	 * <dd>3. DangerSituation should be past.
	 */
	@Test
	public void dangerSituationEnd2 () {
		System.out.println("[JUnit] Starting test 'dangerSituationEnd2'");
		try {
			adrLevel = threshold + 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			this.checkDangerSituation(1);
			DangerSituation situation = this.getDangerSituation();
			assertEquals("DangerSituation has incorrect status.", Situation.Status.PRESENT, situation.getStatus());
			
			EnvironmentalCue e1 = new EnvironmentalCue(new int [] {1,2,3}, "e1");
			hippocampus.insert(e1, defaultDelay);
			
			EnvironmentalCue e2 = new EnvironmentalCue(new int [] {1,4,5}, "e2");
			hippocampus.insert(e2, defaultDelay);
			
			adrLevel = threshold - 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			this.checkDangerSituation(1);
			situation = this.getDangerSituation();
			this.situationCheckEnvironmentalCues(situation, new EnvironmentalCue[]{e1,e2});
			assertEquals("DangerSituation has incorrect status.", Situation.Status.PAST, situation.getStatus());

		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught");
		}finally{
			System.out.println("[JUnit] Finishing test 'dangerSituationEnd2'");
		}
	}
}
