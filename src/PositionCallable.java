package fr.utbm;

import java.util.concurrent.Callable;

public class PositionCallable implements Callable<Position> {
	
	private Position p;
	
	/* Setter */
	public void setPosition(Position p){
		this.p = p;
	}

	/* Function call() for the implementation of callable */
	@Override
	public Position call() throws Exception {
		this.p = null;
		while(this.p == null){
			Thread.sleep(100);
		}
		return this.p;
	}

}
