package combat;

import resources.ImageResourceManager;
import screens.Combat;
import javafx.scene.canvas.GraphicsContext;
import graphics.Drawable;

public class PlayerUnit extends Drawable{
	
	float playerHP = 100;
	float playerMaxHP = 100;
	Combat combat;
	
	public PlayerUnit(Combat combat, int x, int y, int width, int height){
		this.combat = combat;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		drawable_type = "CG_IMAGE";
		sprite = ImageResourceManager.getImage("playerunit.png");
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(sprite, x, y, width, height);
		
	}
	
	public float getPlayerHP(){
		return playerHP;
	}
	
	public void damage(float dmg){
		playerHP-= dmg;
		if(playerHP <= 0){
			combat.endGame(false);
		}
	}

	public float getPlayerMaxHP() {
		return playerMaxHP;
	}
}
