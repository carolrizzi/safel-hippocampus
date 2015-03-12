package main.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

import org.drools.KnowledgeBase;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.rule.FactHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.kent.cs.Hippocampus;

public class HippocampusTest {

	private StatefulKnowledgeSession ksession;
	private Hippocampus hippocampus = null;
	private KnowledgeBase kbase = null;

	@Test
	public final void testInitKnowledgeBase() {
		try{
			assertNotNull("KnowledgeBase cannot be null.", kbase);
			
			Collection<KnowledgePackage> kpackages = kbase.getKnowledgePackages();
			assertNotNull("Knowledge package cannot be null.", kpackages);
			KnowledgePackage kpackage = null;
			for (KnowledgePackage kpkg : kpackages){
				if(kpkg.getName().equals("uk.ac.kent.cs")) {
					kpackage = kpkg;
					break;
				}
			}
			
			Collection<Rule> rules = kpackage.getRules();
			assertEquals("Incorrect number of rules in Knowledge Base.", 7, rules.size());
			String [] ruleNames = new String [] {
				"Adrenaline Level Change", 
				"Eminent Danger Situation", 
				"Danger Situation", 
				"Danger Situation End",
				"Environmental Cue During Danger Situation", 
				"Adrenaline Change During Danger Situation",
				"Generic Situation"
			};
			boolean found = false;
			for (String ruleName : ruleNames) {
				for (Rule rule : rules){
					if(rule.getName().equals(ruleName)){
						found = true;
						break;
					}
				}
				assertTrue("Rule '" + ruleName + "' was not found in Knowledge Base.", found);
				found = false;
			}
			
			//TODO: how to check EventProcessingOption == STREAM ?
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught.");
		}
	}

	@Test
	public final void testInitKnowledgeSession () {
		try{
			assertNotNull("KnowledgeSession cannot be null.", ksession);
			
			String globalName = "adrThreshold";
			int global = (Integer) ksession.getGlobal(globalName);
			assertEquals("Global variable " + globalName + " has incorrect value.", Hippocampus.ADRENALINE_THRESHOLD, global);
			
			KnowledgeSessionConfiguration config = ksession.getSessionConfiguration();
			ClockTypeOption ctOption = config.getOption(ClockTypeOption.class);
			assertEquals("Clock Type for Knowledge Session is incorrect", "realtime", ctOption.getClockType());
			
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught.");
		}
	}
	
	@Test
	public final void testInsert() {
		try{
			Object obj = new Object();
			Method method = Hippocampus.class.getDeclaredMethod("insert", Object.class);
			method.setAccessible(true);
			FactHandle fh = (FactHandle) method.invoke(hippocampus, obj);
			
//			FactHandle fh = hippocampus.insert(obj);
			assertEquals("Incorrect object retrieved from Knowledge Session.", obj, ksession.getObject(fh));
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught.");
		}
	}

//	@Test
//	public final void testUpdate() {
//		try{
//			int level = Hippocampus.ADRENALINE_THRESHOLD - 1;
//			Adrenaline adrenaline = new Adrenaline(level);
//			FactHandle fh = hippocampus.insert(adrenaline);
//			assertEquals("Incorrect level value of Adrenaline object inserted in Knowledge Session.", level, ((Adrenaline) ksession.getObject(fh)).getLevel());
//			
//			level = Hippocampus.ADRENALINE_THRESHOLD - 2;
//			adrenaline.setLevel(level);
//			hippocampus.update(fh, adrenaline);
//			assertEquals("Incorrect level value of Adrenaline object inserted in Knowledge Session.", level, ((Adrenaline) ksession.getObject(fh)).getLevel());
//		}catch(Exception e){
//			e.printStackTrace();
//			fail("Exception caught.");
//		}
//	}
//
//	@Test
//	public final void testRetract() {
//		try{
//			Object obj = new Object();
//			FactHandle fh = hippocampus.insert(obj);
//			assertEquals("Incorrect object retrieved from Knowledge Session.", obj, ksession.getObject(fh));
//			
//			hippocampus.retract(fh);
//			assertEquals("Incorrect amount of objects in Knowldge Session.", 0, ksession.getObjects().size());
//			assertNull("Object should not exist in Knowledge Session.", ksession.getObject(fh));
//		}catch(Exception e){
//			e.printStackTrace();
//			fail("Exception caught.");
//		}
//	}

	@Before
	public void before () {
		try{
			this.hippocampus = new Hippocampus();
			
			Field field = Hippocampus.class.getDeclaredField("ksession");
			field.setAccessible(true);
			this.ksession = (StatefulKnowledgeSession) field.get(hippocampus);
			
			field = Hippocampus.class.getDeclaredField("kbase");
			field.setAccessible(true);
			this.kbase = (KnowledgeBase) field.get(hippocampus);
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught.");
		}
	}
	
	@After
	public void after () {
		try{
			this.hippocampus.disposeSession();
			this.ksession = null;
			this.kbase = null;
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception caught.");
		}
	}
}
