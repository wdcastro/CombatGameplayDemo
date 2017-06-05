package combat;

import core.Game;
import resources.ImageResourceManager;
import screens.Combat;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import graphics.Drawable;

public class EnemyUnit extends Drawable{
	
	float left = 423.0f;
	float right = 1200.f;
	float up = 0.0f;
	float down = 474f;
	Image normalSprite = ImageResourceManager.getImage("dragon_normal.png");
	Image damageSprite = ImageResourceManager.getImage("dragon_damage.png");
	int flashCount = 0;
	int currentCount = 0;
	boolean isWhite = false;
	boolean isFlashing = false;
	Combat combat;
	PixelReader pixelreader = normalSprite.getPixelReader();
	
	float hp = 100.0f;
	float maxhp = 100.0f;
	
	float attackInterval = 1000f;
	float currentAttackDuration = 0f;
	
	public EnemyUnit(Combat combat, int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.combat = combat;
		drawable_type = "CG_IMAGE";
	}
	
	public void flash(int count){
		if(!isFlashing){
			flashCount = count;
			currentCount = 0;
			isFlashing = true;
		}
	}
	
	public void damage(float dmg){
		hp-=dmg;
		if(hp<=0){
			combat.endGame(true);
		}
	}
	
	public void update(){
		currentAttackDuration += Game.delta_time/Game.MILLIS_TO_NANOS;
		if(currentAttackDuration >= attackInterval){
			combat.playerHit(10);
			currentAttackDuration = 0;
		}
		if(currentCount < flashCount){
			isWhite = !isWhite;
			currentCount++;
		}
		if(currentCount == flashCount){
			isFlashing = false;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		if(isWhite){
			gc.drawImage(damageSprite, x, y, width, height);		
		} else {
			gc.drawImage(normalSprite, x, y, width, height);		
		}
	}
	
	public boolean isHit(double x, double y){
		if(x>left && x<right){
			if(y>up && y<down){
				combat.shotHit();
				return true;
			}
		}
		return false;
	}
	
	public float getHP(){
		return hp;
	}
	
	public float getMaxHP(){
		return maxhp;
	}
}
