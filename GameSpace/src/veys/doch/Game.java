package veys.doch;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import gameObjects.Player;
import state.GameState;
import veys.doch.loader.Assets;

public class Game implements Runnable {
	
	public Display display;
	public Canvas canvas;
	public Graphics g;
	private Thread thread;
	private GameState gameState;
	
	public int averageFPS = 0;



	public int width,height;
	public String title;
	
	public BufferStrategy bs;
	
	static boolean running = false;

	
	
	public Game(int width, int height,String title){
		this.width = width;
		this.height = height;
		this.title = title;

	}
	
	public void init(){
		display = new Display(width , height , title);
		Assets.init();
		gameState = new GameState();
	}
	
	public void render(){
		bs= display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.CYAN);
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("FPS : " + averageFPS, 715, 25);
		
		
		
		gameState.draw(g);
		
		bs.show();
		g.dispose();

	
	}
	public void tick(){
		display.update();
		gameState.update();
		

	}
	
	public void run(){
		
		init();
		
		int  fps = 60;
		double timePerTicks = 1000000000 / fps;
		
		int ticks=0;
		long timer = 0;
		long lastTime = System.nanoTime();
		double delta = 0;

		long now;
	
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTicks;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				render();
				tick();
				ticks++;
				delta--;
				
			}
			
			if(timer >= 1000000000){
				System.out.println("FPS : " + ticks);
				averageFPS = ticks;
				ticks = 0;
				timer = 0;
			}
			
		}
		stop();
		
	}
	public synchronized void start(){
		
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop(){
		
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

}
