package by.yakimchik.game;

import java.util.ArrayList;

import by.yakimchik.data.Coordinates;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {
	
	private GameView gameView;
	
	private Bitmap bmp;
	
	private int x;
	private int y;
	
	private int xSpeed = 2;
	private int ySpeed = 2;
	
	private int val = 11;
	
	private int width;
	private int height;
	
	public Sprite(GameView gameView, Bitmap bmp){
		this.gameView = gameView;
		this.bmp = bmp;
		width = bmp.getWidth();
		height = bmp.getHeight();
	}
	
	public void setPosition(int _x, int _y){
		x = _x;
		y = _y;
	}
	
	private void move(){
		if (x > gameView.getWidth() - bmp.getWidth() - xSpeed) {
            xSpeed = -2;
            if(val>0)
            	val*=(-1);
	     }
	     if (x + xSpeed< 0) {
	            xSpeed = 2;
	            if(val<0){
	            	val*=(-1);
	            }
	     }
	     x = x + xSpeed;
	}
	
	private void searchBox(){
		gameView.gameMap.getGameFieldCoordinates();
		
	}
	
	public synchronized void onDraw(Canvas canvas){
		if(Coordinates.isMove){
			move();
			if(Coordinates.getX()!=x+val)
				Coordinates.setX(x+val);
			else
				Coordinates.isMatch = true;
		}
			
		canvas.drawBitmap(bmp, x, y, null);
	}
	
	public boolean isCollition(float x2, float y2){
		return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
	}

}
