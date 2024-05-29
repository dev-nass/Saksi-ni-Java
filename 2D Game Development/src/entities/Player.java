package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gamePanel;
	KeyHandler keyH;	
	
	public Player(GamePanel gamePanel, KeyHandler keyH) {
		
		this.gamePanel = gamePanel;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		
		// these variables are pre-declared on the 'Entity Class' 
		x = 100; 
		y = 100;
		speed = 4;
		direction = "down";
		
	}
	
	public void getPlayerImage() {
		
		try {
			
			// IDLE IMAGES
			frontIdle = ImageIO.read(getClass().getResourceAsStream("/Player/stading-idle.png")); // pressing S, Y axis positive
			backIdle = ImageIO.read(getClass().getResourceAsStream("/Player/back-standing-idle.png")); // pressing W, Y axis negative
			leftIdle = ImageIO.read(getClass().getResourceAsStream("/Player/face-left-idle.png")); // 
			rightIdle = ImageIO.read(getClass().getResourceAsStream("/Player/face-right-idle.png"));
			
			// WALKING IMAGES
			up1 = ImageIO.read(getClass().getResourceAsStream("/Player/Front-Walk-Left.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/Player/Front-Walk-Right.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/Player/back-walk-left.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/Player/back-walk-right.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/Player/face-left-walk-left.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/Player/face-left-walk-right.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/Player/face-right-walk-left.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/Player/face-right-walk-right.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// responsible for updating the information on the screen (X, Y) position
	// this method is called within the GamePanel update() method too, yup they both have the same name
	public void update() {
		// Whenever a key is pressed, 'KeyHandler Class' will take care of it. And depending on the Virtual Key Code (VK) that's being handle, it will go here
		// this block of code ensures that the player will visually move on the screen
				
		// So basically in Java the upper left side of the screen is start's with X,Y (0, 0)
		// the X coordinates increases as they go RIGHT, and decreases as they go LEFT
		// the Y coordinate increase as they go DOWN, and decreases as they go UP
		
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
				y = y - speed;
			} 
					
			else if (keyH.downPressed == true) {
				direction = "down";
				y = y + speed;
			}
					
			else if (keyH.leftPressed == true) {
				direction = "left";
				x = x - speed;
			}
					
			else if (keyH.rightPressed == true) {
				direction = "right";
				x = x + speed;
			}
			
			spriteCounter++;
			
			if(spriteCounter > 5) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum = 3;
				}
				else if (spriteNum == 3) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}  

		
	}
	
	// responsible for the graphics after the update has been made
	public void draw(Graphics2D g2d) {
		
		//g2d.setColor(new Color(0xF5F5F5));
		// this method call takes the X and Y coordinates of the graphics you want to display, following your preferred Width and Height
		//g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
		
		BufferedImage image = null;
		
		switch (direction) {
		case "up":	
			if (keyH.upPressed == true) {
				if (spriteNum == 1) {
					image = backIdle;
				}
				if (spriteNum == 2) {
					image = down1;
				}
				if (spriteNum == 3) {
					image = down2;
				}
			} 
			
			// this is added to ensures that the image icon of player is 
			// set whenever the key is not pressed.
			else if (keyH.upPressed == false) {
				image = backIdle;
			}
			break;
			
		case "down":
			
			if (keyH.downPressed == true) {
				if (spriteNum == 1) {
					image = frontIdle;
				}
				if (spriteNum == 2) {
					image = up1;
				}
				if (spriteNum == 3) {
					image = up2;
				}
			}
			
			// this is added to ensures that the image icon of player is set whenever the key is not pressed.
			else if (keyH.downPressed == false) {
				image = frontIdle;
			}
			
			break;
			
		case "left":
			
			if (keyH.leftPressed == true) {
				if (spriteNum == 1) {
					image = leftIdle;
				}
				if (spriteNum == 2) {
					image = left1;
				}
				if (spriteNum == 3) {
					image = left2;
				}
			}
			
			// this is added to ensures that the image icon of player is set whenever the key is not pressed.
			else if (keyH.leftPressed == false) {
				image = leftIdle;
			}
			
			break;
			
		case "right":
			
			if (keyH.rightPressed == true) {
				if (spriteNum == 1) {
					image = rightIdle;
				}
				if (spriteNum == 2) {
					image = right1;
				}
				if (spriteNum == 3) {
					image = right2;
				}
			}
			
			// this is added to ensures that the image icon of player is set whenever the key is not pressed.
			else if (keyH.rightPressed == false) {
				image = rightIdle;
			}
			break;
		}
		
		// this is written to ensure the initial position of the IMAGE, in this case the character, being process.
		g2d.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
		
	}
	
}
