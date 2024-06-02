package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigInteger;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gamePanel;
	KeyHandler keyH;	
	
	public final int screenX; // use as players coordinate
	public final int screenY; // use as players coordinate
	
	public int hashKey = 0;
	
	
	
	
	// RESPONSIBLE FOR PLAYER'S CENTERING THE CAMERA WITHIN THE PLAYER AFTER ITS COORDINATES WITHIN THE MAP IS REVEALED BY setDefaultValue()
	public Player(GamePanel gamePanel, KeyHandler keyH) {
		
		this.gamePanel = gamePanel;
		this.keyH = keyH;
	// camera  = ensures that the camera is centered - this is added ensuring that the player is actually on the center of the screen, not the left corner of its picture (see video#5)
		screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2); // 384 - 24 = 360
		screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2); // 288 - 24 = 264
		
	// variable pre-defined on the entity data
	// the this block does is basically set the rectangle that will be use as an indicator for collision, if player is hitting any object, size in accordance to player character sprite
	// do remember that the rectangle is intended to be smaller than the player's character
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	// RESPONSIBLE FOR PLAYER'S COORDINATES WITHIN THE WHOLE MAP / TEXT FILE
	public void setDefaultValues() {
		
		// these variables are pre-declared on the 'Entity Class' 
		// these are use to set player's position within the whole map/text file
		worldX = gamePanel.tileSize * 23; // 48 * 23 = 1,104
		worldY = gamePanel.tileSize * 21;
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
				
			} 
					
			else if (keyH.downPressed == true) {
				direction = "down";
				
			}
					
			else if (keyH.leftPressed == true) {
				direction = "left";
				
			}
					
			else if (keyH.rightPressed == true) {
				direction = "right";
				
			}
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gamePanel.cChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			int objIndex = gamePanel.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
		
			
			
			
			
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if (collisionOn == false) {
				switch(direction) {
				case "up":
					worldY = worldY - speed;
					break;
					
				case "down":
					worldY = worldY + speed;
					break;
					
				case "left":
					worldX = worldX - speed;
					break;
					
				case "right":
					worldX = worldX + speed;
					break;
						
				}
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
	
	public void pickUpObject(int i) {
		
		
		if(i != 999) {
			
			String objectName  = gamePanel.sObj[i].name;
			
			switch(objectName) {
			case "Key":
				gamePanel.playSE(1);
				hashKey++;
				gamePanel.sObj[i] = null;
				gamePanel.ui.showMessaege("NIGGA");
				break;
				
			// indicates that if the user has a key the door will disappear
			case "Door":
				if(hashKey > 0) {
					gamePanel.playSE(3);
					gamePanel.sObj[i] = null;
					gamePanel.ui.showMessaege("A NIGGA opened a door");
					hashKey--; 
				} else {
					gamePanel.ui.showMessaege("A NIGGA can't open a door");
				}
				break;
				
			case "Boots":
			    gamePanel.playSE(2);
			    speed += 2;
			    gamePanel.sObj[i] = null;
			    gamePanel.ui.showMessaege("SPEEDY SHIT MY NIGGA");
			    break;
			    
			case "Chest":
				gamePanel.ui.gameFinished = true;
				gamePanel.stopMusic();
				gamePanel.playSE(4);
				break;
			}
			
			
			System.out.println("Key: "+hashKey);
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
		g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
		
	}
	
	public void movementLimiter() {
		
	}
	
	
}
