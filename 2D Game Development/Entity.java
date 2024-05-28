package entities;

import java.awt.image.BufferedImage;

public class Entity {

	public int x, y;
	public int speed;
	
	public BufferedImage frontIdle, backIdle, leftIdle, rightIdle, up1, up2, down1, down2, left1, left2, right1, right2; // use as a container for images
	public String direction; // use to hold or indicate which direction the player is intended to move
	
	// Sprite mean, in the context of game development or computer graphics, refers to a 2D image that represents something, such as character or such entities within the game
	public int spriteCounter = 0;
	public int spriteNum = 1; // use to indicate which 3 part image should be use (idle, walking with left foot, walking with right foot)
	
	
}
