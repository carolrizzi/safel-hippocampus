package uk.ac.kent.cs;

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

public class Hippocampus {

	private static KnowledgeBase kbase = null;
	private static StatefulKnowledgeSession ksession = null;
	public static final int ADRENALINE_THRESHOLD = 5;

	public Hippocampus() throws Exception {
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
    	config.setOption(ClockTypeOption.get("realtime"));
    	ksession = kbase.newStatefulKnowledgeSession(config, null);
    	ksession.setGlobal("adrThreshold", ADRENALINE_THRESHOLD);
    }
    
    public FactHandle insert (Object object) {
    	FactHandle fh = ksession.insert(object);
    	ksession.fireAllRules();
    	return fh;
    }
    
    public void update (FactHandle fh, Object object) {
    	ksession.update(fh, object);
    	ksession.fireAllRules();
    }

    public void retract (FactHandle fh) {
    	ksession.retract(fh);
    	ksession.fireAllRules();
    }
	
    public void disposeSession () {
    	ksession.dispose();
    }
}
