package by.yakimchik.game;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import by.yakimchik.activity.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import by.yakimchik.data.Coordinates;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	
	private SurfaceHolder holder;
	
	private GameManager gameLoopThread;
	
	private GameDrawMap gameMap;
	
	private Sprite sprite;
	
	private XmlPullParser parser;
	
	private List<Sprite> sprites = new ArrayList<Sprite>();

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		holder = getHolder();
		holder.addCallback(this);
		gameLoopThread = new GameManager(this);
		parser = getResources().getXml(R.xml.map1);
		Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.rect);
		Bitmap box = BitmapFactory.decodeResource(getResources(), R.drawable.box);
		gameMap = new GameDrawMap(parser, map, box);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Sprite s = createSprite(R.drawable.right);
		s.setPosition(5, 5);
		sprites.add(s);
		s = createSprite(R.drawable.left);
		s.setPosition(getWidth()-25, 5); 
		Coordinates.X = getWidth()-25;
		Coordinates.Y = 5;
		sprites.add(s);
		gameLoopThread.setRunning(true);
		gameLoopThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		stopThread();
	}
	
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		//sprite.onDraw(canvas);
		
		for(Sprite _sprite: sprites){
			//_sprite.onDraw(canvas);
		}
		
		gameMap.onDraw(canvas);
		
		if(Coordinates.isMatch){
			stopThread();
		}
	}
	
	private Sprite createSprite(int resouce){
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
		return new Sprite(this, bmp);
	}
	
	private void stopThread(){
		boolean retry = true;
		gameLoopThread.setRunning(false);
		while(retry){
			try{
				gameLoopThread.join();
				retry = false;
			}
			catch (InterruptedException e) {
				// TODO: handle exception
				Log.e("Thread", "Error when destroy thread");
			}
		}
	}

}
