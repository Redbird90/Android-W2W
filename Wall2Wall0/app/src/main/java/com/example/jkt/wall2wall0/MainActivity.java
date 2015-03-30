package com.example.jkt.wall2wall0;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    boolean tapped = false;
    public Input getInput() {
        return null;
    }

    public void onClick(View view) {
        Button screen_button = (Button) findViewById(R.id.screen_button);
        Log.i("touch", "confirmed");
        tapped = true;

    }


    public FileIO getFileIO() {
        return null;
    }

    //public Graphics getGraphics();

    //public Audio getAudio();

    //public void setScreen(Screen screen);

    //public Screen getCurrentScreen();

    //public Screen getStartScreen();

    //public Context getContext();

    //public int getScreenWidth();

    //public int getScreenHeight();

    //public int getScreenOrientation();




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
