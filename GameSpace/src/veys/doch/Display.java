package veys.doch;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import input.KeyBoard;


public class Display {
	
	public int width,height;
	public String TITLE;
	
	private JFrame frame;
	private Canvas canvas;
	private KeyBoard keyboard;
	
	
	
	public Display(int width,int height , String TITLE){
		this.width = width;
		this.height = height;
		this.TITLE = TITLE;
		
		createDisplay();
	}
	
	public void createDisplay(){
		
		
		frame = new JFrame();
		frame.setSize(width,height);
		frame.setTitle(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
		
		canvas = new Canvas();
		keyboard = new KeyBoard();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		frame.add(canvas);
		canvas.addKeyListener(keyboard);
		canvas.requestFocus();
		frame.pack();
	}
	public void update(){
		
		keyboard.update();
		
	}
	
	public Canvas getCanvas(){
		return canvas;
	}

}
