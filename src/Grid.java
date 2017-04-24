package fr.utbm;

import java.awt.Graphics;

public interface Grid {
	
	public PositionCallable getCallable();
	
	public void paintComponent(Graphics g);
	
	public void addShape(Shape newShape);
	
	public void removeShape(Position p);
	
}
