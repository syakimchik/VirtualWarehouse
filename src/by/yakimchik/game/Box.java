package by.yakimchik.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import by.yakimchik.interfaces.IDraw;
import by.yakimchik.interfaces.IMove;

public class Box implements IMove, IDraw{
	
	private GameView gameView;
	
	private Bitmap bmp;
	
	private int x;
	private int y;
	
	private int xSpeed = 2;
	private int ySpeed = 2;
	
	private int val = 11;
	
	private int width;
	private int height;
	
	public Box(GameView gameView, Bitmap bmp){
		this.gameView = gameView;
		this.bmp = bmp;
		width = bmp.getWidth();
		height = bmp.getHeight();
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(int _x, int _y) {
		// TODO Auto-generated method stub
		x = _x;
		y = _y;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

}
