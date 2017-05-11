package gamecomponents;



import resources.ImageResourceManager;
import storytelling.Cutscene;
import ui.MainMenu;
import world.World;
import graphics.CGImage;
import graphics.DrawingThread;
import graphics.Screen;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class GameStage {
	//defines what is on screen right now - screen, combat, cutscene
	//set what controls do - scene.setOnClick(whateverHandler)
	//pass it to drawing thread
	Image background;
	DrawingThread drawingthread;
	public Screen currentScreen;
	public Screen prevScreen;
	
	public GameStage(Canvas canvas){

		drawingthread = new DrawingThread(canvas.getGraphicsContext2D(), this);
		//Screen screen = new MainMenu();
		//Screen screen = new Cutscene("res/cutscenes/intro cutscene.txt");
		Screen screen = new World("MANSION");
		AnimationTimer updateLoop = new AnimationTimer(){

			@Override
			public void handle(long arg0) {
				currentScreen.update();
				Game.dialogbox.update();
			}
			
		};
		setGameStage(screen);

		updateLoop.start();
		drawingthread.start();
		System.out.println("DrawingThread started");
		PlayerData.loadDefaults();
		
		
	}
	
	public void backToPrevScreen(){
		Screen tempScreen = prevScreen;
		setGameStage(tempScreen);
		tempScreen = null;
	}
	
	public void setGameStage(Screen s){
		System.out.println(s);
		//TODO: render css
		prevScreen = currentScreen;
		currentScreen = s;
		System.out.println("Game stage set to "+ s.getClass().toString());
		drawingthread.updateDrawables(s.getDrawables()); // sprites are drawables
		System.out.println("Starting drawing...");
		System.out.println("--------------------------------");
	}
	
	
}
