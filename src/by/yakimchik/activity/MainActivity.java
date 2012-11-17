package by.yakimchik.activity;

import by.yakimchik.game.GameView;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//application have not title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//always landscape orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		//full screen
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//set view
		setContentView(new GameView(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
