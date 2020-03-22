package state;

import java.awt.Graphics;

import gameObjects.Player;
import math.Vector2D;
import veys.doch.loader.Assets;

public class GameState {
	
	private Player player;
	
	
	public GameState(){
		
		player = new Player(new Vector2D(100,500), new Vector2D(), 5, Assets.player);
	}
	
	public void update(){
		
		player.update();
		
	}
	
	public void draw(Graphics g){
	
		player.draw(g);
	}

}
