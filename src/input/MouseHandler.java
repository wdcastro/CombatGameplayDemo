package input;

import core.Game;
import core.GameStage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseHandler implements EventHandler<MouseEvent> {
	
	boolean MOUSE_PRIMARY_DOWN = false;
	boolean MOUSE_SECONDARY_DOWN = false;
	boolean MOUSE_MIDDLE_DOWN = false;

	@Override
	public void handle(MouseEvent e) {
		if(e.getEventType().toString() == "MOUSE_PRESSED"){
			handleMousePress(e);
		}
		if(e.getEventType().toString() == "MOUSE_RELEASED"){
			handleMouseRelease(e);
		}
	}
	
	public void handleMousePress(MouseEvent e){

		GameStage.currentScreen.handleMousePress(e);
		//System.out.println(e.getButton() + " PRESSED");
	}
	
	public void handleMouseRelease(MouseEvent e){
		if(e.getButton().toString() == "PRIMARY"){
			MOUSE_PRIMARY_DOWN = false;
		} else if (e.getButton().toString() == "SECONDARY"){
			MOUSE_SECONDARY_DOWN = false;
		} else if (e.getButton().toString() == "MIDDLE"){
			MOUSE_MIDDLE_DOWN = false;
		}
		//System.out.println(e.getButton() + " RELEASED");
	}

}
