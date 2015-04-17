package com.example.jkt.wall2wall0;

import android.content.Context;
import android.graphics.*;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jkt.wall2wall0.impl.AndroidGame;
import com.example.jkt.wall2wall0.impl.AndroidImage;
import com.example.jkt.wall2wall0.impl.AndroidMusic;
import com.example.jkt.wall2wall0.impl.AndroidSound;
import com.example.jkt.wall2wall0.math.OverlapTester;
import com.example.jkt.wall2wall0.math.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JDK on 3/30/2015.
 */
public class GameScreen extends Screen {
    private player_char player1;
    private falling_enemy enemy1;
    private boolean enemy1added;
    private final Random randomGenerator;
    private final ArrayList<falling_enemy> enemy_list;
    private final OverlapTester checkForOverlap;
    private boolean drawPlayer;
    private final long[] previousSpawnTime;
    private final Handler timerHandler;
    private final Runnable timerRunnable;
    private long delay_time;
    private Rectangle pause_rect;
    private int highScore;
    private int passes;
    private int currentScore;
    private boolean newHighScore;
    private long menu_delay;
    private int top_walls_y_pos;
    private int bot_walls_y_pos;
    private int top_backg_y_pos;
    private int bot_backg_y_pos;
    private float fpscounter;
    private float cam_scroll1;
    private float cam_scroll2;
    public boolean height_thresh1;
    public boolean height_thresh2;

    public AndroidMusic game_music;
    public AndroidSound death_sound;
    private long pause_time;
    private long time_paused;
    private boolean tutorial_time = true;
    private int current_level = 1;
    private int opacity_num = 5;
    private boolean reached_255_opacity = false;
    private boolean transition_incomplete = true;

    enum GameState {
        Ready, Running, Paused, GameOver
    }
    int a = 0;

