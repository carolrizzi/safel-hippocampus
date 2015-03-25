package uk.ac.kent.cs.model.event;

public class Adrenaline extends Event{

	private static final long serialVersionUID = 1L;
	private Double level = 0.0; 
	
	public Adrenaline(double level) {
		this.setLevel(level);
	}
	
	public double getLevel() {
		return level;
	}
	
	public void setLevel(double level) {
		if(level > 1) this.level = 1.0;
		else if (level < 0) this.level = 0.0;
		else this.level = level;
	}
}
