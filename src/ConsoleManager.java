package fr.utbm;

import java.util.Scanner;

public class ConsoleManager implements IOManager {
	
	public void init(){
		this.clearScreen();
		System.out.println("*-*-*-*-*-* Bienvenue dans une valse de la neige et du feu! *-*-*-*-*-* \n\n");
	}
	
	public int menu1(){
		System.out.println("Avec quel taille de plateau voulez-vous jouer ?");
		System.out.println("9 / 13 /19");
		System.out.println("Tapez votre choix puis validez");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int i = reader.nextInt();
		while (i!=9 && i!=13 && i!=19){
			System.out.println("Taille invalide! Veuillez taper votre choix et valider");
			i = reader.nextInt();
		}
		System.out.println("\n");
		return i;
	}
	
	public int menu2(){
		System.out.println("A quoi voulez vous jouer ?");
		System.out.println("0. A l'atari-go");
		System.out.println("1. Au go");
		System.out.println("Tapez votre choix puis validez");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int i = reader.nextInt();
		while (i!=0 && i!=1){
			System.out.println("Jeu introuvable! Veuillez taper votre choix et valider");
			i = reader.nextInt();
		}
		System.out.println("\n");
		return i;
		//The scanners stay open to avoid the errors link to Systeme.in
	}
	
	
	public void printBoard(Board board, boolean b){
		this.clearScreen();
		int size = board.getSize();
		int i = 0;
		for (i=0; i<size; i++){
			int j = 0;
			Position p;
			for (j=0; j<size; j++){
				p = new Position(i, j);
				switch (board.getCase(p).getColor()){
					case NEUTRAL : System.out.print("_");
						break;
					case DARK : System.out.print("F");
						break;
					case WHITE : System.out.print("S");
						break;
					default : System.out.print("_");
						break;
				}
			}
			System.out.print("\n");
		}
	}
	
	public void printCase(Position position, Color color){
		char escCode = 0x1B;
		System.out.print(String.format("%c[%d;%df", escCode, position.getX()+1, position.getY()+1));
		switch(color){
			case NEUTRAL : System.out.println("_");
				break;
				
			case DARK : System.out.println("F");
				break;
			
			case WHITE : System.out.println("S");
				break;
				
			default : System.out.println("_");
				break;
			
		}
	}
	
	public Position getPosition(Board board){
		char escCode = 0x1B;
		System.out.print(String.format("%c[%d;%df", escCode, 22, 1));
		System.out.println("Veuillez taper la position en x et en y de votre mouvement, taper 0 0 pour passer votre tour!");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int x = reader.nextInt();
		int y = reader.nextInt();
		while (x>board.getSize() || y>board.getSize() || x<0 || y<0){
			System.out.println("Saisie Invalide!");
			System.out.print(String.format("%c[%d;%df", escCode, 22, 1));
			System.out.println("Veuillez taper la position en x et en y de votre mouvement");
			x = reader.nextInt();
			y = reader.nextInt();
		}
		Position p = new Position(x-1, y-1);
		return p;
	}
	
	private void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public void end(){
		this.clearScreen();
		System.out.println("Fin du jeu!");
	}
	
	public int reviewMode(Board board){
		System.out.println("Veuillez taper -1 pour voir le coup précedent, 1 pour voir le coup suivant, 0 pour quitter le mode review");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int i = reader.nextInt();
		while (i != -1 && i != 1 && i != 0){
			System.out.println("Saisie Invalide!");
			System.out.println("Veuillez taper -1 pour voir le coup précedent, 1 pour voir le coup suivant, 0 pour quitter le mode review");
			i = reader.nextInt();
		}
		return i;
	}

	@Override
	public void newTurn(Color color) {
		char escCode = 0x1B;
		System.out.print(String.format("%c[%d;%df", escCode, 26, 1));
		switch(color){
		case DARK:
			System.out.println("Fire's turn");
			break;
		case WHITE:
			System.out.println("Snow's turn");
			break;
		default:
			break;
		
		}
		
		
	}

	@Override
	public void endReviewMode() {
		this.clearScreen();
	}

}
