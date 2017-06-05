package ui;

import core.Game;
import graphics.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import resources.ImageResourceManager;

public class HUD extends Drawable{
	//draw enemy hp bar
	//draw your circle
	//draw your hp bar
	//draw icons
	//animate icons
	float enemyHP = 1.0f;
	float playerHP = 1.0f;
	float playerMana = 1.0f;
	Image statusCircle = ImageResourceManager.getImage("hp border.png");
	Image playerHPbar = ImageResourceManager.getImage("player hp.png");
	Image playerManabar = ImageResourceManager.getImage("player mana.png");
	Image enemyHPbar = ImageResourceManager.getImage("enemy hp bar.png");
	
	public void updateEnemyHP(float hp){
		enemyHP = hp;
	}
	
	public void updatePlayerHP(float hp){
		playerHP = hp;
	}
	
	public void updatePlayerMana(float mana){
		playerMana = mana;
	}

	@Override
	public void draw(GraphicsContext gc) {
		//gc.drawImage(statusCircle, 0, 0);
		gc.drawImage(enemyHPbar, Game.SCREEN_WIDTH*0.2, 0, enemyHPbar.getWidth()*enemyHP*0.5, enemyHPbar.getHeight()*0.5);
		gc.drawImage(playerHPbar, 0,  Game.SCREEN_HEIGHT- playerHPbar.getHeight()-playerManabar.getHeight(), playerHPbar.getWidth()*playerHP, playerHPbar.getHeight());
		gc.drawImage(playerManabar, 0, Game.SCREEN_HEIGHT-playerManabar.getHeight(),  playerManabar.getWidth()*playerMana, playerManabar.getHeight());
	}
}
