package fr.utbm;

public class GoManager implements GameManager {
	
	protected Board board;
	protected Gamer snow;
	protected Gamer fire;
	protected History history;
	protected IOManager io;
	
	/* Constructor */
	public GoManager(Board board, IOManager io){
		this.board = board;
		this.io = io;
		this.snow = new Gamer(Color.WHITE);
		this.fire = new Gamer(Color.DARK);
		this.history = new History(io);
	}
	
	/* The function who implement one turn */
	public boolean turn(Gamer gamer){
		boolean isGameContinue = true;
		boolean isTurnContinue = true;
		
		io.newTurn(gamer.getColor());
		
		while (isTurnContinue){
			if (board.getStone() == 0){ 
				Board newBoard = board.copy();
				history.add(newBoard);
				isGameContinue = false; // End of the game
				isTurnContinue = false;
			}else{
				Position p; 
				p = io.getPosition(board); // The getter of the move of the player
				Board newBoard;
				if (p.getX() == -1 || p.getY() == -1){ // The player skip his turn
					board.skip(true);
					if (history.hasLast()){
						if (history.getLast().isSkip()){ // The previous player have skipped too
							newBoard = board.copy();
							history.add(newBoard);
							isGameContinue = false; // End of the game
							isTurnContinue = false;
						}else{
							newBoard = board.copy();
							history.add(newBoard);
							isGameContinue = true; // End of the turn
							isTurnContinue = false;
						}
					}else{
						newBoard = board.copy();
						history.add(newBoard);
						isGameContinue = true; // End of the turn
						isTurnContinue = false;
					}
				}else{
					board.skip(false);
					if (board.move(p, gamer.getColor()) == true){ // Check that we can move
						if (history.hasSecondToLast()){
							if (board.koRule(history.getSecondToLast())){ // // The ko rule
								board.move(p, Color.NEUTRAL);
								isGameContinue = true; // The turn continue
								isTurnContinue = true;
							}else{
								newBoard = board.copy();
								history.add(newBoard);
								board.removeStone(); // Invalid move
								isGameContinue = true; // End of the turn
								isTurnContinue = false;
							}
						}else{
							newBoard = board.copy();
							history.add(newBoard);
							isGameContinue = true; // End of the turn
							isTurnContinue = false;
						}
					}else{
						newBoard = board.copy();
						history.add(newBoard);
						isGameContinue = true; // The turn continue
						isTurnContinue = true;
					}
					
				
					board.prisonersRules(board.getCase(p)); // The prisoners rule
				}
			}	
		}

		return isGameContinue;
	}
	 /* The function who manage the game */
	public void game(Board board, IOManager io){
		boolean b = true;
		int i = 0;
		
		io.printBoard(board, true);
		while(b){
			switch (i%2){
			case 0:
				b = this.turn(fire);
				break;
			case 1:
				b = this.turn(snow);
				break;
			default:
				break;
			}
			
			i++;
		}
		io.end();
		history.reviewMode(board);
	}

}
