package fr.utbm;

public class AtariGoManager extends GoManager implements GameManager {
	
	public AtariGoManager(Board board, IOManager io){
		super(board, io);
	}
	
	public void game(Board board, IOManager io){
		boolean b = true;
		int i = 0;
		
		io.printBoard(board, true);
		while(b){
			switch (i%2){
				case 0:
					this.turn(fire);
					break;
				case 1:
					this.turn(snow);
					break;
				default:
					break;
			}
			
			b = !(board.hasPrisoners()); // A prisoners = a victory
			i++;
		}
		io.end();
		history.reviewMode(board);
	}
}
