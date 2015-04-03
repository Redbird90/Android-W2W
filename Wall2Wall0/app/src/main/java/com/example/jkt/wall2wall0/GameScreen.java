package com.example.jkt.wall2wall0;

import android.content.Context;
import android.graphics.*;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import com.example.jkt.wall2wall0.impl.AndroidImage;
import com.example.jkt.wall2wall0.math.OverlapTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by JDK on 3/30/2015.
 */
public class                                GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }
    int a = 0;

    GameState state = GameState.Ready;
    //private static player_char player1;
    //private static example_enemy enemy1;
    Paint paint;
    public GameScreen(Game game) {
        super(game);
        Log.i("GameScreen", "start");


        // Initialize game objects


        //player_char player1 = new player_char(210f, 1113f, 40f, 40f);
        //example_enemy enemy1 = new example_enemy(450f, 80f, 40f, 40f);

        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
    }

    //player_char player1 = new player_char(210f, 1113f, 40f, 40f);



    Graphics g = game.getGraphics();  //ONLY NEEDED IF DRAWING AT GAME START.
    player_char player1 = new player_char(96f, 576f, 40f, 50f);  // TRIED TO FORCE TO AndroidImage
    example_enemy enemy1 = new example_enemy(200f, 10f, 60f, 60f);
    Random randomGenerator = new Random();
    int enemyspawntime;
    ArrayList<example_enemy> enemy_list = new ArrayList(12);
    boolean spawn_enemy = false;
    OverlapTester checkForOverlap = new OverlapTester();




    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        Log.i("GameScreen", "general update");

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
        Log.i("GameScreen", "state is RUNNING");
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
/*        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);*/

    }


    private void updateReady(List<Input.TouchEvent> touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game
        // begins. state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        if (touchEvents.size() > 0) //CHANGE
            Log.i("GameScreen", "updateReadytoRunning");
            state = GameState.Running;

    }

    private void updateRunning(List<Input.TouchEvent> touchEvents, float deltaTime) {

        // This is identical to the update() method from Unit 2/3.

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
                    player1.start_movement();
                    //currentSprite = anim.getImage();
                }
            }
        }
        Log.i("GameScreen", "update2, past touch handling");

        // 2. Check miscellaneous events like death:

        // UPDATE TO CHECK FOR COLLISION BETWEEN PLAYER AND ENEMIES
    /*        if (player1.player_rect)
            state = GameState.GameOver;*/

        // 3. Call individual update methods here.
        // This is where all the game updates happen.
        // For example, player1.update();
        player1.update_char();
        enemy1.update_enemy();
        for (int i=0; i < enemy_list.size(); i++) {
            enemy_list.get(i).update_enemy();
        }

        for (int i=0; i < enemy_list.size(); i++) {
            if (player1.dying == false) {
                if (checkForOverlap.overlapRectangles(player1.player_rect, enemy_list.get(i).bounds)) {
                    player1.dying = true;
                    Log.i("OVERLAP FOUND", String.valueOf(player1.getX_pos()));
                    break;
                }
            }
        }


        Log.i("GameScreen", "update3, char and enemy");
        if (enemyspawntime + deltaTime > 30) {
            enemyspawntime += 30;
            example_enemy later_enemy = new example_enemy((float) (((randomGenerator.nextInt(10000)/10000.0)*258)+110), (-80f), 60, 60);
            enemy_list.add(later_enemy);
/*            example_enemy next_enemy;
            next_enemy = (example_enemy) enemy_list.get(0);*/
            spawn_enemy = true;
            Log.i("afterup3", "enemy spawned");
        }

        for (int i=0; i < enemy_list.size(); i++) {
            if ((enemy_list.get(i).getY_pos() > 820)) {
                enemy_list.remove(i);
                Log.i("after up3", "enemy removed");
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
/*    private void updateGameOver(List<Input.TouchEvent> touchEvents) {
        int touchEventsSize = touchEvents.size();
        for (int touchEventIndex = 0; touchEventIndex < touchEventsSize; touchEventIndex++) {
            Input.TouchEvent currentEvent = (Input.TouchEvent) touchEvents
                    .get(touchEventIndex);
            // If the user touches anywhere in the screen
            // while the game over screen is shown,
            // clean up and return to the menu (on a new game.)
            if (currentEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                if (inBounds(currentEvent, 0, 0, 800, 480)) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }*/

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
/*        g.drawImage(player_char_image, (int) player1.getX_pos(), (int) player1.getY_pos());
        g.drawImage(enemy1_char_image, (int) enemy1.getX_pos(), (int) enemy1.getY_pos());*/
/*        g.drawImage(player_char_image, 0, 0);
        g.drawImage(enemy1_char_image, 200, 400);*/
        Canvas canvas = g.getCanvas();

/*        player1.update_char();
        enemy1.update_enemy();*/
        g.drawImage(g.newImage("left_wall_image.png", Graphics.ImageFormat.RGB565), 0, 0);
        g.drawImage(g.newImage("right_wall_image.png", Graphics.ImageFormat.RGB565), 384, 0);
        g.drawImage(g.newImage("enemy_image1.png", Graphics.ImageFormat.RGB565), (int) enemy1.getX_pos(), (int) enemy1.getY_pos());
        g.drawImage(g.newImage("player_image.png", Graphics.ImageFormat.RGB565), (int) player1.getX_pos(), (int) player1.getY_pos());
        Log.i("char X", String.valueOf(player1.getX_pos()));
        Log.i("char Y", String.valueOf(player1.getY_pos()));
        Log.i("enemy X", String.valueOf(enemy1.getX_pos()));
        Log.i("enemy Y", String.valueOf(enemy1.getY_pos()));

        for (int i = 0; i < enemy_list.size(); i++) {
            g.drawImage(g.newImage("enemy_image1.png", Graphics.ImageFormat.RGB565), (int) enemy_list.get(i).getX_pos(), (int) enemy_list.get(i).getY_pos());
        }

        //g.drawImage(g.newImage("tester_image.png", Graphics.ImageFormat.RGB565), 0, 0);

/*        g.drawImage(g.newImage("player_image.png", Graphics.ImageFormat.RGB565), 100, 400);
        g.drawImage(g.newImage("player_image.png", Graphics.ImageFormat.RGB565), 400, 100);*/


/*        g.drawImage(currentSprite, robot.getCenterX() - 61,
                robot.getCenterY() - 63);
        g.drawImage(hanim.getImage(), hb.getCenterX() - 48,
                hb.getCenterY() - 48);
        g.drawImage(hanim.getImage(), hb2.getCenterX() - 48,
                hb2.getCenterY() - 48);

        // And now, we overlay the UI:
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();*/

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
    }

    private void nullify() {
        // Set all variables to null. We'll recreate
        // them in the constructor.
        Log.i("GameScreen", "nullify");
        paint = null;
        player1 = null;
        enemy1 = null;
        // Call the garbage collector to clean up our memory.
        System.gc();
    }

}

