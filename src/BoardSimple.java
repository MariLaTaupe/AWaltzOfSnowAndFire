package fr.utbm;

import java.util.ArrayList;

public class BoardSimple implements Board {
	
	private Case[][] board;
	private int size;
	private IOManager io;
	private boolean skip;
	private int stones;
	private boolean hasPrisoners;
	
	/* Constructor */
	public BoardSimple(int size, IOManager io){
		this.size = size;
		board = new Case[size][size];
		int i = 0;
		for (i=0; i<size; i++){
			int j = 0;
			for (j=0; j<size; j++){
				Position position = new Position (i,j);
				board[i][j] = new Case(position);
			}
		}
		this.skip = false;
		this.hasPrisoners = false;
		this.io = io;
		this.stones = size * size;
	}
	
	/* Setters & getters */
	@Override
	public Case getCase(Position position){
		return board[position.getX()][position.getY()];
	}

	@Override
	public void setCase(Position p, Case c){
		board[p.getX()][p.getY()].setColor(c.getColor());
	}
	
	@Override
	public int getSize(){
		return this.size;
	}

	@Override
	public boolean isSkip(){
		return this.skip;
	}

	@Override
	public void skip(boolean b){
		this.skip = b;
	}

	@Override
	public int getStone(){
		return this.stones;
	}

	@Override
	public boolean hasPrisoners(){
		return this.hasPrisoners;
	}

	@Override
	public boolean move(Position position, Color color){
		if (board[position.getX()][position.getY()].getColor() == Color.NEUTRAL && color != Color.NEUTRAL){
			board[position.getX()][position.getY()].setColor(color);
			io.printCase(position, color);
			return true;
		}else{
			if(board[position.getX()][position.getY()].getColor() != Color.NEUTRAL && color == Color.NEUTRAL){
				board[position.getX()][position.getY()].setColor(color);
				io.printCase(position, color);
				return true;
			}else{
				return false;
			}
		}
		
	}

	@Override
	public void removeStone(){
		this.stones = this.stones - 1; 
	}

	@Override
	public Board copy() {
		int i = 0;
		int j = 0;
		
		Board newB = new BoardSimple(size, io);
		Position p;
		for (i=0; i<size; i++){
			for (j=0; j<size; j++){
				p = new Position(i,j);
				newB.setCase(p, this.board[i][j].copy());				
			}
		}	
		newB.skip(this.isSkip());
		return newB;
	}
	
	/* The rules of the go game */
	@Override
	public boolean koRule(Board lastBoard){
		return this.equals(lastBoard);
	}

	@Override
	public void prisonersRules(Case c){
		if(hasCaseLeft(c) && caseLeft(c).getColor() != Color.NEUTRAL && caseLeft(c).getColor() != c.getColor()){
			prisoners(caseLeft(c));
		}
		
		if(hasCaseDown(c) && caseDown(c).getColor() != Color.NEUTRAL && caseDown(c).getColor() != c.getColor()){
			prisoners(caseDown(c));
		}
		
		if(hasCaseRight(c) && caseRight(c).getColor() != Color.NEUTRAL && caseRight(c).getColor() != c.getColor()){
			prisoners(caseRight(c));
		}
		
		if(hasCaseUp(c) && caseUp(c).getColor() != Color.NEUTRAL && caseUp(c).getColor() != c.getColor()){
			prisoners(caseUp(c));
		}
	}
	
