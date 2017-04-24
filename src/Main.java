package fr.utbm;

public class Main {

	public static void main(String[] args) {
		IOManager myIO;
		
		/* The choice of a graphical interface or a console is made with the argument of the main function */
		if (args.length > 0){
			switch (args[0]){
				case "-c": 
					myIO = new ConsoleManager();
					break;
				default: 
					myIO = new GraphicManager();
					break;
			}
		}else{
			myIO = new GraphicManager();
		}
		
		/* Initialization of the variables with the menus */
		myIO.init();
		int size = myIO.menu1();
		int game = myIO.menu2();
		
		Board myBoard;
		GameManager myGame;
		myBoard = new BoardSimple(size, myIO);
		
		switch (game){
			case 0: 
				myGame = new AtariGoManager(myBoard, myIO);
				break;
			default : 
				myGame = new GoManager(myBoard, myIO);
				break;
		}
		
		/* Launch of the game */
		//myIO.printBoard(myBoard);
		//Position p = myIO.getPosition(myBoard);
		//myBoard.move(p, Color.DARK);
		myGame.game(myBoard, myIO);
			
	}

}
