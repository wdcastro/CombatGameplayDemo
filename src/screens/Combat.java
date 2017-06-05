package screens;

import combat.EnemyUnit;
import combat.PlayerUnit;
import combat.ProjectileManager;
import resources.ImageResourceManager;
import ui.HUD;
import javafx.scene.input.MouseEvent;
import core.Game;
import graphics.CGImage;

public class Combat extends Screen{

	//draw background - parallax scrolling
	//animate dragon
	//draw player
	//handle input
	//check hp bars
	CGImage sky = new CGImage(ImageResourceManager.getImage("sky.jpg"), 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
	CGImage ground = new CGImage(ImageResourceManager.getImage("ground.png"), 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
	EnemyUnit enemyunit = new EnemyUnit(this, 0,0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
	PlayerUnit playerunit = new PlayerUnit(this, 0,0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
	ProjectileManager projman = new ProjectileManager(this);
	HUD hud = new HUD();
	double startTime = 0.0;
	double shotsFired = 0;
	double shotsHit;
	boolean isPlaying = false;
	
	double currentCooldown = 0;
	
	public Combat(){
		init();
	}
	
	public void init(){
		drawables.add(sky);
		drawables.add(enemyunit);
		drawables.add(ground);
		drawables.add(projman);
		drawables.add(playerunit);
		drawables.add(hud);
		startTime = System.currentTimeMillis();
		isPlaying = true;
	}

	@Override
	public String getScreenType() {
		return "COMBAT";
	}

	@Override
	public void update() {
		enemyunit.update();
		projman.update();
		handleKeyPress();
		if(currentCooldown > 0){
			currentCooldown -= Game.delta_time/Game.MILLIS_TO_NANOS;
		}
	}

	private void handleKeyPress() {
		if(currentCooldown <= 0){
			if(Game.keyhandler.isKeyDown("Q")){
				System.out.println("Cast spell 1");
				currentCooldown = 1000;
			}
			if(Game.keyhandler.isKeyDown("TAB")){
				System.out.println("Change characters");
				currentCooldown = 1000;
			}
		}
		
		
	}

	@Override
	public void handleMousePress(MouseEvent e) {		
		projman.addProjectile(e.getSceneX(), e.getSceneY(), enemyunit.isHit(e.getSceneX(), e.getSceneY()));
		System.out.println(e.getSceneX()+", "+e.getSceneY());
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	public void endGame(boolean win){
		if(isPlaying){
			System.out.println("Game Ended");
			if(win){
				System.out.println("You win!");
			} else {
				System.out.println("You lose!");
			}
			System.out.println("-------------------------");
			System.out.println("HP Remaining: ");
			System.out.println("Time taken: " + ((System.currentTimeMillis()-startTime)/1000)+ " seconds");
			System.out.println("Shots fired: "+shotsFired);
			System.out.println("Shots hit: "+shotsHit);
			System.out.println("Accuracy: "+(Math.round((shotsHit/shotsFired)*100))+"%");
			isPlaying = false;	
		}
		
	}

	public void enemyHit(float dmg) {
		if(isPlaying){
			enemyunit.flash(10);
			enemyunit.damage(dmg);
			hud.updateEnemyHP(enemyunit.getHP()/enemyunit.getMaxHP());	
		}
		
	}
	
	public void shotHit(){
		shotsHit++;
	}
	
	public void shotFired(){
		shotsFired++;
	}

	public void playerHit(float dmg) {
		if(isPlaying){
			playerunit.damage(dmg);
			hud.updatePlayerHP(playerunit.getPlayerHP()/playerunit.getPlayerMaxHP());
		}
	}
	
}
