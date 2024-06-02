package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {

	GamePanel gamePanel;
	Font Arial_40, Arial_80B;
	BufferedImage keyImage;
	public boolean messageOn = false; // indicates that the message is off
	public String message = ""; // variable will be use to indicate a message on the screen
	public int messageCounter = 0; // use to time how long the message can appear on the screen
	public boolean gameFinished = false;
	
	double playTime;
	DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gamePanel) {
		
		this.gamePanel = gamePanel;
		Arial_40 = new Font("Arial", Font.PLAIN, 40);
		Arial_80B = new Font("Arial", Font.BOLD, 50);
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
	}
	
	public void showMessaege(String text) {
		
		message = text;
		messageOn = true;
	
	}
	
	public void draw(Graphics2D g2) {
		
		if(gameFinished == true) {
			
			g2.setFont(Arial_40);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;	
			
			// CONGRATULATION AT THE TOP
			text = "MY NIGS JUST FNISHED THIS SHIT???";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // returns the length of the text set as a congratulation message
			x = gamePanel.screenWidth/2 - textLength/2;
			y = gamePanel.screenHeight/2 - (gamePanel.tileSize*3);
			g2.drawString(text, x, y);
			
			// PLAY THIS FOR FNISHING THE GAME
			text = "YOUR TIME IS: "+decimalFormat.format(playTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // returns the length of the text set as a congratulation message
			x = gamePanel.screenWidth/2 - textLength/2;
			y = gamePanel.screenHeight/2 + (gamePanel.tileSize*4);
			g2.drawString(text, x, y);
			
			// CONGRATULATION AT THE BOTTOM
			g2.setFont(Arial_80B);
			g2.setColor(Color.yellow);
			text = "WELL CONGRATS, MY REAL NIGGGER";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // returns the length of the text set as a congratulation message
			x = gamePanel.screenWidth/2 - textLength/2;
			y = gamePanel.screenHeight/2 + (gamePanel.tileSize*3);
			g2.drawString(text, x, y);
			
			gamePanel.gameThread = null;
			
		}
		else {
			g2.setFont(Arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null); // responsible for placing a key image on the top left corner
			g2.drawString("x "+gamePanel.player.hashKey, 74, 65); // responsible for the letter X beside the keyImage + the quantity of the key the player obtained
			
			// TIME
			playTime +=(double)1/60;
			g2.drawString("Time: "+decimalFormat.format(playTime), gamePanel.tileSize*11, 65);
			
			// DISPLAYING MESSAGE
			if(messageOn == true) {
				
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gamePanel.tileSize/2, gamePanel.tileSize*5);
				
				messageCounter++;
				
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
		
		
		
		 
	}
	
}
