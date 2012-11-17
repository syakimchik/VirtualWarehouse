package by.yakimchik.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameDrawMap {

	private GameView gameView;
	
	private XmlPullParser parser;
	
	private Bitmap bmp;
	
	private List<Integer> xCoordinates = new ArrayList<Integer>();
	private List<Integer> yCoordinates = new ArrayList<Integer>();
	
	public GameDrawMap(XmlPullParser parser, Bitmap bmp){
		this.parser = parser;
		this.bmp = bmp;
	}
	
	private void parseXmlFile() throws XmlPullParserException, IOException{
		while(parser.getEventType()!=XmlPullParser.END_DOCUMENT){
			if(parser.getEventType()==XmlPullParser.START_TAG && parser.getName().equals("point")){
				xCoordinates.add(Integer.parseInt(parser.getAttributeValue(0)));
				yCoordinates.add(Integer.parseInt(parser.getAttributeValue(1)));
			}
			parser.next();
		}
	}
	
	public void onDraw(Canvas canvas){
		try {
			parseXmlFile();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i<xCoordinates.size()-1; i++){
			int x2 = xCoordinates.get(i+1);
			int y2 = yCoordinates.get(i+1);
			int max, j;
			boolean curCoord;
			if(xCoordinates.get(i)==x2){
				max = y2;
				curCoord = true;
				j = yCoordinates.get(i);
			}
			else{
				max = x2;
				curCoord = false;
				j = xCoordinates.get(i);
			}
			for(; j<=max; j+=25){
				if(curCoord){
					canvas.drawBitmap(bmp, xCoordinates.get(i), j, null);
				}
				else{
					canvas.drawBitmap(bmp, j, yCoordinates.get(i), null);
				}
			}
		}
	}
}
