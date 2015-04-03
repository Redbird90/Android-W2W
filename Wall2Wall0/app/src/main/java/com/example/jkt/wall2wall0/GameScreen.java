package com.example.jkt.wall2wall0;

import android.content.Context;
import android.graphics.*;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.example.jkt.wall2wall0.impl.AndroidImage;
import com.example.jkt.wall2wall0.math.OverlapTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JDK on 3/30/2015.
 */
public class GameScreen extends Screen {
    private player_char player1;
    private example_enemy enemy1;
    private boolean enemy1added;
    private final Random randomGenerator;
    private final ArrayList<example_enemy> enemy_list;
    private final OverlapTester checkForOverlap;
    private boolean drawPlayer;
    private final long[] previousSpawnTime;
    private final long startTime;
    private final Handler timerHandler;
    private final Runnable timerRunnable;
    private long delay_time;

    enum GameState {
        Ready, Running, Paused, GameOver
    }
    int a = 0;

    GameState state = GameState.Ready;
    //private static player_char player1;
    //private static example_enemy enemy1;
    Paint paint;
    Paint paint2;
    public GameScreen(Game game) {
        super(game);
        Log.i("GameScreen", "start");


        // Initialize game objects

        this.player1 = new player_char(96f, 576f, 40f, 50f);
        this.enemy1 = new example_enemy(200f, -30f, 60f, 60f);
        this.enemy1added = false;
        this.randomGenerator = new Random();
        this.enemy_list = new ArrayList<example_enemy>(12);
        this.checkForOverlap = new OverlapTester();
        this.drawPlayer = true;
        this.previousSpawnTime = new long[1];
        this.startTime = System.currentTimeMillis();
        this.timerHandler = new Handler(Looper.getMainLooper());
        this.timerRunnable = new Runnable() {
            @Override
            public void run() {
                Log.i("GameScreen", "ready to spawn");
                if (enemy_list.size() < 12) {
                    example_enemy later_enemy = new example_enemy((float) (((randomGenerator.nextInt(10000) / 10000.0) * 200) + 110), (-80f), 60, 60);
                    enemy_list.add(later_enemy);
                    previousSpawnTime[0] = System.currentTimeMillis();
                }
            }
        };


        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(10);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.YELLOW);
    }

    Graphics g = game.getGraphics();  //ONLY NEEDED IF DRAWING AT GAME START.

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        Log.i("GameScreen", "general update");
        Log.i("TESTING", String.valueOf(this.player1.getY_pos()));

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different
        // update methods. Refer to Unit 3's code. We did a similar
        // thing without separating the update method.

        // Note that order is important here. If the game is
        // in Ready state, we should only go to Running.
        // From Running, we should only go to Paused or GameOver.

        if (state == GameState.Ready)
            Log.i("GameScreen", "state is READY");
            updateReady(touchEvents);
        if (state == GameState.Running)
            Log.i("GameScreen", "state is RUNNING");
            updateRunning(touchEvents, deltaTime);
