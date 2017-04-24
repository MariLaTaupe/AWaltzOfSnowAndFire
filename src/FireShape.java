package fr.utbm;

import java.awt.Graphics;
import java.awt.Color;

public class FireShape implements Shape {
	
	private int x;
	private int y;
	
	/* Constructor */
	public FireShape(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/* Getters */
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	/* Function draw a red Shape */
	public void draw(Graphics g){
		g.setColor(Color.red);
		g.fillOval((x+1)*32 + 153, (y+1)*32 + 5, 30, 30);
	}
	
}
