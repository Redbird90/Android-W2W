package com.example.jkt.wall2wall0;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.example.jkt.wall2wall0.impl.AndroidGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by JDK on 3/30/2015.
 */
public class W2WGame extends AndroidGame {

    public static String map;
    boolean firstTimeCreate = true;

    public Screen getInitScreen() {
        if (firstTimeCreate) {
            Settings.load(getFileIO());
            firstTimeCreate = false;
        }
        Log.i("W2WGame", "getInitScreen");

        //InputStream mapInputStream = getResources().openRawResource(R.raw.map1);
        //map = convertStreamToString(mapInputStream);   LOADED GAME MAP

        //return new SplashLoadingScreen(this);

        return new LoadingScreen(this);
    }

    @Override
    public void onBackPressed() {
        //getCurrentScreen().backButton();
    }

/*    private static String convertStreamToString(InputStream convertStream) {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                convertStream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = inputReader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            Log.w("LOG", e.getMessage());
        } finally {
            try {
                convertStream.close();
            } catch (IOException e) {
                Log.w("LOG", e.getMessage());
            }
        }

        return sb.toString();
    }*/

    @Override
    public void onResume() {
        super.onResume();

        //Assets.theme.play();
    }

    @Override
    public void onPause() {
        super.onPause();
        //Assets.theme.pause();
    }

    //@Override
    public Screen getStartScreen() {
        return null;
    }

    //@Override
    public int getScreenWidth() {
        return 0;
    }

    //@Override
    public int getScreenHeight() {
        return 0;
    }

    //@Override
    public int getScreenOrientation() {
        return 0;
    }

/*    public int getHighScore() {
        return this.highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }*/
}