/*        if (state == GameState.Paused)
            updatePaused(touchEvents);*/
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);

    }


    private void updateReady(List<Input.TouchEvent> touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game
        // begins. state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        Log.i("TESTING", String.valueOf(this.player1.getY_pos()));

        if (true) {//CHANGE
            Log.i("GameScreen", "updateReadytoRunning");
            state = GameState.Running;
            this.drawPlayer = true;
        }
    }

    private void updateRunning(List<Input.TouchEvent> touchEvents, float deltaTime) {

        // This is identical to the update() method from Unit 2/3.
        if (this.player1.getY_pos() > 870) {
            state = GameState.GameOver;
            Log.i("GameScreen", "player fell to DEATH");
            this.drawPlayer = false;
            delay_time = (long) 0;
        }

        // 1. All touch input is handled here:
        Log.i("GameScreen", "update1, updateRunning started");
        int touchEventsSize = touchEvents.size();
        for (int currentTouchEventIndex = 0; currentTouchEventIndex < touchEventsSize; currentTouchEventIndex++) {
            Input.TouchEvent currentEvent = (Input.TouchEvent) touchEvents
                    .get(currentTouchEventIndex);
            if (currentEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                Log.i("GameScreen", "TOUCH_DOWN");
                // Check if pause button pressed
                // UPDATE FOR PAUSE IMPLEMENTATION
/*                if (inBounds(currentEvent, 0, 0, 35, 35)) {
                    player1.stop_movement();
                    pause();
                }*/
            }
            if (currentEvent.type == Input.TouchEvent.TOUCH_UP) {
                Log.i("GameScreen", "TOUCH_UP");
                // Screen pressed in bounds
                if (inBounds(currentEvent, 0, 180, 770, 860)) {
                    if (System.currentTimeMillis() - delay_time > 2000) {
                        this.player1.start_movement();
                        delay_time = 0;
                        Log.i("GameScreen", "player movement started");
                    }
                }
            }
        }

        // 2. Check miscellaneous events like death:
        if (!enemy1added) {
            this.enemy_list.add(this.enemy1);
            Log.i("GameScreen", "first enemy spawned");
            this.enemy1added=true;
            this.previousSpawnTime[0] = System.currentTimeMillis();
        }

        // UPDATE TO CHECK FOR COLLISION BETWEEN PLAYER AND ENEMIES
    /*        if (player1.player_rect)
            state = GameState.GameOver;*/

        // 3. Call individual update methods here.
        // This is where all the game updates happen.
        // For example, player1.update();
        this.player1.update_char();
        for (int i=0; i < this.enemy_list.size(); i++) {
            this.enemy_list.get(i).update_enemy();
        }

        for (int i=0; i < this.enemy_list.size(); i++) {
            if (this.player1.dying == false) {
                if (this.checkForOverlap.overlapRectangles(this.player1.player_rect, this.enemy_list.get(i).bounds)) {
                    this.player1.dying = true;
                    Log.i("OVERLAP FOUND", String.valueOf(this.player1.getX_pos()));
                    break;
                }
            }
        }


        Log.i("GameScreen", "update2, char and enemy");
        if (System.currentTimeMillis() - this.previousSpawnTime[0] > 1600) {
            Log.i(String.valueOf(System.currentTimeMillis()), String.valueOf(this.previousSpawnTime));
            this.timerRunnable.run(); // previousSpawnTime modified in runnable
            Log.i("afterup2", ("enemy spawned " + String.valueOf(this.enemy_list.size())));
        }

        for (int i=0; i < this.enemy_list.size(); i++) {
            if ((this.enemy_list.get(i).getY_pos() > 820)) {
                this.enemy_list.remove(i);
                Log.i("after up2", "enemy removed");
            }
        }

/*        ArrayList<Projectile> projectiles = robot.getProjectiles();
        for (int projectileIndex = 0; projectileIndex < projectiles.size(); projectileIndex++) {
            Projectile projectile = (Projectile) projectiles
                    .get(projectileIndex);
            if (projectile.isVisible())
                projectile.update();
            else
                projectiles.remove(projectileIndex);
        }*/

/*            updateTiles();
            hb.update();
            hb2.update();
            bg1.update();
            bg2.update();
            animate();*/

        // We have fallen to our death.
/*            if (player1.getY_pos() > 500) {
                Log.i("GameScreen", "GameOver");
                state = GameState.GameOver;
            }*/
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width,
                             int height) {
        Log.i("GameScreen", "inBounds");
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    // UPDATE FOR PAUSE SCREEN IMPLEMENTATION
/*    private void updatePaused(List<Input.TouchEvent> touchEvents) {
        int touchEventsSize = touchEvents.size();
        for (int touchEventIndex = 0; touchEventIndex < touchEventsSize; touchEventIndex++) {
            Input.TouchEvent currentEvent = (Input.TouchEvent) touchEvents
                    .get(touchEventIndex);
            // The user picks up their finger
            if (currentEvent.type == Input.TouchEvent.TOUCH_UP) {
                // Anywhere on the top of the screen except in the upper left
                // corner
                if (inBounds(currentEvent, 0, 0, 800, 240))
                    if (!inBounds(currentEvent, 0, 0, 35, 35))
                        // unPause
                        resume();
                // If anywhere in the lower half of the screen
                if (inBounds(currentEvent, 0, 240, 800, 240)) {
                    // Clean up and go to menu.
                    nullify();
                    goToMenu();
                }
            }
        }

    }*/

    // UPDATE FOR GAME OVER IMPLEMENTATION
    private void updateGameOver(List<Input.TouchEvent> touchEvents) {
        Log.i("GameScreen", "GAME CURRENTLY OVER");
        int touchEventsSize = touchEvents.size();
        for (int touchEventIndex = 0; touchEventIndex < touchEventsSize; touchEventIndex++) {
            Input.TouchEvent currentEvent = (Input.TouchEvent) touchEvents
                    .get(touchEventIndex);
            // If the user touches anywhere in the screen
            // while the game over screen is shown,
            // clean up and return to the menu (on a new game.)
            if (currentEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                if (inBounds(currentEvent, 0, 0, 800, 480)) {
                    Log.i("Game Screen", "Restarting Game...");
                    nullify();
                    game.setScreen(new GameScreen(game));
                    state = GameState.Ready;
                    delay_time = System.currentTimeMillis();
                    return;
                }
            }
        }

    }

    //@Override
    public void paint(float deltaTime) {
        Log.i("GameScreen", "paint1");
        Graphics g = game.getGraphics();
        g.clearScreen(Color.GRAY);


        // We start in Z-order: Background first, then map tiles:
        //g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
        //g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());

        // Now we want the bullets/enemies:
/*        ArrayList<falling_enemy> falling_enemies = robot.getProjectiles();
        for (int projectileIndex = 0; projectileIndex < projectiles.size(); projectileIndex++) {
            Projectile projectile = (Projectile) projectiles
                    .get(projectileIndex);
            g.drawRect(projectile.getX(), projectile.getY(), 10, 5,
                    android.graphics.Color.YELLOW);
        }*/



        // Now game elements:
        Log.i("GameScreen", "paint2");

        g.drawImage(g.newImage("left_wall_image.png", Graphics.ImageFormat.RGB565), 0, 0);
        g.drawImage(g.newImage("right_wall_image.png", Graphics.ImageFormat.RGB565), 384, 0);
        g.drawImage(g.newImage("player_image.png", Graphics.ImageFormat.RGB565), (int) this.player1.getX_pos(), (int) this.player1.getY_pos());
        Log.i("char X", String.valueOf(this.player1.getX_pos()));
        Log.i("char Y", String.valueOf(this.player1.getY_pos()));
        Log.i("enemy X", String.valueOf(this.enemy1.getX_pos()));
        Log.i("enemy Y", String.valueOf(this.enemy1.getY_pos()));

        for (int i = 0; i < this.enemy_list.size(); i++) {
            g.drawImage(g.newImage("enemy_image1.png", Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
        }

        //g.drawImage(g.newImage("tester_image.png", Graphics.ImageFormat.RGB565), 0, 0);

/*        g.drawImage(g.newImage("player_image.png", Graphics.ImageFormat.RGB565), 100, 400);
        g.drawImage(g.newImage("player_image.png", Graphics.ImageFormat.RGB565), 400, 100);*/


/*        g.drawImage(currentSprite, robot.getCenterX() - 61,
                robot.getCenterY() - 63);
        g.drawImage(hanim.getImage(), hb.getCenterX() - 48,
                hb.getCenterY() - 48);
        g.drawImage(hanim.getImage(), hb2.getCenterX() - 48,
                hb2.getCenterY() - 48);*/

        // And now, we overlay the UI:
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
/*        if (state == GameState.Paused)
            drawPausedUI();*/
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    @Override
    public void dispose() {
        Log.i("GameScreen", "dispose");

    }

    @Override
    public void resume() {
        Log.i("GameScreen", "resume");

    }


    @Override
    public void pause() {
        Log.i("GameScreen", "pause");
        this.timerHandler.removeCallbacks(this.timerRunnable);
    }

    private void nullify() {
        // Set all variables to null. We'll recreate
        // them in the constructor.
        Log.i("GameScreen", "nullify");
        paint = null;
        this.player1 = null;
        for(int i = 0; i < this.enemy_list.size(); i++) {
            this.enemy_list.clear();
        }
        // Call the garbage collector to clean up our memory.
        System.gc();
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        // Darken the screen
        g.drawARGB(155, 0, 0, 0);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        // ADD RUNNING UI
        g.drawString(("Height Scaled: " + String.valueOf(this.player1.player_score)), 45, 50, paint2);
        g.drawRect(400, 20, 60, 60, Color.CYAN);
    }

/*    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the screen to display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Resume", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);

    }*/

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        //g.drawRect(0, 0, 482, 802, Color.BLACK);
        g.drawString("Game Over", 240, 200, paint);
        g.drawString("Tap to Try Again!", 240, 320, paint);
    }
}