    GameState state = GameState.Ready;
    //private static player_char player1;
    //private static falling_enemy enemy1;
    Paint paint;
    Paint paint2;
    Paint paint3;
    Paint paint4;
    public GameScreen(Game game) {
        super(game);
        Log.i("GameScreen", "start");

        // Initialize game objects

        this.player1 = new player_char(80f, 576f, 40f, 55f);
        this.enemy1 = new falling_enemy(200f, -30f, 60f, 60f, 0);
        this.enemy1added = false;
        this.randomGenerator = new Random();
        this.enemy_list = new ArrayList<falling_enemy>(12);
        this.checkForOverlap = new OverlapTester();
        this.drawPlayer = true;
        this.previousSpawnTime = new long[1];
        this.passes = 0;
        this.currentScore = 0;
        this.newHighScore = false;
        this.highScore = Settings.getHighScore();
        this.top_backg_y_pos = -800;
        this.bot_backg_y_pos = 0;
        this.top_walls_y_pos = -800;
        this.bot_walls_y_pos = 0;
        this.fpscounter = 0;
        this.delay_time = System.currentTimeMillis();
        this.cam_scroll1 = 0;
        this.cam_scroll2 = 0;
        this.timerHandler = new Handler(Looper.getMainLooper());
        this.timerRunnable = new Runnable() {
            @Override
            public void run() {
                Log.i("GameScreen", "ready to spawn");
                if (enemy_list.size() < 12) {
                    falling_enemy later_enemy;
                    int enemy_num = randomGenerator.nextInt(3);
                    if (enemy_num == 0) {
                        later_enemy = new falling_enemy((float) (((randomGenerator.nextInt(10000) / 10000.0) * 230) + 90), (-80f), 80, 80, enemy_num);
                    } else if (enemy_num == 1) {
                        later_enemy = new falling_enemy((float) (((randomGenerator.nextInt(10000) / 10000.0) * 230) + 90), (-80f), 64, 96, enemy_num);
                    } else {
                        later_enemy = new falling_enemy((float) (((randomGenerator.nextInt(10000) / 10000.0) * 230) + 90), (-80f), 96, 64, enemy_num);
                    }
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
        paint2.setTextSize(30);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.YELLOW);

        paint3 = new Paint();
        paint3.setTextSize(20);
        paint3.setTextAlign(Paint.Align.CENTER);
        paint3.setAntiAlias(true);
        paint3.setColor(Color.YELLOW);

        paint4 = new Paint();
        paint4.setTextSize(15);
        paint4.setTextAlign(Paint.Align.CENTER);
        paint4.setAntiAlias(true);
        paint4.setColor(Color.BLACK);

        pause_rect = new Rectangle(395, 15, 70, 70);

        if (Settings.soundEnabled){
            game_music = (AndroidMusic) game.getAudio().newMusic("179562__clinthammer__clinthammermusic-ts-bass.wav");
            game_music.play();
            game_music.setLooping(true);
            death_sound = (AndroidSound) game.getAudio().newSound("Realistic_Punch.wav");
        }

    }

    Graphics g = game.getGraphics();  //ONLY NEEDED IF DRAWING AT GAME START.

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        Log.i("FPSCOUNTER", String.valueOf((System.currentTimeMillis() - this.fpscounter)*(1.000/1.000)));
        this.fpscounter = System.currentTimeMillis();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different
        // update methods. Refer to Unit 3's code. We did a similar
        // thing without separating the update method.

        // Note that order is important here. If the game is
        // in Ready state, we should only go to Running.
        // From Running, we should only go to Paused or GameOver.

        if (state == GameState.Ready) {
            Log.i("GameScreen", "state is READY");
            updateReady(touchEvents);
        }
        if (state == GameState.Running) {
            updateRunning(touchEvents, deltaTime);
        }
        if (state == GameState.Paused) {
            Log.i("GameScreen", "state is PAUSED");
            updatePaused(touchEvents);
        }
        if (state == GameState.GameOver) {
            Log.i("GameScreen", "state is GAME OVER");
            updateGameOver(touchEvents);
        }
    }


        private void updateReady(List<Input.TouchEvent> touchEvents) {

            // This example starts with a "Ready" screen.
            // When the user touches the screen, the game
            // begins. state now becomes GameState.Running.
            // Now the updateRunning() method will be called!

            if (true) {
                state = GameState.Running;
                this.drawPlayer = true;
            }
        }

        private void updateRunning(List<Input.TouchEvent> touchEvents, float deltaTime) {

            // 1. All touch input is handled here:
            Log.i("GameScreen", "update1, updateRunning started");
            int touchEventsSize = touchEvents.size();
            for (int currentTouchEventIndex = 0; currentTouchEventIndex < touchEventsSize; currentTouchEventIndex++) {
                Input.TouchEvent currentEvent = (Input.TouchEvent) touchEvents
                        .get(currentTouchEventIndex);
                if (currentEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                    // Check if pause button pressed
                    if (inBounds(currentEvent, 395, 15, 70, 70)) {
                        pause();
                    }
                }
                if (currentEvent.type == Input.TouchEvent.TOUCH_UP) {
                    // Screen pressed in game bounds
                    if (inBounds(currentEvent, 0, 180, 770, 860)) {
                        if (System.currentTimeMillis() - delay_time > 300) { // CHANGE 300 TO 2000
                            if (tutorial_time) {
                                tutorial_time = false;
                            } else {
                                this.player1.start_movement();
                                delay_time = 0;
                                Log.i("GameScreen", "player movement started");
                            }
                        }
                    }
                }
            }
            // 2. Check miscellaneous events like death:
            if (this.player1.getY_pos() > 870) {
                if (Settings.soundEnabled) {
                    death_sound.play(1.0f);
                }
                this.state = GameState.GameOver;
                Log.i("GameScreen", "player fell to DEATH");
                this.drawPlayer = false;
                this.currentScore = (int) player1.player_score;
                if (this.currentScore > this.highScore) {
                    this.highScore = this.currentScore;
                    this.newHighScore = true;
                    Settings.setHighScore(this.highScore);
                    Settings.save(game.getFileIO());
                }
                delay_time = (long) 0;
            }
            if (player1.first_jump) {
                if (!enemy1added) {
                    this.enemy_list.add(this.enemy1);
                    Log.i("GameScreen", "first enemy spawned");
                    this.enemy1added = true;
                    this.previousSpawnTime[0] = System.currentTimeMillis();
                }
            }

            if (this.player1.getY_pos() > 526) {

            }

            // 3. Call individual update methods here.
            // This is where all the game updates happen.
            // For example, player1.update();
            this.player1.update_char();

            // Check player height and update appropriate variables
            if (this.player1.getY_pos() <= 526) {
                this.height_thresh1 = true;
                if (this.player1.getY_pos() <= 476) {
                    this.height_thresh2 = true;
                } else if (this.player1.getY_pos() > 476 && this.height_thresh2) {
                    this.height_thresh2 = false;
                }
            } else if (this.player1.getY_pos() > 526 && this.height_thresh1) {
                this.height_thresh1 = false;
            }

            // Check height variables and set falling speed variables
            if (this.height_thresh1) {
                if (this.height_thresh2) {
                    this.cam_scroll2 = 4.0f;
                } else {
                    this.cam_scroll2 = 0.0f;
                }
                this.cam_scroll1 = 2.0f;
            } else {
                this.cam_scroll1 = 0.0f;
            }

            for (int i = 0; i < this.enemy_list.size(); i++) {
                // Speed up blocks when player jumps
                if (this.player1.jumped) {
                    Log.i("TESTING", "loop1");
                    if (this.enemy_list.get(i).player_jumping == false) {
                        this.enemy_list.get(i).setPlayer_jumping(true);
                    }
                    this.enemy_list.get(i).update_enemy();
                } else {
                    Log.i("TESTING", "loop2");
                    if (this.enemy_list.get(i).player_jumping == true) {
                        this.enemy_list.get(i).setPlayer_jumping(false);
                    }
                    this.enemy_list.get(i).update_enemy();
                }
                // Speed up blocks when player is too high
                if (this.height_thresh1) {
                    if (this.height_thresh2) {
                        this.enemy_list.get(i).setY_pos(this.enemy_list.get(i).getY_pos() + 4.0f);
                    }
                    this.enemy_list.get(i).setY_pos(this.enemy_list.get(i).getY_pos() + 2.0f);
                }
            }
            Log.i("CAM", String.valueOf(this.cam_scroll1) + "," + String.valueOf(this.cam_scroll2));
            if (this.cam_scroll1 != 0 && this.cam_scroll2 != 0) {
                this.top_backg_y_pos += ((this.cam_scroll1 + this.cam_scroll2) - 2.0f);
                this.bot_backg_y_pos += ((this.cam_scroll1 + this.cam_scroll2) - 2.0f);
            }
            Log.i("TESTING1A", String.valueOf(this.top_backg_y_pos) + "," + String.valueOf(this.bot_backg_y_pos));
            Log.i("TESTING2A", String.valueOf(this.top_walls_y_pos) + "," + String.valueOf(this.bot_walls_y_pos));

            this.top_walls_y_pos += (this.cam_scroll1 + this.cam_scroll2);
            this.bot_walls_y_pos += (this.cam_scroll1 + this.cam_scroll2);


            if (player1.jumped) {
/*                Log.i("TESTING BACKG", (String.valueOf(top_backg_y_pos) + "," + String.valueOf(bot_backg_y_pos)));
                Log.i("TESTING WALLS", (String.valueOf(top_walls_y_pos) + "," + String.valueOf(bot_walls_y_pos)));*/
                // Handle background scrolling as player is jumping
                Log.i("TESTING1B", String.valueOf(this.top_backg_y_pos) + "," + String.valueOf(this.bot_backg_y_pos));
                Log.i("TESTING2B", String.valueOf(this.top_walls_y_pos) + "," + String.valueOf(this.bot_walls_y_pos));
                this.top_backg_y_pos += 4.0f;
                this.bot_backg_y_pos += 4.0f;
                // Handle walls scrolling as player is jumping
                this.top_walls_y_pos += 7.0f;
                this.bot_walls_y_pos += 7.0f;
            }

            // Redraw background and walls once they reach the bottom
            if (this.bot_backg_y_pos > 800) {
                this.bot_backg_y_pos = -800;
                this.top_backg_y_pos = 0;
            } else if (this.top_backg_y_pos > 800) {
                this.top_backg_y_pos = -800;
                this.bot_backg_y_pos = 0;
            }

            if (this.bot_walls_y_pos > 800) {
                this.bot_walls_y_pos = -800;
                this.top_walls_y_pos = 0;
            } else if (this.top_walls_y_pos > 800) {
                this.top_walls_y_pos = -800;
                this.bot_walls_y_pos = 0;
            }


            for (int i = 0; i < this.enemy_list.size(); i++) {
                if (this.player1.dying == false) {
                    if (this.checkForOverlap.overlapRectangles(this.player1.player_rect, this.enemy_list.get(i).bounds)) {
                        this.player1.dying = true;
                        Log.i("OVERLAP FOUND", String.valueOf(this.player1.getX_pos()));
                        Log.i("OVERLAP", String.valueOf(this.enemy_list.get(i).bounds.width));
                        // CHANGE TO DIFF SOUND EFFECT
                        if (Settings.soundEnabled) {
                            death_sound.play(1f);
                        }
                        break;
                    }
                }
            }

            Log.i("GameScreen", "update2, char and enemy");
            if (player1.first_jump) {
                if (System.currentTimeMillis() - this.previousSpawnTime[0] > (randomGenerator.nextInt(250) + 1600)) {
                    //this.timerRunnable.run(); // previousSpawnTime modified in runnable
                }
            }

            for (int i = 0; i < this.enemy_list.size(); i++) {
                if ((this.enemy_list.get(i).getY_pos() > 820)) {
                    this.enemy_list.remove(i);
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


        }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    // UPDATE FOR PAUSE SCREEN IMPLEMENTATION
private void updatePaused(List<Input.TouchEvent> touchEvents) {
    int touchEventsSize = touchEvents.size();
    for (int touchEventIndex = 0; touchEventIndex < touchEventsSize; touchEventIndex++) {
        Input.TouchEvent currentEvent = (Input.TouchEvent) touchEvents.get(touchEventIndex);
        // The user picks up their finger
        if (currentEvent.type == Input.TouchEvent.TOUCH_DOWN) {
            // If on Pause Button again
            if (inBounds(currentEvent, 395, 15, 70, 70)) {
                if (this.state == GameState.Paused && this.passes < 1) {
                    this.passes ++;
                } else {
                    resume();
                }
            }
            // If on Menu Button
            if (inBounds(currentEvent, 140, 365, 200, 50)) {
                // Clean up and go to menu.
                nullify();
                goToMenu();
            }
        }
    }
}

    // UPDATE FOR GAME OVER IMPLEMENTATION
    private void updateGameOver(List<Input.TouchEvent> touchEvents) {
        int touchEventsSize = touchEvents.size();
        for (int touchEventIndex = 0; touchEventIndex < touchEventsSize; touchEventIndex++) {
            Input.TouchEvent currentEvent = (Input.TouchEvent) touchEvents
                    .get(touchEventIndex);
            // If the user touches anywhere in the screen
            // while the game over screen is shown,
            // clean up and return to the menu (on a new game.)
            if (currentEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                if (inBounds(currentEvent, 0, 80, 800, 400)) {
                    nullify();
                    game.setScreen(new GameScreen(game));  // CREATES NEW GAMESCREEN EVERY GAME
                    state = GameState.Ready;
                    delay_time = System.currentTimeMillis();
                    return;
                }
            }
        }

    }

    //@Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.clearScreen(Color.BLACK);


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


        if (current_level == 1) {
            // Now game elements:
            // Draw two sets of scrolling backgrounds
            g.drawImage(g.newImage("background_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 0, this.top_backg_y_pos);
            g.drawImage(g.newImage("background_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 0, this.bot_backg_y_pos);
            Log.i("TESTING1", String.valueOf(this.top_backg_y_pos) + "," + String.valueOf(this.bot_backg_y_pos));
            Log.i("TESTING2", String.valueOf(this.top_walls_y_pos) + "," + String.valueOf(this.bot_walls_y_pos));
            // Draw two sets of left walls
            g.drawImage(g.newImage("left_wall_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 0, this.top_walls_y_pos);
            g.drawImage(g.newImage("left_wall_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 0, this.bot_walls_y_pos);
            // Draw two sets of right walls
            g.drawImage(g.newImage("right_wall_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 400, this.top_walls_y_pos);
            g.drawImage(g.newImage("right_wall_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 400, this.bot_walls_y_pos);
        } else if (current_level == 2) {  // REPLACE IMAGES ONCE OBT
            // Now game elements:
            // Draw two sets of scrolling backgrounds
            g.drawImage(g.newImage("background_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 0, this.top_backg_y_pos);
            g.drawImage(g.newImage("background_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 0, this.bot_backg_y_pos);
            Log.i("TESTING1", String.valueOf(this.top_backg_y_pos) + "," + String.valueOf(this.bot_backg_y_pos));
            Log.i("TESTING2", String.valueOf(this.top_walls_y_pos) + "," + String.valueOf(this.bot_walls_y_pos));
            // Draw two sets of left walls
            g.drawImage(g.newImage("left_wall_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 0, this.top_walls_y_pos);
            g.drawImage(g.newImage("left_wall_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 0, this.bot_walls_y_pos);
            // Draw two sets of right walls
            g.drawImage(g.newImage("right_wall_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 400, this.top_walls_y_pos);
            g.drawImage(g.newImage("right_wall_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 400, this.bot_walls_y_pos);
        }

        for (int i = 0; i < this.enemy_list.size(); i++) {
            falling_enemy current_enemy_draw = this.enemy_list.get(i);
            if (current_enemy_draw.getEnemy_num() == 0) {
                g.drawImage(g.newImage("enemy_image1_larger.png", Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            } else if (current_enemy_draw.getEnemy_num() == 1) {
                g.drawImage(g.newImage("enemy_image2.png", Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            } else if (current_enemy_draw.getEnemy_num() == 2) {
                g.drawImage(g.newImage("enemy_image3.png", Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            } else if (current_enemy_draw.getEnemy_num() == 3) {

            } else if (current_enemy_draw.getEnemy_num() == 4) {

            } else if (current_enemy_draw.getEnemy_num() == 5) {

            } else if (current_enemy_draw.getEnemy_num() == 6) {

            } else if (current_enemy_draw.getEnemy_num() == 7) {

            } else if (current_enemy_draw.getEnemy_num() == 8) {

            } else {

            }
        }
        g.drawImage(g.newImage("player_image.png", Graphics.ImageFormat.RGB565), (int) this.player1.getX_pos(), (int) this.player1.getY_pos());

        // And now, we overlay the UI:
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    @Override
    public void dispose() {
        Log.i("GameScreen", "dispose");

    }

    @Override
    public void resume() {
        this.time_paused = System.currentTimeMillis() - this.pause_time;
        this.game_music.seekBegin();
        this.previousSpawnTime[0] += time_paused;
        this.passes = 0;
        this.state = GameState.Running;
    }


    @Override
    public void pause() {
        this.pause_time = System.currentTimeMillis();
        if (Settings.soundEnabled) {
            this.game_music.pause();
        }
        Settings.save(game.getFileIO());
        this.timerHandler.removeCallbacks(this.timerRunnable);
        this.state = GameState.Paused
;    }

    private void nullify() {
        // Set all variables to null. We'll recreate
        // them in the constructor.
        Log.i("GameScreen", "nullify");
        paint = null;
        paint2 = null;
        paint3 = null;
        paint4 = null;
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
        if (this.player1.player_score > 1000) {
            g.drawString((String.valueOf((int) this.player1.player_score) + "m"), 40, 40, paint3);
        } else {
            g.drawString((String.valueOf((int) this.player1.player_score) + "m"), 40, 40, paint2);
        }
        g.drawRect(410, 20, 60, 60, Color.CYAN);
        g.drawString("Pause", 440, 50, paint4);

        if (this.player1.player_score >= 300 && transition_incomplete) {
            Log.i("STARTING", String.valueOf(this.opacity_num));
            g.drawARGB(this.opacity_num, 255, 255, 255);
            if (this.opacity_num < 255 && !this.reached_255_opacity) {
                this.opacity_num += 25;
            } else if (this.opacity_num == 255) {
                this.opacity_num -= 25;
                this.reached_255_opacity = true;
                this.current_level = 2;
            } else if (this.opacity_num < 255 && this.reached_255_opacity) {
                this.opacity_num -= 25;
            }
            if (this.opacity_num < 0) {
                transition_incomplete = false;
                Log.i("ENDING", "TRANSITION");
            }
        }

        if (tutorial_time) {
            g.drawString("Tap anywhere to jump.", 240, 350, paint4);
            g.drawString("Avoid the obstacles", 240, 400, paint);
            g.drawString("falling from above!", 240, 430, paint);
        }

    }

private void drawPausedUI() {
    Graphics g = game.getGraphics();
    // Darken the screen to display the Paused screen.
    g.drawARGB(155, 0, 0, 0);
    g.drawRect(140, 365, 200, 50, Color.BLACK);
    g.drawRect(410, 20, 60, 60, Color.CYAN);
    g.drawString("Resume", 440, 50, paint4);
    g.drawString("Back to Menu", 240, 400, paint);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawARGB(155, 0, 0, 0);
        //g.drawRect(0, 0, 482, 802, Color.BLACK);
        g.drawString("You Lose!", 240, 200, paint);
        // SHOW CURRENT AND HIGH SCORES
        g.drawString("Tap to Try Again!", 240, 320, paint);
        g.drawString("Score: " + String.valueOf(this.currentScore) + "m", 240, 450, paint);
        if (newHighScore) {
            g.drawString("NEW HIGH SCORE!", 240, 550, paint2);
        }
    }

    private void goToMenu() {
        game.setScreen(new MainMenuScreen(game));
    }
}

