package helper;

import java.util.concurrent.TimeUnit;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.io.ResourceFactory;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.rule.FactHandle;
import org.drools.time.SessionPseudoClock;

import uk.ac.kent.cs.Hippocampus;

public class HippocampusPseudoClock {

	private static KnowledgeBase kbase = null;
	private static StatefulKnowledgeSession ksession = null;
	private SessionPseudoClock clock = null;
	
	public HippocampusPseudoClock() throws Exception {
		this.initKnowledgeBase();
		this.initKownledgeSession();
	}
	
    private void initKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("situation.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBaseConfiguration config = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        config.setOption( EventProcessingOption.STREAM );
        kbase = KnowledgeBaseFactory.newKnowledgeBase(config);
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
    }
    
    private void initKownledgeSession () throws Exception {
    	if(kbase == null) throw new Exception ("KnowledgeBase cannot be null.");
    	KnowledgeSessionConfiguration config = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
    	config.setOption(ClockTypeOption.get("pseudo"));
    	ksession = kbase.newStatefulKnowledgeSession(config, null);
    	ksession.setGlobal("adrThreshold", Hippocampus.ADRENALINE_THRESHOLD);
    	this.clock = ksession.getSessionClock();
    }
    
    public FactHandle insert (Object object, long delay) {
    	clock.advanceTime(delay, TimeUnit.SECONDS);
    	return this.insert(object);
    }
//    
//    public FactHandle insert (Adrenaline adr, long delay) {
//    	clock.advanceTime(delay, TimeUnit.SECONDS);
//    	this.adrenaline.setLevel(adr.getLevel());
//    	this.update(this.adrenalineFactHandle, this.adrenaline, delay);
//    	return this.adrenalineFactHandle;
//    }
    
    public FactHandle insert (Object object) {
    	FactHandle fh = ksession.insert(object);
    	ksession.fireAllRules();
    	return fh;
    }
    
    public void update (FactHandle fh, Object object, long delay) {
    	clock.advanceTime(delay, TimeUnit.SECONDS);
    	this.update(fh, object);
    }
    
    public void update (FactHandle fh, Object object) {
    	ksession.update(fh, object);
    	ksession.fireAllRules();
    }

    public void retract (FactHandle fh, long delay) {
    	clock.advanceTime(delay, TimeUnit.SECONDS);
    	this.retract(fh);
    }
    
    public void retract (FactHandle fh) {
    	ksession.retract(fh);
    	ksession.fireAllRules();
    }
	
    public void disposeSession () {
    	ksession.dispose();
    }
    
    public StatefulKnowledgeSession getKnowledgeSession () {
    	return ksession;
    }
}
