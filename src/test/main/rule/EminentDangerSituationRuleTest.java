package main.rule;

import static org.junit.Assert.fail;

import org.junit.Test;

import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.event.EnvironmentalCue;
import uk.ac.kent.cs.model.situation.EminentDangerSituation;

public class EminentDangerSituationRuleTest extends RulesTest {

	/**
	 * Verifies creation of EminentDangerSituation when no EnvironmentalCue has been inserted before raising Adrenaline levels.
	 * <dl>
	 * <dt>Steps:
	 * <dd>3. Insert adrenaline > THRESHOLD
	 * <dt>Expected:
	 * <dd>1. KieBase should contain no EminentDangerSituation objects.
	 */
	@Test
	public void eminentDangerSituation1 () {
		System.out.println("[JUnit] Starting test 'eminentDangerSituation1'");
		try {
			// 1. ADRENALINE > THRESHOLD
			// a) KieBase should contain no Situation or EnvironmentalCue
			adrLevel = threshold + 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			checkEminentDangerSituation(0);		
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught");
		}finally{
			System.out.println("[JUnit] Finishing test 'eminentDangerSituation1'");
		}
	}
	
	/**
	 * Verifies creation of EminentDangerSituation when EnvironmentalCue objects have been inserted before raising Adrenaline levels.
	 * <dl>
	 * <dt>Steps:
	 * <dd>1. Insert EnvironmentalCue e1 and e2
	 * <dd>2. Delay < 2 minutes
	 * <dd>3. Insert adrenaline > THRESHOLD
	 * <dd>3. Insert adrenaline > previous adrenaline
	 * <dt>Expected:
	 * <dd>1. KieBase should contain one EminentDangerSituation
	 * <dd>2. EminentDangerSituation should contain both e1 and e2.
	 */
	@Test
	public void eminentDangerSituation2 () {
		System.out.println("[JUnit] Starting test 'eminentDangerSituation2'");
		try {
			EnvironmentalCue e1 = new EnvironmentalCue(new byte [] {1,2,3});
			hippocampus.insert(e1, defaultDelay);
			checkEminentDangerSituation(0);
			
			EnvironmentalCue e2 = new EnvironmentalCue(new byte [] {1,4,5});
			hippocampus.insert(e2, defaultDelay);
			checkEminentDangerSituation(0);
			
			adrLevel = threshold + 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			checkEminentDangerSituation(1);
			EminentDangerSituation situation = checkSituation(EminentDangerSituation.class, 2, 0, 0);
			this.situationCheckEnvironmentalCues(situation, new EnvironmentalCue[]{e1,e2});
			
			adrLevel = threshold + 2;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			checkEminentDangerSituation(1);
			situation = checkSituation(EminentDangerSituation.class, 2, 0, 0);
			this.situationCheckEnvironmentalCues(situation, new EnvironmentalCue[]{e1,e2});
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught");
		}finally{
			System.out.println("[JUnit] Finishing test 'eminentDangerSituation2'");
		}
	}

	/**
	 * Verifies creation of EminentDangerSituation when EnvironmentalCue objects have been inserted in different delays before raising Adrenaline levels.
	 * <dl>
	 * <dt>Steps:
	 * <dd>1. Insert EnvironmentalCue e1
	 * <dd>2. Delay > 2 minutes
	 * <dd>3. Insert EnvironmentalCue e2
	 * <dd>4. Insert adrenaline > THRESHOLD
	 * <dt>Expected:
	 * <dd>1. KieBase should contain one EminentDangerSituation
	 * <dd>2. EminentDangerSituation should contain only EnvironmentalCue e1.
	 */
	@Test
	public void eminentDangerSituation3 () {
		System.out.println("[JUnit] Starting test 'eminentDangerSituation3'");
		try {
			EnvironmentalCue e1 = new EnvironmentalCue(new byte [] {1,2,3});
			hippocampus.insert(e1, defaultDelay);
			checkEminentDangerSituation(0);
			
			EnvironmentalCue e2 = new EnvironmentalCue(new byte [] {1,2,3});
			hippocampus.insert(e2, 180);
			checkEminentDangerSituation(0);
			
			adrLevel = threshold + 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			checkEminentDangerSituation(1);
			EminentDangerSituation situation = checkSituation(EminentDangerSituation.class, 1, 0, 0);
			this.situationCheckEnvironmentalCues(situation, new EnvironmentalCue[]{e2});
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught");
		}finally{
			System.out.println("[JUnit] Finishing test 'eminentDangerSituation3'");
		}
	}
	
	private void checkEminentDangerSituation (int amount) {
		this.checkObject(amount, EminentDangerSituation.class);
	}
}
