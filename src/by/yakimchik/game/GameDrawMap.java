package by.yakimchik.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import by.yakimchik.data.Point;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameDrawMap {
	
	private XmlPullParser parser;
	
	private Bitmap wall;
	private Bitmap box;
	private Bitmap robot;
	
	private GameView gameView;
	
	//coordinates of wall	
	private List<Point> wallCoordinates = new ArrayList<Point>();
	
	//coordinates of box
	private ArrayList<Point> boxCoordinates = new ArrayList<Point>();
	
	//coordinates of sprite
	private ArrayList<Point> robotCoordinates = new ArrayList<Point>();
	
	private Point exitCoordinate = null;
	
	private ArrayList<Robot> robots = new ArrayList<Robot>();
	
	//game 	field coordinates
	private ArrayList<Point> gameFieldCoordinates = new ArrayList<Point>();
	
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
				setCoordinates(parser, 1);
			}
			
			if(parser.getEventType()==XmlPullParser.START_TAG && parser.getName().equals("box")){				
				setCoordinates(parser, 2);
			}
			
			if(parser.getEventType()==XmlPullParser.START_TAG && parser.getName().equals("robot")){
				setCoordinates(parser, 3);
			}
			
			if(parser.getEventType()==XmlPullParser.START_TAG && parser.getName().equals("door")){
				setCoordinates(parser, 4);
			}
			
			parser.next();
		}
		
		for(int i=0; i<robotCoordinates.size(); i++){
			Robot s = new Robot(gameView, robot);
			s.setPosition(robotCoordinates.get(i).getX(), robotCoordinates.get(i).getY());
			robots.add(s);
		}
	}
	
	private void setCoordinates(XmlPullParser parser, int tag){
		
		int x = Integer.parseInt(parser.getAttributeValue(0));
		int y = Integer.parseInt(parser.getAttributeValue(1));
		
		Point p = new Point(x, y);
		
		switch (tag) {
		case 1:
			wallCoordinates.add(p);
			break;
			
		case 2:
			boxCoordinates.add(p);
			break;
			
		case 3:
			robotCoordinates.add(p);
			break;
			
		case 4:
			exitCoordinate = p;
			break;

		default:
			break;
		}
	}
	
	public void onDraw(Canvas canvas){		
		for(int i=0; i<wallCoordinates.size()-1; i++){
			int x2 = wallCoordinates.get(i+1).getX();
			int y2 = wallCoordinates.get(i+1).getY();
			int max, j;
			boolean curCoord;
			if(wallCoordinates.get(i).getX()==x2){
				curCoord = true;
				if(wallCoordinates.get(i+1).getY()<wallCoordinates.get(i).getY()){
					j = wallCoordinates.get(i+1).getY();
					max = wallCoordinates.get(i).getY();
				}
				else{
					max = y2;
					j = wallCoordinates.get(i).getY();
				}
			}
			else{
				curCoord = false;
				if(wallCoordinates.get(i+1).getX()<wallCoordinates.get(i).getX()){
					j = wallCoordinates.get(i+1).getX();
					max = wallCoordinates.get(i).getX();
				}
				else{
					max = x2;
					j = wallCoordinates.get(i).getX();
				}
			}
			for(; j<=max; j+=25){
				if(curCoord){
					canvas.drawBitmap(wall, wallCoordinates.get(i).getX(), j, null);
					gameFieldCoordinates.add(new Point(wallCoordinates.get(i).getX(), j));
				}
				else{
					canvas.drawBitmap(wall, j, wallCoordinates.get(i).getY(), null);
					gameFieldCoordinates.add(new Point(j, wallCoordinates.get(i).getY()));
				}
			}
		}
		
		for(int i=0; i<boxCoordinates.size(); i++){
			canvas.drawBitmap(box, boxCoordinates.get(i).getX(), boxCoordinates.get(i).getY(), null);
		}
		
		gameFieldCoordinates.add(exitCoordinate);

	}
	
	public ArrayList<Robot> getSprites(){
		return robots;
	}
	
	public ArrayList<Point> getBoxCoordinates(){
		return boxCoordinates;
	}
	
	public ArrayList<Point> getGameFieldCoordinates(){
		return gameFieldCoordinates;
	}
}
