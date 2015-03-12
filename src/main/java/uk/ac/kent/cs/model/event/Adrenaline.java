package uk.ac.kent.cs.model.event;

public class Adrenaline extends Event{

	private static final long serialVersionUID = 1L;
	private int level = 0; 
	
	public Adrenaline(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
