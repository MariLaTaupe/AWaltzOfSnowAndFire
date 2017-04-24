package fr.utbm;

public class Case {
	
	private Position position;
	private Color color;
	
	/* Constructor */
	public Case(Position position){
		this.position = position;
		this.color = Color.NEUTRAL;
	}
	
	public Case(Position position, Color color){
		this.position = position;
		this.color = color;
	}
	
	/* Setters & getters */
	public Position getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Case copy(){
		return new Case(position, color);
	}

}
