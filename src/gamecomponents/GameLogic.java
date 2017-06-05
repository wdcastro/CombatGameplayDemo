package gamecomponents;

import java.util.ArrayList;

import screens.Combat;
import screens.Cutscene;
import screens.World;
import core.GameStage;
import core.PlayerData;
import audio.PlaylistManager;

public class GameLogic {
	

	public static void start(){
		loadChapter(getCurrentChapter());
	}
	
	public static void loadChapter(int chapter){
		System.out.println("GameLogic:loadChapter");
		ArrayList<String> playlist = new ArrayList<String>();
		System.out.println("Loading chapter "+ chapter);
		System.out.println("-------------------------------");
		switch(chapter){
		/*case 0:
			playlist.clear();
			playlist.add("kwartetjaponski.mp3"); 
			playlist.add("fieldforce.mp3"); 
			PlaylistManager.loadPlaylist(playlist);
			
			Cutscene cutscene = new Cutscene("res/en/cutscenes/intro cutscene.txt");
			GameStage.setGameStage(cutscene);
			cutscene.play();
			break;
		case 1:
			playlist.clear();
			playlist.add("fieldforce.mp3"); 
			PlaylistManager.loadPlaylist(playlist);
			
			World chapter1map = new World("mansion.tmx","res/en/world/CHAPTER1MANSION.txt");
			
			GameStage.setGameStage(chapter1map);
			break;
			*/
		default:
			playlist.clear();
			playlist.add("fieldforce.mp3"); 
			PlaylistManager.loadPlaylist(playlist);
			
			Combat combat = new Combat();
			GameStage.setGameStage(combat);
			break;
		}
	}
	
	public static int getCurrentChapter(){
		if(!PlayerData.checkFlag("intro cutscene")){
			return 0;
		}
		if(!PlayerData.checkFlag("found glasses") && !PlayerData.checkFlag("found bell") && !PlayerData.checkFlag("found rope")){
			return 1;
		}
		if(!PlayerData.checkFlag("festival cutscene")){
			return 2;
		}
		if(!PlayerData.checkFlag("children enter")){
			return 3;
		}
		if(!PlayerData.checkFlag("talk to child 1") && !PlayerData.checkFlag("talk to child 2") && !PlayerData.checkFlag("talk to child 3")){
			return 4;
		}
		if(!PlayerData.checkFlag("denial scene")){
			return 5;
		}
		if(!PlayerData.checkFlag("found broken picture")){
			return 6;
		}
		if(!PlayerData.checkFlag("second group of kids")){
			return 7;
		}
		if(!PlayerData.checkFlag("pick up photo")){
			return 8;
		}
		if(!PlayerData.checkFlag("cat cutscene")){
			return 9;
		}
		if(!PlayerData.checkFlag("am i actually dead")){
			return 10;
		}
		if(!PlayerData.checkFlag("memory recalled")){
			return 11;
		}
		return 12;
	}
	
	public static void cutsceneFinishedCallback(){
		start();
	}

}
