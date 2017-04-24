package fr.utbm;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

import java.io.IOException;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Grid19 extends JPanel implements MouseListener, Grid {
	
	private static final long serialVersionUID = -3401619678556109739L;
	private PositionCallable positionCallable = new PositionCallable();
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Image fireImg;
	private Image snowImg;
	protected String str1 = "Skip";
	protected String str2 = "Skip";

	/* Constructor & initialization */
	public Grid19(){
		addMouseListener(this);
		
		try {
			this.fireImg = ImageIO.read(this.getClass().getResourceAsStream("/fireprofil.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.snowImg = ImageIO.read(this.getClass().getResourceAsStream("/snowprofil.png"));	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PositionCallable getCallable(){
		 return this.positionCallable;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int i = 0;
		
		g.drawImage(fireImg, 20, 20, this);
		
		g.setColor(Color.black); /* The first button */
		g.drawRect(51, 295, 65, 17);
		g.setColor(Color.red);
		g.fillRect(52, 296, 64, 16);
		g.setColor(Color.black);
		g.drawString(str1, 65, 296+13);
		
		g.drawRect(168,20,640,640);
		
		for(i=0; i<19; i++){
			g.drawLine(200 + 32*i,20,200 + 32*i,660); /* The grid */
			g.drawLine(168,52 + 32*i,808,52 + 32*i);
		}
		
		for(Shape s : shapes){
			s.draw(g);
		}
		
		g.drawImage( snowImg, 828, 20, this);
		
		g.setColor(Color.white); /*The second button */
		g.drawRect(859, 295, 65, 17);
		g.setColor(Color.blue);
		g.fillRect(860, 296, 64, 16);
		g.setColor(Color.white);
		g.drawString(str2, 828+45, 296+13);
	}

	/* Add & Remove */
	public void addShape(Shape newShape){
		this.shapes.add(newShape);
	}
	
	public void removeShape(Position p){
		ArrayList<Shape> prisoners = new ArrayList<Shape>();
		for(Shape s : shapes){
			if(p.getX() == s.getX() && p.getY() == s.getY()){
				prisoners.add(s);
			}
		}
		
		for(Shape s : prisoners){
			shapes.remove(s);
		}
	}

	/* Listener */
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = -1;
		int y = -1;
		
		if (e.getX() > 184 && e.getX() < 824 && e.getY() > 36 && e.getY() < 644){ /* The Grid */
			x = (e.getX() - 184) /32;
			y = (e.getY() - 36) /32;
		}
		
		if (e.getX() > 52-1 && e.getX() < 52+65 && e.getY() > 296-1 && e.getY() < 296+17){ /* The first button */
			x = -1;
			y = -1;
		}
		
		if (e.getX() > 860-1 && e.getX() < 860+65 && e.getY() > 296-1 && e.getY() < 296+17){ /* The second button */
			x = -1;
			y = -1;
		}
		Position newP = new Position(x , y);
		this.positionCallable.setPosition(newP);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}