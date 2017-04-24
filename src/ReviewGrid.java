package fr.utbm;

import java.awt.Graphics;

public interface ReviewGrid {

	public ReviewCallable getCallable();
	
	public void paintComponent(Graphics g);
	
	public void addShape(Shape newShape);
	
	public void addAllShape(Board board);
	
	public void removeShape(Position p);
	
	public void removeAllShape();
	
}
