package suite;

import main.java.HippocampusTest;
import main.java.model.event.AdrenalineTest;
import main.java.model.event.EnvironmentalCueTest;
import main.java.model.event.EventTest;
import main.java.model.situation.DangerSituationTest;
import main.java.model.situation.EminentDangerSituationTest;
import main.java.model.situation.SituationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	HippocampusTest.class,
	EventTest.class,
	AdrenalineTest.class,
	EnvironmentalCueTest.class,
	SituationTest.class,
	DangerSituationTest.class,
	EminentDangerSituationTest.class
})

public class JavaTestsSuite {} 