package fr.utbm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ReviewGrid13 extends JPanel implements MouseListener, ReviewGrid {

	private static final long serialVersionUID = -4073505236508430227L;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ReviewCallable reviewCallable = new ReviewCallable();
	private Image fireImg;
	private Image snowImg;
	protected String str1 = "Previous";
	protected String str2 = "Next";
	protected String str3 = "Exit";

	/* Constructor & initialization */
	public ReviewGrid13(){
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

	@Override	
	public ReviewCallable getCallable(){
		 return this.reviewCallable;
	}
	

	@Override
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
		
		g.drawRect(168,20,448,448);
		
		for(i=0; i<13; i++){
			g.drawLine(200 + 32*i,20,200 + 32*i,468); /* The grid */
			g.drawLine(168,52 + 32*i,616,52 + 32*i);
		}

		for(Shape s : shapes){
			s.draw(g);
		}
		
		g.drawImage( snowImg, 636, 20, this);
		
		g.setColor(Color.white); /*The second button */
		g.drawRect(667, 295, 65, 17);
		g.setColor(Color.blue);
		g.fillRect(668, 296, 64, 16);
		g.setColor(Color.white);
		g.drawString(str2, 636+45, 296+13);
		
		g.setColor(Color.white); /* The third button */
		g.drawRect(359, 487, 65, 17);
		g.setColor(Color.green);
		g.fillRect(360, 488, 64, 16);
		g.setColor(Color.white);
		g.drawString(str3, 373, 501);
	}

	/* Add & Remove */
	@Override	
	public void addShape(Shape newShape){
		this.shapes.add(newShape);
	}

	@Override	
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

	@Override
	public void addAllShape(Board board) {
		int i;
		int j;
		Position p;
		
		this.removeAllShape();
		
		for(i=0; i<13; i++){
			for(j=0; j<13; j++){
				p = new Position(i, j);
				switch (board.getCase(p).getColor()){
				case DARK:
					Shape newFireShape = new FireShape(p.getX(), p.getY());
					this.addShape(newFireShape);
					break;
				case WHITE:
					Shape newSnowShape = new SnowShape(p.getX(), p.getY());
					this.addShape(newSnowShape);
					break;
				default:
					break;
				
				}
			}
		}
		
	}

	@Override
	public void removeAllShape() {
		this.shapes.removeAll(shapes);
	}
	
	/* Listener */
	@Override
	public void mouseClicked(MouseEvent e){
		int x = 0;
		
		if (e.getX() > 52-1 && e.getX() < 52+65 && e.getY() > 296-1 && e.getY() < 296+17){ /* The first button */
			x = -1;
		}
		
		if (e.getX() > 668-1 && e.getX() < 668+65 && e.getY() > 296-1 && e.getY() < 296+17){ /* The second button */
			x = 1;
		}
		
		if (e.getX() > 360-1 && e.getX() < 360+65 && e.getY() > 488-1 && e.getY() < 488+17){ /* The third button */
			x = 0;
		}
		
		this.reviewCallable.setI(x);
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
