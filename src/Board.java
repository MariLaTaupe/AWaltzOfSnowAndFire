package fr.utbm;

public interface Board {
	
	public Case getCase(Position position);
	
	public void setCase(Position p, Case c);
	
	public int getSize();
	
	public boolean isSkip();
	
	public void skip(boolean b);
	
	public int getStone();
	
	public boolean hasPrisoners();
	
	public boolean move(Position position, Color color);
	
	public void removeStone();
	
	public boolean koRule(Board lastBoard);
		
	public void prisonersRules(Case c);
	
	public Board copy();
}
