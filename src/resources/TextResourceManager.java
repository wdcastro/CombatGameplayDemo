package resources;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class TextResourceManager {
	public final static String resourceLocation = "res/resourcemanagerfiles/ENG_TEXT.resource";
	public static HashMap<String, String> texts = new HashMap<String, String>();
	
	public static void loadResourcesFromFile(){
		try {
			byte[] bytes = FileReader.readBytesFromFile(resourceLocation);
			String[] lines = new String(bytes, "UTF-8").split("\n");
			for(int i = 0; i<lines.length; i++){
				String[] currentLine = lines[i].trim().split(";;");
				if(currentLine[0].startsWith("#")){
					continue;
				} else {
					texts.put(currentLine[0].trim(), currentLine[1].trim());
					System.out.println("Ref: "+currentLine[0] + " Line: "+currentLine[1]);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
