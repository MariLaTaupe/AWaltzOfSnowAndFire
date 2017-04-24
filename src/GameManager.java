package fr.utbm;

public interface GameManager {
	
	public boolean turn(Gamer gamer);
	public void game(Board board, IOManager io);

}
