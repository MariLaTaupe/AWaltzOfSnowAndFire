package fr.utbm;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Dimension;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GraphicManager implements IOManager {
	
	private JFrame gameFrame = null;
	private Grid grid;
	private ReviewGrid reviewGrid;
	private ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	private ImageIcon fire1 = new ImageIcon(this.getClass().getResource("/firegagnee.png"));
	private ImageIcon fire2 = new ImageIcon(this.getClass().getResource("/firecolere.png"));
	private ImageIcon fire3 = new ImageIcon(this.getClass().getResource("/firecontente.png"));
	private ImageIcon fire4 = new ImageIcon(this.getClass().getResource("/fireinquiete.png"));
	private ImageIcon fire5 = new ImageIcon(this.getClass().getResource("/fireperdue.png"));
	
	private ImageIcon snow1 = new ImageIcon(this.getClass().getResource("/snowgagne.png"));
	private ImageIcon snow2 = new ImageIcon(this.getClass().getResource("/snowcolere.png"));
	private ImageIcon snow3 = new ImageIcon(this.getClass().getResource("/snowcontent.png"));
	private ImageIcon snow4 = new ImageIcon(this.getClass().getResource("/snowinquiet.png"));
	private ImageIcon snow5 = new ImageIcon(this.getClass().getResource("/snowperdu.png"));

	@SuppressWarnings("static-access")
	public void init() {
		JOptionPane jop = new JOptionPane();
		jop.showMessageDialog(null, "Welcome in the Waltz of Snow and Fire, \nthe application of go game and atari-go game developped \nby Prunelle Daudré--Treuil for the LP24 lessons.", "A Waltz Of Snow And Fire", JOptionPane.INFORMATION_MESSAGE, fire1);
			
	}

	@SuppressWarnings("static-access")
	public int menu1() {
		String[] choice = {"9*9", "13*13", "19*19"}; /* Choice of the size of the board */
		JOptionPane jop = new JOptionPane();
		String size = (String)jop.showInputDialog(null, "Which size of board do you want to use?", "A Waltz Of Snow And Fire", JOptionPane.INFORMATION_MESSAGE, snow3, choice, choice[0]);
		switch(size){
			case "9*9" :
				return 9;
			case "13*13" : 
				return 13;
			case "19*19" :
				return 19;
			default :
				return 0;
		}
	}

	@SuppressWarnings("static-access")
	public int menu2() {
		String[] choice = {"Go", "Atari-go"}; /* Choice of the type of game */
		JOptionPane jop = new JOptionPane();
		String size = (String)jop.showInputDialog(null, "Which game do you want to play?", "A Waltz Of Snow And Fire", JOptionPane.INFORMATION_MESSAGE, fire3, choice, choice[0]);
		switch(size){
		case "Go" :
			return 1;
		case "Atari-go" : 
			return 0;
		default :
			return -1;
		}
	}

	public void printBoard(Board board, boolean b) {
		if(b){
			if (this.gameFrame == null){ /* Game mode */
				/* Initialization of the graphical interface */
				this.gameFrame = new JFrame();
				this.gameFrame.setTitle("A Waltz of Snow and Fire");
				this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.gameFrame.getContentPane().setLayout(new BorderLayout());
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			
				switch(board.getSize()){
					case 9 : /* Grid with a size of 9*9 */
						this.gameFrame.setLocation((dim.width-656)/2, (dim.height-380)/2);
						this.gameFrame.setSize(656, 380);
						this.grid = new Grid9();
						break;
					
					case 13 : /* Grid with a size of 16*16 */
						this.gameFrame.setLocation((dim.width-784)/2, (dim.height-508)/2);
						this.gameFrame.setSize(784, 508);
						this.grid = new Grid13();
						break;
					
					case 19 : /* Grid with a size of 19*19 */
						this.gameFrame.setLocation((dim.width- 976)/2, (dim.height-700)/2);
						this.gameFrame.setSize(976, 700);
						this.grid = new Grid19();
						break;
				}
			
				this.gameFrame.setResizable(false);
				this.gameFrame.getContentPane().add((JPanel)grid, BorderLayout.CENTER);
				this.gameFrame.setVisible(true);
				
			} else { /* Graphical interface update */
				((JPanel)this.grid).invalidate();
				((JPanel)this.grid).repaint();
			}
		}else{ /* Review mode */
			if (this.gameFrame == null){
				/* Initialization of the graphical interface */
				this.gameFrame = new JFrame();
				this.gameFrame.setTitle("A Waltz of Snow and Fire");
				this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.gameFrame.getContentPane().setLayout(new BorderLayout());
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			
				switch(board.getSize()){
					case 9 : /* Grid with a size of 9*9 */
						this.gameFrame.setLocation((dim.width-656)/2, (dim.height-380)/2);
						this.gameFrame.setSize(656, 416);
						this.reviewGrid = new ReviewGrid9();
						break;
					
					case 13 : /* Grid with a size of 13*13 */
						this.gameFrame.setLocation((dim.width-784)/2, (dim.height-508)/2);
						this.gameFrame.setSize(784, 544);
						this.reviewGrid = new ReviewGrid13();
						break;
					
					case 19 : /* Grid with a size of 19*19 */
						this.gameFrame.setLocation((dim.width- 976)/2, (dim.height-700)/2);
						this.gameFrame.setSize(976, 736);
						this.reviewGrid = new ReviewGrid19();
						break;
				}
			
				this.gameFrame.setResizable(false);
				this.gameFrame.getContentPane().add((JPanel)reviewGrid, BorderLayout.CENTER);
				this.reviewGrid.addAllShape(board);
				this.gameFrame.setVisible(true);
				
			} else {/* Graphical interface update */
				this.reviewGrid.addAllShape(board);
				((JPanel)this.reviewGrid).invalidate();
				((JPanel)this.reviewGrid).repaint();
			}
		}
	}

	public void printCase(Position position, Color color) {
		switch (color){ /* A shape is the view of the Color enum of a case */
			case DARK:
				Shape newFireShape = new FireShape(position.getX(), position.getY());
				this.grid.addShape(newFireShape);
				break;
			case WHITE:
				Shape newSnowShape = new SnowShape(position.getX(), position.getY());
				this.grid.addShape(newSnowShape);
				break;
			case NEUTRAL : grid.removeShape(position);
				
			default:
				break;
		}
		
		/* Graphical interface update */
		((JPanel)this.grid).invalidate();
		((JPanel)this.grid).repaint();
	}

	public Position getPosition(Board board) {
		Future<Position> p = executorService.submit(this.grid.getCallable());
		
		while (!p.isDone()){ /* We wait for a click */
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try { /*Return the value get by the callable */
			return p.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("static-access")
	public void end() {
		
		try { /* The application must wait a little for the brain of the player */
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/* Removal of the graphical interface */
		this.gameFrame.setVisible(false);
		this.gameFrame = null;
		JOptionPane jop = new JOptionPane();
		jop.showMessageDialog(null,"Game ended, you will enter in the review mode",  "A Waltz Of Snow And Fire", JOptionPane.INFORMATION_MESSAGE, snow1);
	}

	public int reviewMode(Board board) {
		
		if(this.reviewGrid == null){
			this.printBoard(board, false);
		}
		
		Future<Integer> i = executorService.submit(this.reviewGrid.getCallable());
		
		while (!i.isDone()){ /* We wait for a click */
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try { /*Return the value get by the callable */
			return i.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public void endReviewMode(){
		/* Removal of the graphical interface */
		this.gameFrame.setVisible(false);
		this.gameFrame = null;
		
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void newTurn(Color color) {
		
		try { /* The application must wait a little for the brain of the player */
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/* We print randomly a image for the new turn's box */
		int ran = (int)(Math.random()*(5)) + 1;
		ImageIcon img;
		JOptionPane jop = new JOptionPane();
		switch(color){
			case DARK:
				switch(ran){
					case 1:
						img = fire1;
						break;
					case 2:
						img = fire2;
						break;
					case 3:
						img = fire3;
						break;
					case 4:
						img = fire4;
						break;
					case 5:
						img = fire5;
						break;
					default:
						img = fire1;
						break;
				}
				
				jop.showMessageDialog(null,"Fire's turn",  "A Waltz Of Snow And Fire", JOptionPane.INFORMATION_MESSAGE, img);
				break;
				
			case WHITE:
				switch(ran){
				case 1:
					img = snow1;
					break;
				case 2:
					img = snow2;
					break;
				case 3:
					img = snow3;
					break;
				case 4:
					img = snow4;
					break;
				case 5:
					img = snow5;
					break;
				default:
					img = snow1;
					break;
			}
				jop.showMessageDialog(null,"Snow's turn",  "A Waltz Of Snow And Fire", JOptionPane.INFORMATION_MESSAGE, img);
				break;
				
			default:
				break;
		
		}
	}

}
