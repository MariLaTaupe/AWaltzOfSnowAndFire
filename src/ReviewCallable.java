package fr.utbm;

import java.util.concurrent.Callable;

public class ReviewCallable implements Callable<Integer> {

	private Integer i = null;
	
	/* Setter */
	public void setI(int i){
		this.i = i;
	}

	/* Function call() for the implementation of callable */
	@Override
	public Integer call() throws Exception {
		this.i = null;
		while(this.i == null){
			Thread.sleep(100);
		}
		return this.i;
	}
	
}
