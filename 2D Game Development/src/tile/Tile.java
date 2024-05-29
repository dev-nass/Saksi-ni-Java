package tile;

import java.awt.image.BufferedImage;

public class Tile {
	
	// Since Tile class and TileManager class are both in the same package, Time Manager class has now an access to this BufferedImage variable
	public BufferedImage image;
	public boolean collision = false;
	
}
