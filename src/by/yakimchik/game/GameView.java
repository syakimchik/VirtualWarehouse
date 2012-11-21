package by.yakimchik.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

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
	
	public GameDrawMap gameMap;
	
	private XmlPullParser parser;
	
	private Canvas canvas;
	
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
		Bitmap robot = BitmapFactory.decodeResource(getResources(), R.drawable.front);
		gameMap = new GameDrawMap(this, parser, map, box, robot);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try {
			gameMap.parseXmlFile();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sprites = gameMap.getSprites();
		
		gameLoopThread.setRunning(true);
		gameLoopThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		stopThread();
	}
	
	protected void onDraw(Canvas canvas){
		this.canvas = canvas;
		canvas.drawColor(Color.BLACK);
		
		gameMap.onDraw(canvas);
		
		for(Sprite _sprite: sprites){
			_sprite.onDraw(canvas);
		}
		
		if(Coordinates.isMatch){
			stopThread();
		}
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
	
	public void setDraw(){
		Coordinates.isMove = true;
		onDraw(canvas);
	}

}
