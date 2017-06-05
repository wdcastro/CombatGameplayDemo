package resources;


import java.io.File;
import java.util.Hashtable;

import javafx.scene.image.Image;

public class ImageResourceManager {
	
	static Hashtable<String, String> images = new Hashtable<String, String>();

	public ImageResourceManager(){
	}
	
	public static Image getImage(String imagename){
		if(images.containsKey(imagename)){
			return new Image(new File(images.get(imagename)).toURI().toString());
		} else {
			//return new Image(new File(filenames.get("DEFAULT").getImageName()).toURI().toString());
			return null;
		}
	}
	
	
	public static void loadResourcesFromFile(){
		File file = new File("res/images");
		File[] dirlist = file.listFiles();
		for(int i = 0; i < dirlist.length; i++){
			images.put(dirlist[i].getName(), dirlist[i].getPath());
			System.out.println(dirlist[i].getName()+", "+images.get(dirlist[i].getName()));
		}
	}
	
	public static boolean hasImage(String s){
		return images.contains(s);
	}
	
	public static Image getImageFromFile(String path){
		return new Image(new File(path).toURI().toString());
	}
	
	//TODO: function for loading strings into hash table from external file
}
