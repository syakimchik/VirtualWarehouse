package by.yakimchik.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameDrawMap {
	
	private XmlPullParser parser;
	
	private Bitmap wall;
	private Bitmap box;
	private Bitmap robot;
	
	private GameView gameView;
	
	//coordinates of wall
	private List<Integer> xWallCoordinates = new ArrayList<Integer>();
	private List<Integer> yWallCoordinates = new ArrayList<Integer>();
	
	//coordinates of box
	private List<Integer> xBoxCoordinates = new ArrayList<Integer>();
	private List<Integer> yBoxCoordinates = new ArrayList<Integer>();
	
	//sprite
	private List<Integer> xRobotCoordinates = new ArrayList<Integer>();
	private List<Integer> yRobotCoordinates = new ArrayList<Integer>();
	
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	public GameDrawMap(GameView gameView, XmlPullParser parser, Bitmap wall, Bitmap box, Bitmap robot){
		this.parser = parser;
		this.wall = wall;
		this.box = box;
		this.robot = robot;
		this.gameView = gameView;
	}
	
	public void parseXmlFile() throws XmlPullParserException, IOException{
		while(parser.getEventType()!=XmlPullParser.END_DOCUMENT){
			if(parser.getEventType()==XmlPullParser.START_TAG && parser.getName().equals("point")){
				xWallCoordinates.add(Integer.parseInt(parser.getAttributeValue(0)));
				yWallCoordinates.add(Integer.parseInt(parser.getAttributeValue(1)));
			}
			
			if(parser.getEventType()==XmlPullParser.START_TAG && parser.getName().equals("box")){
				xBoxCoordinates.add(Integer.parseInt(parser.getAttributeValue(0)));
				yBoxCoordinates.add(Integer.parseInt(parser.getAttributeValue(1)));
			}
			
			if(parser.getEventType()==XmlPullParser.START_TAG && parser.getName().equals("robot")){
				xRobotCoordinates.add(Integer.parseInt(parser.getAttributeValue(0)));
				yRobotCoordinates.add(Integer.parseInt(parser.getAttributeValue(1)));
			}
			
			parser.next();
		}
		
		for(int i=0; i<xRobotCoordinates.size(); i++){
			Sprite s = new Sprite(gameView, robot);
			s.setPosition(xRobotCoordinates.get(i), yRobotCoordinates.get(i));
			sprites.add(s);
		}
	}
	
	public void onDraw(Canvas canvas){		
		for(int i=0; i<xWallCoordinates.size()-1; i++){
			int x2 = xWallCoordinates.get(i+1);
			int y2 = yWallCoordinates.get(i+1);
			int max, j;
			boolean curCoord;
			if(xWallCoordinates.get(i)==x2){
				curCoord = true;
				if(yWallCoordinates.get(i+1)<yWallCoordinates.get(i)){
					j = yWallCoordinates.get(i+1);
					max = yWallCoordinates.get(i);
				}
				else{
					max = y2;
					j = yWallCoordinates.get(i);
				}
			}
			else{
				curCoord = false;
				if(xWallCoordinates.get(i+1)<xWallCoordinates.get(i)){
					j = xWallCoordinates.get(i+1);
					max = xWallCoordinates.get(i);
				}
				else{
					max = x2;
					j = xWallCoordinates.get(i);
				}
			}
			for(; j<=max; j+=25){
				if(curCoord){
					canvas.drawBitmap(wall, xWallCoordinates.get(i), j, null);
				}
				else{
					canvas.drawBitmap(wall, j, yWallCoordinates.get(i), null);
				}
			}
		}
		
		for(int i=0; i<xBoxCoordinates.size(); i++){
			canvas.drawBitmap(box, xBoxCoordinates.get(i), yBoxCoordinates.get(i), null);
		}

	}
	
	public ArrayList<Sprite> getSprites(){
		return sprites;
	}
}
