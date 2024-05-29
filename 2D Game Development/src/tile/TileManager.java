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
	Tile[] tile;
	int mapTileNum[] [];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadMap("/Maps/TilesPattern.txt");
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grassv2.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grassv21.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/water.png"));
			
			
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
			
			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				// read the first line within the text file, and put it inside the String variable
				String line = br.readLine();
				
				while (col < gp.maxScreenCol) {
					
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
				if (col == gp.maxScreenCol) {
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
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		// So basically, this WHILE loop fills the tiles horizontally (-) first:
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			// the [col][row] arguments are just there to retrieve what value of number are there in that index of the 2D array named mapTileNum
			// so "what number does the mapTileNum stores in the of index [0] [0], that's 0 base on the txt file --
			int tileNum = mapTileNum[col][row];
			// -- this 0 is then passed on the tileNum variable. Then tileNum becomes an argument to access what pre-defined image that are there in tile[0] -- that's graasv2
			// arguments as follows: image to be use, x and y coordinates, width, height, idk.
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			
			col++;
			x = x + gp.tileSize;
		
	   // then as the horizontal line is filled indicating in this if and else statement that 
	   // if COL becomes = 16, equal to screen width which is 16 too.
	   // the Y variable increases causing the placement of the tile to go vertically ( | ) for once
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y = y + gp.tileSize;
			}
		}
		
	}
	
	
}
