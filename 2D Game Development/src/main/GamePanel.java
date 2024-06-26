package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entities.Player;
import object.SuperObject;
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
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int maxWidth = tileSize * maxWorldCol; // these variables are unused
	public final int maxHeight = tileSize * maxWorldRow; // these variables are unused
	
	// FPS
	int FPS = 60;
	
	// SYSTEM
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(); // Creates the object for the KeyHandler Class created 
	Sound soundTheme = new Sound();
	Sound soundEffect = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssestSetter aSetter = new AssestSetter(this);
	public UI ui = new UI(this);
	// Important concept of 2D game is it needs to have TIME, so as in real life
	// this concept of TIME allows the game to run all the time while waiting for the users inputs or processing the inputs
	Thread gameThread; // automatically calls the run method	
	
	
	// ENTITY AND OBJECT
	public Player player = new Player(this,keyH); //instantiates the player - passing THIS GamePanel class and the keyHandler VK that's being handle
	public SuperObject sObj[] = new SuperObject[10]; // Create java object for object class -- the argument states that array object can be use to store 10 different kind of items
	
	// !! MAIN CONSTRUCTOR
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // enables the game rendering performance
		this.addKeyListener(keyH); // adds the key handler on the GamePanel Class
		this.setFocusable(true); // enables the 'GamePanel class' to be focused on receiving inputs, just read it on his captions
	}
	
	// called before the game start, the gameThread below
	public void setupGame() {
		
		aSetter.setObject();
		
		playMusic(0);
	}

	public void StartGameThread() {
		
		gameThread = new Thread(this); // passes the whole GamePanel class to this object
		gameThread.start(); // calls the run method below, this part is on the video#1
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		// uses nanoseconds - see the video#2 for this
		double drawInterval = 1000000000/60; // ensures that the screen will refresh for every 0.01666 seconds
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
		
		// TILE
		tileM.draw(g2d);
		
		// OBJECTS
		for (int i = 0; i < sObj.length; i++) {
			if (sObj[i] != null) {
				sObj[i].draw(g2d, this);
			}
		}
		
		// PLAYER
		player.draw(g2d);
		
		// UI 
		ui.draw(g2d);
		g2d.dispose();
	}
	
	// RESPONSIBLE FOR PLAYING THE MAIN THEME SONG OF THE GAME, IN A LOOP THAT IS
	public void playMusic(int i) {
		
		soundTheme.setFile(i); // this argument is use to ensure that the BlueBoyAdventure.mav is used. the value is passed above in setupGame()
		soundTheme.play();
		soundTheme.loop(); // the whole music of the game has to be loop
		
	}
	
	public void stopMusic() {
		
		soundTheme.stop();
	}
	
	// RESPONSIBLE FOR PLAYING THE SOUND EFFECTS
	public void playSE(int i) {
		
		soundEffect.setFile(i);
		soundEffect.play();
		
	}
	
	
}
