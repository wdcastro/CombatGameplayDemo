package tilemap;

import java.util.ArrayList;
import java.util.HashMap;

import core.CommandExecutionManager;
import parser.Element;

public class Tile {
	
	HashMap<String, TiledProperty> properties = new HashMap<String, TiledProperty>();
	ArrayList<TiledObjectGroup> objectgroups = new ArrayList<TiledObjectGroup>();
	public int id;
	public int gid;//id to draw
	private ArrayList<Integer> animations = new ArrayList<Integer>();
	private ArrayList<Float> durations = new ArrayList<Float>();
	private int currentFrame = 0;
	private double lastUpdateTime;
	public int tilesetIndex;
	boolean specialTile = false;
	boolean animatedTile = false;
	boolean hasObject = false;
	boolean isVisible = true;
	
	public Tile(int id, String type, int tilesetIndex){
		if(type == "data"){
			this.id = id;
			this.gid = id-1;
		} else {
			this.id = id+1;
			this.gid = id;
		}
		lastUpdateTime = System.currentTimeMillis();
		this.tilesetIndex = tilesetIndex;
	}
	
	public void loadObjectGroup(Element element){
		HashMap<String, String> attributes = element.getAttributes();

		TiledObjectGroup group = new TiledObjectGroup();
		if(attributes.containsKey("name")){
			group.setName(attributes.get("name"));
		}
		
		if (attributes.containsKey("draworder")){
			group.setName(attributes.get("draworder"));
		}
		
		if (attributes.containsKey("offsetx")){
			group.setOffsetX(Integer.parseInt(attributes.get("offsetx")));
		}
		
		if (attributes.containsKey("offsety")){
			group.setOffsetX(Integer.parseInt(attributes.get("offsety")));
		}
		objectgroups.add(group);
	}
	
	public void loadProperties(Element element){
		ArrayList<Element> children = element.getChildren();
		for(int i = 0; i < children.size(); i++){
			Element currentElement = children.get(i);
			if(!currentElement.getName().equals("property")){
				System.err.println("Trying to load a tag that isn't a properties in Tile: "+currentElement.getName());
			}
			HashMap<String, String> attributes = currentElement.getAttributes();
			TiledProperty property = new TiledProperty(attributes.get("name"), attributes.get("type"), attributes.get("value"));
			properties.put(property.name,property);
		}		
	}
	
	public void loadAnimations(Element element){
		ArrayList<Element> children = element.getChildren();
		for(int i = 0; i < children.size(); i++){
			Element currentElement = children.get(i);
			animations.add(Integer.parseInt(currentElement.getAttributes().get("tileid")));
			durations.add(Float.parseFloat(currentElement.getAttributes().get("duration")));
		}
	}
	
	public void update(){
		if(!animations.isEmpty()){
			if((System.currentTimeMillis() - lastUpdateTime) >= durations.get(currentFrame)){
				if(currentFrame == animations.size()-1){
					currentFrame = 0;
				} else {
					currentFrame++;
				}
				gid = animations.get(currentFrame);
				lastUpdateTime = System.currentTimeMillis();
			}
		}
		
	}
	
	public void setAnimatedTile(boolean set){
		animatedTile = set;
	}
	
	public boolean isAnimatedTile(){
		return animatedTile;
	}
	
	public void setHasObject(boolean set){
		hasObject = set;
	}
	
	public boolean hasObject(){
		return hasObject;
	}
	
	public boolean isInteractableTile(){
		return (properties.containsKey("nearInteract") || properties.containsKey("farInteract"));
	}
	
	public boolean isSpecialTile(){
		return (isAnimatedTile() || hasObject() || isInteractableTile());
	}

	public void farInteract() {
		if(properties.containsKey("farInteract")){
			System.out.println(properties.get("farInteract").getValue());
			//TODO:Parse interact commands
		}
		
	}

	public void nearInteract() {
		if(properties.containsKey("nearInteract")){
			CommandExecutionManager.execute(properties.get("nearInteract").getValue());
		}
	}
	
	public void setVisibility(boolean b){
		isVisible = b;
	}
}