	private void prisoners(Case c){
		ArrayList<Case> toTraverse = new ArrayList<Case>();
		ArrayList<Case> alreadyTraversed = new ArrayList<Case>();
		ArrayList<Case> prisoners = new ArrayList<Case>();
		boolean bPrisoners = true;
		
		prisoners.add(c);
		alreadyTraversed.add(c);
		toTraverse.add(c);
		
		while(!toTraverse.isEmpty() && bPrisoners){ 
			
			if(hasCaseLeft(toTraverse.get(0))){
				if(!alreadyTraversed.contains(caseLeft(toTraverse.get(0)))){
					switch(caseLeft(toTraverse.get(0)).getColor()){
						case NEUTRAL:
							bPrisoners = false;
							this.hasPrisoners = false;
							break;
						case DARK:
							if (c.getColor() == Color.DARK){
								prisoners.add(caseLeft(toTraverse.get(0)));
								alreadyTraversed.add(caseLeft(toTraverse.get(0)));
							}else{
								alreadyTraversed.add(caseLeft(toTraverse.get(0)));
							}
							break;
						case WHITE:
							if (c.getColor() == Color.WHITE){
								prisoners.add(caseLeft(toTraverse.get(0)));
								alreadyTraversed.add(caseLeft(toTraverse.get(0)));
							}else{
								alreadyTraversed.add(caseLeft(toTraverse.get(0)));
							}
							break;
					}
				}			
			}
			
			if(this.hasCaseDown(toTraverse.get(0))){
				if(!alreadyTraversed.contains(caseDown(toTraverse.get(0)))){
					switch(caseDown(toTraverse.get(0)).getColor()){
						case NEUTRAL:
							bPrisoners = false;
							this.hasPrisoners = false;
							break;
						case DARK:
							if (c.getColor() == Color.DARK){
								prisoners.add(caseDown(toTraverse.get(0)));
								alreadyTraversed.add(caseDown(toTraverse.get(0)));
							}else{
								alreadyTraversed.add(caseDown(toTraverse.get(0)));
							}
							break;
						case WHITE:
							if (c.getColor() == Color.WHITE){
								prisoners.add(caseDown(toTraverse.get(0)));
								alreadyTraversed.add(caseDown(toTraverse.get(0)));
							}else{
								alreadyTraversed.add(caseDown(toTraverse.get(0)));
							}
							break;
					}
				}			
			}
			
			if(this.hasCaseRight(toTraverse.get(0))){
				if(!alreadyTraversed.contains(caseRight(toTraverse.get(0)))){
					switch(caseRight(toTraverse.get(0)).getColor()){
						case NEUTRAL:
							bPrisoners = false;
							this.hasPrisoners = false;
							break;
						case DARK:
							if (c.getColor() == Color.DARK){
								prisoners.add(caseRight(toTraverse.get(0)));
								alreadyTraversed.add(caseRight(toTraverse.get(0)));
							}else{
								alreadyTraversed.add(caseRight(toTraverse.get(0)));
							}
							break;
						case WHITE:
							if (c.getColor() == Color.WHITE){
								prisoners.add(caseRight(toTraverse.get(0)));
								alreadyTraversed.add(caseRight(toTraverse.get(0)));
							}else{
								alreadyTraversed.add(caseRight(toTraverse.get(0)));
							}
							break;
					}
				}			
			}
			
			if(this.hasCaseUp(toTraverse.get(0))){
				if(!alreadyTraversed.contains(caseUp(toTraverse.get(0)))){
					switch(caseUp(toTraverse.get(0)).getColor()){
						case NEUTRAL:
							bPrisoners = false;
							this.hasPrisoners = false;
							break;
						case DARK:
							if (c.getColor() == Color.DARK){
								prisoners.add(caseUp(toTraverse.get(0)));
								alreadyTraversed.add(caseUp(toTraverse.get(0)));
							}else{
								alreadyTraversed.add(caseUp(toTraverse.get(0)));
							}
							break;
						case WHITE:
							if (c.getColor() == Color.WHITE){
								prisoners.add(caseUp(toTraverse.get(0)));
								alreadyTraversed.add(caseUp(toTraverse.get(0)));
							}else{
								alreadyTraversed.add(caseUp(toTraverse.get(0)));
							}
							break;
					}
				}
				
			}
			toTraverse.remove(0);
		}

		if(bPrisoners){
			this.hasPrisoners = true;
			for(Case currentCase : prisoners){
				io.printCase(currentCase.getPosition(), Color.NEUTRAL);
				getCase(currentCase.getPosition()).setColor(Color.NEUTRAL);;
			}
		}
	}

	/* Function use by prisonnersRule */
	public Case caseLeft(Case c){
		Position position = c.getPosition();
		return board[position.getX()-1][position.getY()];
	}
	
	public Case caseDown(Case c){
		Position position = c.getPosition();
		return board[position.getX()][position.getY()+1];
	}
	
	public Case caseRight(Case c){
		Position position = c.getPosition();
		return board[position.getX()+1][position.getY()];
	}
	
	public Case caseUp(Case c){
		Position position = c.getPosition();
		return board[position.getX()][position.getY()-1];
	}
	
	public boolean hasCaseLeft(Case c){
		Position position = c.getPosition();
		if (position.getX() == 0){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean hasCaseDown(Case c){
		Position position = c.getPosition();
		if (position.getY() == this.size-1){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean hasCaseRight(Case c){
		Position position = c.getPosition();
		if (position.getX() == this.size-1){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean hasCaseUp(Case c){
		Position position = c.getPosition();
		if (position.getY() == 0){
			return false;
		}else{
			return true;
		}
	}
	
}
