package main;

import entities.Entity;

public class CollisionChecker {

	GamePanel gamePanel;
	
	public CollisionChecker(GamePanel gp) {
		
		this.gamePanel = gp;
		
	}
	
	// IMPELEMENT'S COLLISION - CHECKS WHETHER THE USER CAN PASS THROUGH A TILE
	// see video#6 for further explanations
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x; // for the shoulders part of the rectangle
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width; // for the shoulder part
		int entityTopWorldY = entity.worldY + entity.solidArea.y; // for the header level part of the rectangle
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height; // for the foot level part of the rectangle
		
		int entityLeftCol = entityLeftWorldX/gamePanel.tileSize;
		int entityRightCol = entityRightWorldX/gamePanel.tileSize;
		int entityTopRow = entityTopWorldY/gamePanel.tileSize;
		int entityBottomRow = entityBottomWorldY/gamePanel.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gamePanel.tileSize;
			tileNum1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
			
			if(gamePanel.tileM.tile[tileNum1].collision == true || 
			   gamePanel.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;	
			}
			break;
			
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gamePanel.tileSize;
			tileNum1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(gamePanel.tileM.tile[tileNum1].collision == true || 
			   gamePanel.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;	
			}
			break;
			
		case "left":
			entityLeftCol = (entityLeftWorldX -	 entity.speed)/gamePanel.tileSize;
			tileNum1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			
			if(gamePanel.tileM.tile[tileNum1].collision == true || 
			   gamePanel.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;	
			}
			break;
			
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gamePanel.tileSize;
			tileNum1 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(gamePanel.tileM.tile[tileNum1].collision == true || 
			   gamePanel.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;	
			}
			break;
		}
		
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < gamePanel.sObj.length; i++) {
			
			if(gamePanel.sObj[i] != null) {
				
				// Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get object area's position
				gamePanel.sObj[i].solidArea.x = gamePanel.sObj[i].worldX + gamePanel.sObj[i].solidArea.x;
				gamePanel.sObj[i].solidArea.y = gamePanel.sObj[i].worldY + gamePanel.sObj[i].solidArea.y;
				
				switch (entity.direction) {
					case "up":
						entity.solidArea.y = entity.solidArea.y - entity.speed;
						if(entity.solidArea.intersects(gamePanel.sObj[i].solidArea)) { // if the rectangle on player and the tile touches it automatically detects it
							if(gamePanel.sObj[i].collision == true) {
								entity.collisionOn = true;
							}
							// checks if its a player or not
							if(player == true) {
								index = i;
							}
						}
						break;
						
					case "down":
						entity.solidArea.y = entity.solidArea.y + entity.speed;
						if(entity.solidArea.intersects(gamePanel.sObj[i].solidArea)) { // if the rectangle on player and the tile touches it automatically detects it
							if(gamePanel.sObj[i].collision == true) {
								entity.collisionOn = true;
							}
							// checks if its a player or not
							if(player == true) {
								index = i;
							}
						}
						break;
						
					case "left":
						entity.solidArea.x = entity.solidArea.x - entity.speed;
						if(entity.solidArea.intersects(gamePanel.sObj[i].solidArea)) { // if the rectangle on player and the tile touches it automatically detects it
							if(gamePanel.sObj[i].collision == true) {
								entity.collisionOn = true;
							}
							// checks if its a player or not
							if(player == true) {
								index = i;
							}
						}
						break;
						
					case "right":
						entity.solidArea.x = entity.solidArea.x + entity.speed;
						if(entity.solidArea.intersects(gamePanel.sObj[i].solidArea)) { // if the rectangle on player and the tile touches it automatically detects it
							if(gamePanel.sObj[i].collision == true) {
								entity.collisionOn = true;
							}
							// checks if its a player or not
							if(player == true) {
								index = i;
							}
						}
						break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gamePanel.sObj[i].solidArea.x = gamePanel.sObj[i].solidAreaDefaultX;
				gamePanel.sObj[i].solidArea.y = gamePanel.sObj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
}
