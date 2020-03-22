package veys.doch;

public class Main {
	
	public static int SCALE = 3;
	
	public static void main(String[] args){
		
		Game game = new Game((800/3)*SCALE,(600/3)*SCALE,"veys");
		game.start();
	}

}
