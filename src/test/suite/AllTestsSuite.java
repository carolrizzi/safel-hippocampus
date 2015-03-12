package suite;

import main.java.HippocampusTest;
import main.java.model.event.AdrenalineTest;
import main.java.model.event.EnvironmentalCueTest;
import main.java.model.event.EventTest;
import main.java.model.situation.DangerSituationTest;
import main.java.model.situation.EminentDangerSituationTest;
import main.java.model.situation.SituationTest;
import main.rule.AdrenalineChangeDuringDangerSituationRuleTest;
import main.rule.DangerSituationEndRuleTest;
import main.rule.DangerSituationRuleTest;
import main.rule.EminentDangerSituationRuleTest;
import main.rule.EnvironmentalCueDuringDangerSituationRuleTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	// ===== JAVA ===== //
	HippocampusTest.class,
	EventTest.class,
	AdrenalineTest.class,
	EnvironmentalCueTest.class,
	SituationTest.class,
	DangerSituationTest.class,
	EminentDangerSituationTest.class,

	// ===== RULE ===== //
	DangerSituationRuleTest.class,
	EminentDangerSituationRuleTest.class,
	DangerSituationEndRuleTest.class,
	EnvironmentalCueDuringDangerSituationRuleTest.class,
	AdrenalineChangeDuringDangerSituationRuleTest.class
})

public class AllTestsSuite {} 