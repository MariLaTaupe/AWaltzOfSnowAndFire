package fr.utbm;

import java.util.ArrayList;
import java.util.List;

public class History {

	private List<Board> history;
	protected IOManager io;
	
	/* Constructor */
	public History(IOManager io){
		history = new ArrayList<Board>();
		this.io = io;
	}
	
	/* Setters & getters */
	public void add(Board newBoard){
		history.add(newBoard);
	}
	
	public boolean hasSecondToLast(){
		return history.size() -2 > -1;
	}
	
	public Board getSecondToLast(){
		return history.get(history.size() -2);
	}
	
	public boolean hasLast(){
		return history.size() -1 > -1;
	}
	
	public Board getLast(){
		return history.get(history.size() -1);
	}
	
	
	
	/* Review mode */
	public void reviewMode(Board board){
		int i = this.history.size() - 3;
		int x = 0;
		x = this.io.reviewMode(board);
		while (x != 0){ // 0 = end of the application
			switch (x){
				case -1: // -1 = previous move
					if (i > 0){
						i=i-1;
					}
					break;
				case 1: // 1 = next move
					if (i < history.size()-3){
						i=i+1;
					}
					break;
			}
			io.printBoard(history.get(i), false);
			x = this.io.reviewMode(board);
		}
		this.io.endReviewMode();
	}
	
}
