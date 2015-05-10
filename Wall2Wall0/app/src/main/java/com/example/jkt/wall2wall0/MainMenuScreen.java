package com.example.jkt.wall2wall0;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.Color;
import android.util.Log;
import android.widget.CheckBox;

import com.google.android.gms.games.Games;

import java.util.List;

/**
 * Created by JDK on 4/3/2015.
 */
public class MainMenuScreen extends Screen {

    private boolean at_settings;
    private String sound_pref_text;
    Activity activity;
    private boolean at_leaderboards;

    public MainMenuScreen(Game game) {
        super(game);
        Log.i("MainMenuScreen", "Starting Main Menu Screen...");
        //Music menu_music = game.getAudio().newMusic("Boxing Bell Start Round.wav");
        //menu_music.play();
        //menu_music.setLooping(true);

    }

    Paint menu_paint = new Paint();

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int touchEventsListSize = touchEvents.size();
        for (int touchEventIndex = 0; touchEventIndex < touchEventsListSize; touchEventIndex++) {
            Input.TouchEvent event = touchEvents.get(touchEventIndex);
            if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                if (!at_settings && inBounds(event, 95, 468, 240, 76)) {
                    game.setScreen(new GameScreen(game));
                }
                if (!at_settings && inBounds(event, 95, 680, 290, 76)) {
                    //at_settings = true;
                } else if (at_settings && inBounds(event, 120, 370, 240, 100) && Settings.soundEnabled) {
                    //Settings.soundEnabled = false;
                } else if (at_settings && inBounds(event, 120, 370, 240, 100) && !Settings.soundEnabled) {
                    //Settings.soundEnabled = true;
                } else if (at_settings && inBounds(event, 140, 555, 240, 80)) {
                    //at_settings = false;
                    //Settings.save(game.getFileIO());
                }
                if (!at_settings && inBounds(event, 95, 572, 250, 76)) {
                    //at_leaderboards = true;
                }

/*                if (!at_settings && inBounds(event, LEADERBOARD_BUTTON_DIMENSIONS)) {
                    game.goToLeaderboard();
                }*/

/*                g.drawRect(115, 365, 250, 260, Color.BLACK);
                g.drawRect(120, 370, 240, 250, Color.WHITE);
                menu_paint.setTextSize(28);
                g.drawString(sound_pref_text, 240, 390, menu_paint);
                menu_paint.setTextSize(16);
                g.drawString("Tap to change", 240, 430, menu_paint);
                menu_paint.setTextSize(20);
                g.drawString("Back", 240, 600, menu_paint);*/

            }
        }

    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
/*        menu_paint.setAntiAlias(true);
        menu_paint.setTextAlign(Paint.Align.CENTER);
        menu_paint.setColor(Color.BLACK);
        menu_paint.setTextSize(60);
        g.clearScreen(Color.WHITE);
        g.drawString("WALL", 100, 70, menu_paint);
        g.drawString("WALL", 380, 210, menu_paint);
        menu_paint.setTextSize(90);
        g.drawString("2", 240, 140, menu_paint);
        g.drawRect(190, 550, 100, 60, menu_paint);
        menu_paint.setTextSize(35);
        menu_paint.setColor(Color.BLUE);
        g.drawString("START", 240, 600, menu_paint);

        g.drawString("Settings", 240, 760, menu_paint);*/

        g.drawImage(g.newImage("SplashScreenhighresfilled.png", Graphics.ImageFormat.RGB565), 0, 0);

        if (at_settings) {
            if (Settings.soundEnabled) {
                sound_pref_text = "Sound is ON";
            } else if (!Settings.soundEnabled) {
                sound_pref_text = "Sound is OFF";
            }

            g.drawRect(115, 365, 250, 260, Color.BLACK);
            g.drawRect(120, 370, 240, 250, Color.WHITE);
            menu_paint.setTextSize(28);
            g.drawString(sound_pref_text, 240, 410, menu_paint);
            menu_paint.setTextSize(16);
            g.drawString("Tap to change", 240, 440, menu_paint);
            menu_paint.setTextSize(20);
            g.drawString("Back", 240, 590, menu_paint);
        }
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    //@Override
    public void backButton() {
        // The user seems to want to quit.
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}