package fr.utbm;

public interface IOManager {
	
	public void init();
	
	public int menu1();
	
	public int menu2();
	
	public void printBoard(Board board, boolean b);
	
	public void printCase(Position position, Color color);
	
	public Position getPosition(Board board);
	
	public void end();
	
	public int reviewMode(Board board);

	public void newTurn(Color color);

	void endReviewMode();

}
