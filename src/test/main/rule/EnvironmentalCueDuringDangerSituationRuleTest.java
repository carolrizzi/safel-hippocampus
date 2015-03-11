package main.rule;

import static org.junit.Assert.fail;

import org.junit.Test;

import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.event.EnvironmentalCue;
import uk.ac.kent.cs.model.situation.DangerSituation;

public class EnvironmentalCueDuringDangerSituationRuleTest extends RulesTest {

	/**
	 * Verifies that DangerSituation contains only EnvironmentalCue objects inserted after its creation and before it becomes past.
	 * <dl>
	 * <dt>Steps:
	 * <dd>1. Insert EnvironmentalCue e1
	 * <dd>2. Insert adrenaline > THRESHOLD
	 * <dd>3. Insert EnvironmentalCue e2
	 * <dd>4. Insert adrenaline < THRESHOLD
	 * <dd>5. Insert EnvironmentalCue e3
	 * <dt>Expected:
	 * <dd>1. KieBase should contain one DangerSituation
	 * <dd>2. DangerSituation should contain only EnvironmentalCue e2.
	 */
	@Test
	public void environmentalCueDuringDangerSituation1 () {
		System.out.println("[JUnit] Starting test 'environmentalCueDuringDangerSituation1'");
		try {
			EnvironmentalCue e1 = new EnvironmentalCue(new int [] {1,2,3}, "e1");
			hippocampus.insert(e1, defaultDelay);
			this.checkDangerSituation(0);
			
			adrLevel = threshold + 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			this.checkDangerSituation(1);
			DangerSituation situation = this.getDangerSituation();
			this.situationCheckEnvironmentalCues(situation);
			
			EnvironmentalCue e2 = new EnvironmentalCue(new int [] {1,4,5}, "e2");
			hippocampus.insert(e2, defaultDelay);
			this.checkDangerSituation(1);
			situation = this.getDangerSituation();
			this.situationCheckEnvironmentalCues(situation, new EnvironmentalCue[]{e2});
			
			adrLevel = threshold - 1;
			hippocampus.insert(new Adrenaline(adrLevel), defaultDelay);
			this.checkDangerSituation(1);
			situation = this.getDangerSituation();
			this.situationCheckEnvironmentalCues(situation, new EnvironmentalCue[]{e2});
			
			EnvironmentalCue e3 = new EnvironmentalCue(new int [] {1,8,9}, "e3");
			hippocampus.insert(e3, defaultDelay);
			this.checkDangerSituation(1);
			situation = this.getDangerSituation();
			this.situationCheckEnvironmentalCues(situation, new EnvironmentalCue[]{e2});
			
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught");
		}finally{
			System.out.println("[JUnit] Finishing test 'environmentalCueDuringDangerSituation1'");
		}
	}
	
}
