package misc;

import core.Game;
import core.PlayerData;
import resources.InventoryResourceManager;

public class CommandExecutionManager {
	
	public static void execute(String command){
		String[] commands = command.split(";");
		if(commands == null || commands.length == 0){
			return;
		}
		switch(commands[0]){
		case "say":
			System.out.println(commands.length);
			for(int i = 0; i<commands.length; i++){
				System.out.println(commands[i]);
			}
			if(commands.length == 4){
				Game.dialogbox.say(commands[1].trim(), commands[2].trim(), commands[3].trim());
			} else if (commands.length == 3){
				Game.dialogbox.say(commands[1].trim(), commands[2].trim(), "");
			} else if (commands.length == 2){
				Game.dialogbox.say(commands[1].trim(), "", "");
			}
			break;
		case "item":
			InventoryResourceManager.addToInventory(commands[1].trim(), Integer.parseInt(commands[2].trim()));
			break;
		case "flag":
			if(PlayerData.flags.containsKey(commands[1].trim())){
				PlayerData.flags.put(commands[1].trim(), true);
			}
		default:
			System.out.println("Executing command: "+commands[0].trim());
			break;
			
		}
	}
}
