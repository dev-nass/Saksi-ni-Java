package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	public int worldX, worldY;
	public int speed;
	
	// since Entity class and Player class are both in the same package, Player class then now has an access to this Buffered Image
	public BufferedImage frontIdle, backIdle, leftIdle, rightIdle, up1, up2, down1, down2, left1, left2, right1, right2; // use as a container for images
	public String direction; // use to hold or indicate which direction the player is intended to move
	
	// Sprite mean, in the context of game development or computer graphics, refers to a 2D image that represents something, such as character or such entities within the game
	public int spriteCounter = 0;
	public int spriteNum = 1; // use to indicate which 3 part image should be use (idle, walking with left foot, walking with right foot)
	
	public Rectangle solidArea; // built in class in Java that allows you to create rectangle. will be use for collision
	public boolean collisionOn = false;
}
