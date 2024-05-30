package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[] [];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/Maps/world01.txt");
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/earth.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/sand.png"));
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Responsible for the tile pattern use in the map
	// Expected Argument is added so whenever another map is wanted to be added it easy to just pass its directory path
	public void loadMap(String mapPath) {
		
		try {
			// Inputs the file text on the stream
			InputStream is = getClass().getResourceAsStream(mapPath);
			// This then reads what's on the stream
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				// read the first line within the text file, and put it inside the String variable
				String line = br.readLine();
				
				while (col < gp.maxWorldCol) {
					
					// this then stores each numbers that's contain within the line variable, the (0, 1, 0) in accordance to the text file, because
					// it automatically splits them by spaces. So the retrieval of the letter/numbers becomes individual
					String numbers[] = line.split(" ");
					
					// Converts the collected String of letter into integers
					int num = Integer.parseInt(numbers[col]);
					
					// Hold all the now integers into an integer Array variable one at a time
					mapTileNum[col][row] = num;
					col++; // this one is use to automatically place the, one at a time collected numbers, into different indexes within the array
					
				}
				// if the COL variables becomes equals to 16, which is the value of the maxScreenCol
				if (col == gp.maxWorldCol) {
					// COL is reset again on the 0 value, meaning, it will be use again as 0 or first index of the mapTileNum array
					col = 0;
					// this code is added ensuring that the mapTileNum will stores the numbers on the next ROW
					row++;
				}
			}
			br.close();
			
		} catch(Exception e) {
			
		}
		
	}
	
	// Responsible for rendering the set map pattern
	public void draw(Graphics2D g2) {
		
		// use as arguments for mapTileNum or printing what tile on that array index
		int worldCol = 0;
		int worldRow = 0;
		
		
		// So basically, this WHILE loop fills the tiles horizontally (-) first:
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			// the [worldCol][worldRow] arguments are just there to retrieve what value of number are there in that index of the 2D array named mapTileNum
			// so "what number does the mapTileNum stores in the of index [0] [0], that's 0 base on the text file --
			int tileNum = mapTileNum[worldCol][worldRow];
			// -- this 0 is then passed on the tileNum variable. Then tileNum becomes an argument to access what pre-defined image that are there in tile[0] -- that's graasv2
			
			// worldX and worldY are use for tile position too within the map. noticeable by the comment I don't 100% know the functions of these variables
			int worldX = worldCol * gp.tileSize; // 0 * 48 = 0
			int worldY = worldRow * gp.tileSize; // 0 * 48 = 0
			
			// screnX and Y are use as coordinates for drawing tiles position within the whole map
			// whole world tilePosition = tile position - player's position within the text file / whole Map + ensures that the player on the center of the screen a.k.a Camera
			// this line of code return NEGATIVE value, pass its value on screenX and screenY, ensuring that the loop prints the necessary tiles away from the user position 
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			// this does, whenever the centered player walks along, the program only renders or prints those tiles that within its vicinity, see video#5
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				// arguments as follows: image to be use, x and y coordinates, width, height, idk.
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			
			
			worldCol++;
		
		
	   // then as the horizontal line is filled indicating in this if and else statement that 
	   // if worldCol becomes = 16, equal to screen width which is 16 too.
	   // the Y variable increases causing the placement of the tile to go vertically ( | ) for once
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
			
				worldRow++;
				
			}
		}
		
	}
	
	
}
