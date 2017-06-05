package combat;

import core.Game;
import resources.ImageResourceManager;
import screens.Combat;
import javafx.scene.canvas.GraphicsContext;
import graphics.Drawable;

public class Projectile extends Drawable {
	
	double distance = 0.0;
	double distTravelled = 0.0;
	double destx = 0.0;
	double desty = 0.0;
	double sourcex = 0.0;
	double sourcey = 0.0;
	double currentx = 0.0;
	double currenty = 0.0;
	double xpers = 0.0;
	double ypers = 0.0;
	float spriteWidth = 206;
	float spriteHeight = 205;
	float duration = 1000;
	float currentduration = 0;
	ProjectileManager projman;
	int index;
	public boolean hit = false;
	double speed = 0.0;
	
	public Projectile(ProjectileManager projman, int index, double sourcex, double sourcey, double destx, double desty, boolean hit){
		distance = Math.sqrt(Math.pow((destx-sourcex), 2) + Math.pow((desty-sourcey), 2));

		this.sourcex = sourcex;
		this.sourcey = sourcey;
		currentx = sourcex;
		currenty = sourcey;
		this.destx = destx;
		this.desty = desty;
		this.projman = projman;
		this.index = index;
		sprite = ImageResourceManager.getImage("projectile.png");
		xpers = (destx-sourcex)/duration;
		ypers = (desty-sourcey)/duration;
		this.hit = hit;

	}
	
	public void update(){
		currentduration+=Game.delta_time/Game.MILLIS_TO_NANOS;
		distTravelled = (Math.sqrt(Math.pow((sourcex-currentx), 2) + Math.pow((sourcey-currenty), 2)));
		speed = ((distance-distTravelled)/distance)*5;
		currentx += speed*(xpers*Game.delta_time/Game.MILLIS_TO_NANOS);
		currenty += speed*(ypers*Game.delta_time/Game.MILLIS_TO_NANOS);
		if(distTravelled >= distance || currentduration>=duration){
			projman.removeProjectile(index);
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(sprite, 0,0,spriteWidth, spriteHeight, currentx, currenty, spriteWidth*((distance-distTravelled)/distance), spriteHeight*((distance-distTravelled)/distance));
	}
}
