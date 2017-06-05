package combat;

import screens.Combat;
import javafx.scene.canvas.GraphicsContext;
import graphics.Drawable;

public class ProjectileManager extends Drawable{
	
	Projectile[] projectiles = new Projectile[10];
	Combat combat;
	
	public ProjectileManager(Combat combat){
		this.combat = combat;
	}
	
	public void update(){
		for(int i = 0; i<projectiles.length; i++){
			if(projectiles[i]!= null){
				projectiles[i].update();
			}
		}
	}
	
	public void addProjectile(double targetx, double targety, boolean hit){
		for(int i = 0; i < projectiles.length; i++){
			if(projectiles[i] == null){
				projectiles[i] = new Projectile(this, i, 277, 386, targetx, targety, hit);
				combat.shotFired();
				return;
			}
		}
		System.out.println("Max number of projectiles");
	}

	public void removeProjectile(int index) {
		if(projectiles[index].hit){
			combat.enemyHit(5);
		}
		projectiles[index] = null;
	}

	@Override
	public void draw(GraphicsContext gc) {
		for(int i = 0; i<projectiles.length; i++){
			if(projectiles[i]!= null){
				projectiles[i].draw(gc);
			}
		}
	}

}
