package suite;

import main.rule.AdrenalineChangeDuringDangerSituationRuleTest;
import main.rule.DangerSituationEndRuleTest;
import main.rule.DangerSituationRuleTest;
import main.rule.EminentDangerSituationRuleTest;
import main.rule.EnvironmentalCueDuringDangerSituationRuleTest;
import main.rule.GeneralTestRuleTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	DangerSituationRuleTest.class,
	EminentDangerSituationRuleTest.class,
	DangerSituationEndRuleTest.class,
	EnvironmentalCueDuringDangerSituationRuleTest.class,
	AdrenalineChangeDuringDangerSituationRuleTest.class,
	GeneralTestRuleTest.class
})

public class RuleTestsSuite {} 