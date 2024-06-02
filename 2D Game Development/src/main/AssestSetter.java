package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssestSetter {
	
	GamePanel gamePanel;
	
	public AssestSetter(GamePanel gamePanel) {
		
		this.gamePanel = gamePanel;
	}
	
	// PLACES OBJECT WITHIN THE MAP
	public void setObject() {
		
		gamePanel.sObj[0] = new OBJ_Key();
		gamePanel.sObj[0].worldX = 23 * gamePanel.tileSize; // the coordinates is within column 23 within the text file map
		gamePanel.sObj[0].worldY = 7 * gamePanel.tileSize;	// the coordinate is within row 7 on the text file map
		
		gamePanel.sObj[1] = new OBJ_Key();
		gamePanel.sObj[1].worldX = 23 * gamePanel.tileSize;
		gamePanel.sObj[1].worldY = 40 * gamePanel.tileSize;
		
		gamePanel.sObj[2] = new OBJ_Key();
		gamePanel.sObj[2].worldX = 38 * gamePanel.tileSize;
		gamePanel.sObj[2].worldY = 8 * gamePanel.tileSize;
		
		gamePanel.sObj[3] = new OBJ_Door();
		gamePanel.sObj[3].worldX = 10 * gamePanel.tileSize;
		gamePanel.sObj[3].worldY = 11 * gamePanel.tileSize;
		
		gamePanel.sObj[4] = new OBJ_Door();
		gamePanel.sObj[4].worldX = 8 * gamePanel.tileSize;
		gamePanel.sObj[4].worldY = 28 * gamePanel.tileSize;
		
		gamePanel.sObj[5] = new OBJ_Door();
		gamePanel.sObj[5].worldX = 12 * gamePanel.tileSize;
		gamePanel.sObj[5].worldY = 22 * gamePanel.tileSize;
		
		gamePanel.sObj[6] = new OBJ_Chest();
		gamePanel.sObj[6].worldX = 10 * gamePanel.tileSize;
		gamePanel.sObj[6].worldY = 7 * gamePanel.tileSize;
		
		gamePanel.sObj[7] = new OBJ_Boots();
		gamePanel.sObj[7].worldX = 37 * gamePanel.tileSize;
		gamePanel.sObj[7].worldY = 42 * gamePanel.tileSize;
		
	}
	
	
}
