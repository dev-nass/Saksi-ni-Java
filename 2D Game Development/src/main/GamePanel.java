package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entities.Player;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	final int originalTileSize = 16; // sets every object (pixelated arts) to 16x16
	final int scale = 3; // will be used to multiply on the originalTileSize
	// So basically 16px objects are so small due to large modern monitor.
	// 16 * 3 = 48, common scaling. Make the 16px characters looks 48px
	public final int tileSize = originalTileSize * scale;  // sets every object (pixelated arts) to 48x48
	
	// So Basically, screen, especially in games, are made up of grids that are use as an indicator of pixels
	// these variables are use to set that vertical and horizontal grids
	public final int maxScreenCol = 16; // indicates that the screen is 16px wide
	public final int maxScreenRow = 12; // indicates that the screen is 12px long
	
	public final int screenWidth = maxScreenCol * tileSize; // 768px screen horizontal size
	public final int screenHeight = maxScreenRow * tileSize; // 576px screen vertical size
	
	// FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	// Creates the object for the KeyHandler Class created 
	KeyHandler keyH = new KeyHandler();
	// Important concept of 2D game is it needs to have TIME, so as in real life
	// this concept of TIME allows the game to run all the time while waiting for the users inputs or processing the inputs
	Thread gameThread; // automatically calls the run method
	
	Player player = new Player(this,keyH); //instantiates the player - passing THIS GamePanel class and the keyHandler VK that's being handle
	
	
	// !! MAIN CONSTRUCTOR
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // enables the game rendering performance
		this.addKeyListener(keyH); // adds the key handler on the GamePanel Class
		this.setFocusable(true); // enables the 'GamePanel class' to be focused on receiving inputs, just read it on his captions
	}

	public void StartGameThread() {
		
		gameThread = new Thread(this); // passes the whole GamePanel class to this object
		gameThread.start(); // calls the run method below, this part is on the video#1
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		// uses nanoseconds - see the video#2 for this
		double drawInterval = 1000000000/FPS; // ensures that the screen will refresh for every 0.01666 seconds
		double delta = 0;
		long lastTime = System.nanoTime(); // 1Billion nanoseconds = 1 seconds, use for very accurate indicator of time
		long currentTime;
		long timer = 0; // for the FPS
		int drawCount = 0; // for the FPS
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime(); // checks the current time
			
			delta += (currentTime - lastTime) / drawInterval; 
			timer += (currentTime - lastTime); // for the FPS
			lastTime = currentTime;
			
			// application of the added variables above the allows intervals between update() and repaint()	 methods
			if (delta >= 1) {
				// This method will do two(2) functionalities:
				// 1. UPDATE: update the information on the screen such as the players X, Y coordinates or position
				update();
				// 2. DRAW: draw the screen with the update information
				repaint();
				delta--;
				drawCount++; // for the FPS
			}
			
			// this is mainly for the FPS
			if (timer >= 1000000000) {
				System.out.println("FPS: "+	drawCount);
				drawCount = 0;
				timer = 0;
			}
			
			
			
		}
	}
	
	// responsible for updating the information on the screen (X, Y) position
	public void update() {
		player.update();	
	}
	
	// responsible for the graphics after the update has been made
	public void paintComponent(Graphics g) {
		// Standard method call below of you want to use paintComponent on JPanel
		// super means calling the method of the superclass(parent), 
		// since this GamePanel class extends the JPanel meaning it is a child class of JPanel,  while JPanel being the parent
		super.paintComponent(g);
		
		// Passes the graphics G to grahics2D class, just made this explanation up
		Graphics2D g2d = (Graphics2D)g;
		
		tileM.draw(g2d);
		
		player.draw(g2d);
		
		g2d.dispose();
	}
}
