package uk.ac.kent.cs.model.situation;

import java.util.ArrayList;
import java.util.Collections;

import uk.ac.kent.cs.model.event.Adrenaline;
import uk.ac.kent.cs.model.event.EnvironmentalCue;
import uk.ac.kent.cs.model.event.Event;

public abstract class Situation {

	protected ArrayList<EnvironmentalCue> events;
	protected ArrayList<Integer> adrenalines;
	protected Event startEvent;
	protected Event endEvent;
	protected Status status;
	
	public enum Status {
	    PRESENT, PAST
	}

	public Situation(ArrayList<EnvironmentalCue> events) {
		Collections.sort(events);
		this.startEvent = events.get(0);
		this.events = new ArrayList<EnvironmentalCue>(events);
		this.adrenalines = new ArrayList<Integer>();
		this.status = Status.PRESENT;
	}
	
	public Situation(Event startEvent) {
		this.startEvent = startEvent;
		this.events = new ArrayList<EnvironmentalCue>();
		this.adrenalines = new ArrayList<Integer>();
		this.status = Status.PRESENT;
	}
	
	public void terminate (Event endEvent) {
		this.endEvent = endEvent;
		this.status = Status.PAST;
	}
	
	public void addEnvironmentalCue (EnvironmentalCue event) {
		this.events.add(event);
	}
	
	public void addAdrenaline (Adrenaline adrenaline) {
		this.adrenalines.add(adrenaline.getLevel());
	}
	
	public void addAdrenaline (int adrLevel) {
		this.adrenalines.add(adrLevel);
	}
	
	public ArrayList<EnvironmentalCue> getEnvironmentalCues() {
		return events;
	}
	
	public ArrayList<Integer> getAdrenalines() {
		return adrenalines;
	}
	
	public Status getStatus() {
//		System.out.println("----------------------------Meu status eh: " + this.status);
		return this.status;
	}
	
	public Event getStartEvent() {
		return startEvent;
	}
	
	public Event getEndEvent() {
		return endEvent;
	}
}
