package com.example.jkt.wall2wall0;

import android.graphics.*;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.jkt.wall2wall0.impl.AndroidMusic;
import com.example.jkt.wall2wall0.impl.AndroidSound;
import com.example.jkt.wall2wall0.math.Circle;
import com.example.jkt.wall2wall0.math.OverlapTester;
import com.example.jkt.wall2wall0.math.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JDK on 3/30/2015.
 */
public class GameScreen extends Screen {

    private PlayerChar player1;
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
    private int highScore;
    private int passes;
    private int currentScore;
    private boolean newHighScore;
    private long menu_delay;
    private ArrayList<Object> tile_x_positions;
    private ArrayList<Object> tile_y_positions;
    private int top_walls_y_pos;
    private int bot_walls_y_pos;
    private int top_backg_y_pos;
    private int bot_backg_y_pos;
    private int bot_forestbgtree_y_pos;
    private int top_forestbgtree_y_pos;
    private float fpscounter;
    private float cam_scroll1;
    private float cam_scroll2;
    public boolean height_thresh1;
    public boolean height_thresh2;
    private SpawnTimer enemySpawnTimer;
    private ArrayList<SpawnEvent> enemySpawnEventArray;
    private int walls_blitted;
    private WallHazardHandler wallHazardHandler;
    private ArrayList<WallHazard> hazardBoundsArray;
    private int jump_type;

    public AndroidMusic game_music;
    public AndroidSound death_sound;
    private long pause_time;
    private long time_paused;
    private boolean tutorial_time = true;
    private int current_level = 1;
    private int opacity_num = 5;
    private boolean reached_255_opacity = false;
    private boolean transition_incomplete = true;
    private long game_start_time;
    private long current_time;
    private int enemy_count = 0;
    private boolean enemyArrayParsed;
    private boolean tileArrayRemakeNeeded;
    private boolean first_tileArrayRemake;
    private int forest_tree_1_y_pos;
    private int forest_tree_2_y_pos;
    private int forest_tree_3_y_pos;
    private int forest_tree_4_y_pos;
    private int forest_branch_reversed_x_pos;
    private int forest_branch_1_y_pos;
    private int forest_branch_normal_x_pos;
    private int forest_branch_2_y_pos;
    private int forest_branch_3_y_pos;
    private int forest_branch_4_y_pos;

    private final float ENEMY_Y_SPAWN_POS = -110f;

    public float ENEMY_1_WIDTH=100f;
    public float ENEMY_1_HEIGHT=60f;
    public float ENEMY_2_WIDTH=64f;
    public float ENEMY_2_HEIGHT=96f;
    public float ENEMY_3_WIDTH=62f;
    public float ENEMY_3_HEIGHT=79f;
    public float ENEMY_4_WIDTH=57f;
    public float ENEMY_4_HEIGHT=100f;
    public float ENEMY_5_WIDTH=90f;
    public float ENEMY_5_HEIGHT=114f;
    public float ENEMY_6_WIDTH=95f;
    public float ENEMY_6_HEIGHT=86f;
    public float ENEMY_7_RADIUS=40f;
    public float ENEMY_8_WIDTH=62f;
    public float ENEMY_8_HEIGHT=100f;
    public float ENEMY_9_WIDTH=65f;
    public float ENEMY_9_HEIGHT=60f;
    public float ENEMY_10_WIDTH=95f;
    public float ENEMY_10_HEIGHT=81f;

    private int FOREST_LEFT_WALL_X_POSITION = 0;
    private int FOREST_RIGHT_WALL_X_POSITION = 390;
    private final int FACTORY_LEFT_WALL_X_POSITION = 0;
    private final int FACTORY_RIGHT_WALL_X_POSITION = 386;
    private final int FOREST_RIGHT_WALL_HAZARD_X_POSITION = 300;
    private final int FACTORY_RIGHT_WALL_HAZARD_X_POSITION = 292;
    private final int UI_WINDOW_X_POSITION = 50;
    private final int UI_WINDOW_Y_POSITION = 130;
    private boolean bot_left_wall_low_hazard = false;
    private boolean bot_left_wall_high_hazard = false;
    private boolean top_left_wall_low_hazard = false;
    private boolean top_left_wall_high_hazard = false;
    private boolean bot_right_wall_low_hazard = false;
    private boolean bot_right_wall_high_hazard = false;
    private boolean top_right_wall_low_hazard = false;
    private boolean top_right_wall_high_hazard = false;
    private boolean playerArrayParsed;
    private boolean drawInGameSettings = false;
    private boolean player_holding = false;
    long hold_start_time;


    enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;

    Paint paint;
    Paint paint2;
    Paint paint3;
    Paint paint4;
    Paint paint5;


    public GameScreen(Game game) {
        super(game);
        Log.i("GameScreen", "start");

        // Initialize game objects

        this.player1 = new PlayerChar(80f, 576f, 76f, 102f);
        this.enemy1 = new LogEnemy(200f, -50f, ENEMY_1_WIDTH, ENEMY_1_HEIGHT, 1);
        this.enemy1added = false;
        this.randomGenerator = new Random();
        this.enemy_list = new ArrayList<falling_enemy>(16);
        this.checkForOverlap = new OverlapTester();
        this.drawPlayer = true;
        this.previousSpawnTime = new long[1];
        this.passes = 0;
        this.currentScore = 0;
        this.newHighScore = false;
        this.highScore = Settings.getHighScore();
        this.top_backg_y_pos = -1248; // FOR LEVEL 1 AND LEVEL 2
        this.bot_backg_y_pos = -224;
        this.top_walls_y_pos = -812;
        this.bot_walls_y_pos = -6;
        this.bot_forestbgtree_y_pos = -195;
        this.top_forestbgtree_y_pos = -1190;
        this.fpscounter = 0;
        this.delay_time = System.currentTimeMillis();
        this.cam_scroll1 = 0;
        this.cam_scroll2 = 0;
        this.enemySpawnTimer = new SpawnTimer();
        this.enemySpawnEventArray = new ArrayList<SpawnEvent>();
        this.enemyArrayParsed = false;
        this.tile_x_positions = new ArrayList(10);
        this.tile_y_positions = new ArrayList(10);
        this.tileArrayRemakeNeeded = false;
        this.first_tileArrayRemake = true;
        this.forest_tree_1_y_pos = -1189;
        this.forest_tree_2_y_pos = -689;
        this.forest_tree_3_y_pos = -185;
        this.forest_tree_4_y_pos = 315;
        this.forest_branch_reversed_x_pos = 175;
        this.forest_branch_normal_x_pos = 305;
        this.forest_branch_1_y_pos = -740;
        this.forest_branch_2_y_pos = -292;
        this.forest_branch_3_y_pos = 60;
        this.forest_branch_4_y_pos = 508;
        this.walls_blitted = 2;
        this.wallHazardHandler = new WallHazardHandler();
        this.playerArrayParsed = false;
        this.hazardBoundsArray = new ArrayList<>();
        this.jump_type = 0;
        this.hold_start_time = 0;
        this.player_holding = false;


        this.timerHandler = new Handler(Looper.getMainLooper());
        this.timerRunnable = new Runnable() {

            @Override
            public void run() {
                Log.i("GameScreen", "ready to spawn");
                if (enemy_list.size() < 16) {

                    previousSpawnTime[0] = System.currentTimeMillis();
                    
                    SpawnEvent current_spawn = enemySpawnEventArray.get(enemy_count);
                    Log.i("GameScreen", "Spawning enemy number " +
                            String.valueOf(current_spawn.enemy_type));
                    
                    if (current_spawn.enemy_type == 1) {
                        Log.i("TESTING!", String.valueOf(current_spawn.enemy_x_location));
                        LogEnemy new_enemy = new LogEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_1_WIDTH, ENEMY_1_HEIGHT, 1);
                        enemy_list.add(new_enemy);
/*                    } else if (current_spawn.enemy_type == 2) {
                        BranchEnemy new_enemy = new BranchEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_2_WIDTH, ENEMY_2_HEIGHT, 2);
                        enemy_list.add(new_enemy);*/
                    } else if (current_spawn.enemy_type == 3) {
                        AppleEnemy new_enemy = new AppleEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_3_WIDTH, ENEMY_3_HEIGHT, 3);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 5) {
                        MonkeyEnemy new_enemy = new MonkeyEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_4_WIDTH, ENEMY_4_HEIGHT, 5);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 4) {
                        BirdEnemy new_enemy = new BirdEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_5_WIDTH, ENEMY_5_HEIGHT, 4);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 6) {
                        CrateEnemy new_enemy = new CrateEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_6_WIDTH, ENEMY_6_HEIGHT, 6);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 7) {
                        WheelEnemy new_enemy = new WheelEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_7_RADIUS*2, ENEMY_7_RADIUS*2, 7);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 8) {
                        HammerEnemy new_enemy = new HammerEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_8_WIDTH, ENEMY_8_HEIGHT, 8);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 9) {
                        RobotHeadEnemy new_enemy = new RobotHeadEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_9_WIDTH, ENEMY_9_HEIGHT, 9);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 10) {
                        DroneEnemy new_enemy = new DroneEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_10_WIDTH, ENEMY_10_HEIGHT, 10);
                        enemy_list.add(new_enemy);
                    }
                    enemy_count += 1;
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

        paint5 = new Paint();
        paint5.setTextSize(30);
        paint5.setTextAlign(Paint.Align.CENTER);
        paint5.setAntiAlias(true);
        paint5.setColor(Color.RED);

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
        Log.i("FPSCOUNTER", String.valueOf(((System.currentTimeMillis() - this.fpscounter)*(1.000/1.000))*100000));
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


            if (!this.enemyArrayParsed) {
                this.enemySpawnTimer.createEnemyArray();
                this.enemySpawnEventArray = enemySpawnTimer.parseEnemyArray();
                this.enemyArrayParsed = true;
                Log.i("GameScreen", "enemySpawnEventArray parsed");
                Log.i("GameScreenC", String.valueOf(this.hold_start_time));
            }

/*            if (!this.playerArrayParsed) {
                this.player1.createPlayerRectArrays();
                this.playerArrayParsed = true;
                Log.i("GameScreen", "PlayerRects created");
            }*/



            if (true) {
                state = GameState.Running;
                this.drawPlayer = true;
            }
        }

        private void updateRunning(List<Input.TouchEvent> touchEvents, float deltaTime) {

            this.current_time = System.currentTimeMillis();
            Log.i("GameScreenTIMER", String.valueOf(this.current_time));


            // 1. All touch input is handled here:
            //Log.i("GameScreen", "update1, updateRunning started");
            int touchEventsSize = touchEvents.size();
            for (int currentTouchEventIndex = 0; currentTouchEventIndex < touchEventsSize; currentTouchEventIndex++) {
                Input.TouchEvent currentEvent = (Input.TouchEvent) touchEvents
                        .get(currentTouchEventIndex);

                // Handle TOUCH_DOWN
                if (currentEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                    if (System.currentTimeMillis() - delay_time > 200) {
                        if (!tutorial_time) {
                            if (inBounds(currentEvent, 0, 180, 770, 860)) {
                                Log.i("GameScreen", "TOUCH_DOWN detected in bounds");
                                if (!this.player_holding) {
                                    this.player_holding = true;
                                    this.hold_start_time = System.currentTimeMillis();
                                }
                            } else if (inBounds(currentEvent, 395, 15, 70, 70)) {
                                pause();
                            }
                        }
                    }
                }

                // Handle TOUCH_UP
                if (currentEvent.type == Input.TouchEvent.TOUCH_UP) {
                    // Screen pressed in game bounds
                    if (inBounds(currentEvent, 0, 180, 770, 860)) {
                        if (System.currentTimeMillis() - delay_time > 200) {
                            if (tutorial_time) {
                                tutorial_time = false;
                                game_start_time = System.currentTimeMillis();
                                this.player1.start_movement(0);
                            } else {
                                Log.i("GameScreenC", "TOUCH_UP detected in bounds");
                                if (this.player_holding) {
                                    if ((this.current_time - this.hold_start_time) > (1000 * 1)) {
                                        this.jump_type = 2;
                                    } else if ((this.current_time - this.hold_start_time) > (1000 * 0.5)) {
                                        this.jump_type = 1;
                                    } else {
                                        this.jump_type = 0;
                                    }
                                } else {
                                    this.jump_type = 0;
                                }
                                this.player1.start_movement(this.jump_type);
                                delay_time = 0;
                                this.jump_type = 0;
                                this.player_holding = false;
                                this.hold_start_time = 0;
                                //Log.i("GameScreen", "player movement started");
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
                this.player1.dying = true;
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

            // ADD FIRST ENEMY AT CERTAIN SPOT; KEEP OR REMOVE?
            if (player1.first_jump) {
                if (!enemy1added) {
                    this.enemy_list.add(this.enemy1);
                    Log.i("GameScreen", "first enemy spawned");
                    this.enemy1added = true;
                    this.previousSpawnTime[0] = System.currentTimeMillis();
                }
            }

            // OBSOLETE?
/*            if (this.player1.getY_pos() > 526) {

            }*/

            // 3. Call individual update methods here.
            // This is where all the game updates happen.
            // For example, player1.update();
            this.player1.update_char();
            //ENEMY UPDATES ABOVE WITH ITER


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
                    if (this.enemy_list.get(i).player_jumping == false) {
                        this.enemy_list.get(i).setPlayer_jumping(true);
                    }
                    //this.enemy_list.get(i).update_enemy();
                    //Log.i("TESTING EN1", String.valueOf(this.enemy_list.get(i).getY_pos()));
                } else {
                    if (this.enemy_list.get(i).player_jumping == true) {
                        this.enemy_list.get(i).setPlayer_jumping(false);
                    }
                    //this.enemy_list.get(i).update_enemy();
                    //Log.i("TESTING EN2", String.valueOf(this.enemy_list.get(i).getY_pos()));
                }
                // Speed up blocks when player is too high
                if (this.height_thresh1) {
                    this.enemy_list.get(i).setY_pos(this.enemy_list.get(i).getY_pos() + 2.0f);
                    this.enemy_list.get(i).setY_height_thresh_change(2f);
                    if (this.height_thresh2) {
                        this.enemy_list.get(i).setY_pos(this.enemy_list.get(i).getY_pos() + 4.0f);
                        this.enemy_list.get(i).setY_height_thresh_change(6f);
                    }
                }
                this.enemy_list.get(i).update_enemy();
            }

            //Log.i("CAM", String.valueOf(this.cam_scroll1) + "," + String.valueOf(this.cam_scroll2));
            if (this.cam_scroll1 != 0 && this.cam_scroll2 != 0) {
                this.top_backg_y_pos += ((this.cam_scroll1 + this.cam_scroll2) - 2.0f);
                this.bot_backg_y_pos += ((this.cam_scroll1 + this.cam_scroll2) - 2.0f);
                this.bot_forestbgtree_y_pos += ((this.cam_scroll1 + this.cam_scroll2) - 2.0f);
                this.top_forestbgtree_y_pos += ((this.cam_scroll1 + this.cam_scroll2) - 2.0f);


                //START OBS
/*                for (int i = 0; i < 10; i++) {
                    this.tile_y_positions.set(i, ((int) this.tile_y_positions.get(i) + (((int) this.cam_scroll1 + (int) this.cam_scroll2) - 2)));
                }
                this.forest_tree_1_y_pos += (((int) this.cam_scroll1 + (int) this.cam_scroll2) - 2);
                this.forest_tree_2_y_pos += (((int) this.cam_scroll1 + (int) this.cam_scroll2) - 2);
                this.forest_tree_3_y_pos += (((int) this.cam_scroll1 + (int) this.cam_scroll2) - 2);
                this.forest_tree_4_y_pos += (((int) this.cam_scroll1 + (int) this.cam_scroll2) - 2);

                this.forest_branch_1_y_pos += (((int) this.cam_scroll1 + (int) this.cam_scroll2) - 2);
                this.forest_branch_2_y_pos += (((int) this.cam_scroll1 + (int) this.cam_scroll2) - 2);
                this.forest_branch_3_y_pos += (((int) this.cam_scroll1 + (int) this.cam_scroll2) - 2);
                this.forest_branch_4_y_pos += (((int) this.cam_scroll1 + (int) this.cam_scroll2) - 2);*/
                //END OBS

            }
            //Log.i("TESTING1A", String.valueOf(this.top_backg_y_pos) + "," + String.valueOf(this.bot_backg_y_pos));
            //Log.i("TESTING2A", String.valueOf(this.top_walls_y_pos) + "," + String.valueOf(this.bot_walls_y_pos));

            this.top_walls_y_pos += (this.cam_scroll1 + this.cam_scroll2);
            this.bot_walls_y_pos += (this.cam_scroll1 + this.cam_scroll2);
            for (int i=0; i < this.hazardBoundsArray.size(); i++) {
                this.hazardBoundsArray.get(i).addY_pos((this.cam_scroll1 + this.cam_scroll2));
            }


            if (this.player1.jumped) {
/*                Log.i("TESTING BACKG", (String.valueOf(top_backg_y_pos) + "," + String.valueOf(bot_backg_y_pos)));
                Log.i("TESTING WALLS", (String.valueOf(top_walls_y_pos) + "," + String.valueOf(bot_walls_y_pos)));*/
                // Handle background scrolling as player is jumping
                //Log.i("TESTING1B", String.valueOf(this.top_backg_y_pos) + "," + String.valueOf(this.bot_backg_y_pos));
                //Log.i("TESTING2B", String.valueOf(this.top_walls_y_pos) + "," + String.valueOf(this.bot_walls_y_pos));
                this.top_backg_y_pos += 4.0f;
                this.bot_backg_y_pos += 4.0f;
                this.bot_forestbgtree_y_pos += 4.0f;
                this.top_forestbgtree_y_pos += 4.0f;

/*                for (int i = 0; i < 10; i++) {
                    this.tile_y_positions.set(i, ((int) this.tile_y_positions.get(i) + 4));
                }*/

                //START OBS
/*                this.forest_tree_1_y_pos += 4;
                this.forest_tree_2_y_pos += 4;
                this.forest_tree_3_y_pos += 4;
                this.forest_tree_4_y_pos += 4;

                this.forest_branch_1_y_pos += 4;
                this.forest_branch_2_y_pos += 4;
                this.forest_branch_3_y_pos += 4;
                this.forest_branch_4_y_pos += 4;*/
                // Handle walls scrolling as player is jumping
                this.top_walls_y_pos += 7.0f;
                this.bot_walls_y_pos += 7.0f;
                for (int i=0; i < this.hazardBoundsArray.size(); i++) {
                    this.hazardBoundsArray.get(i).addY_pos(7f);
                }
                //END OBS
            }

            // Redraw background and walls once they reach the bottom
/*            if ((int) this.tile_y_positions.get(2) >= 256){
                this.arrayRemakeNeeded = true;
        }*/

/*            if (this.tileArrayRemakeNeeded || this.first_tileArrayRemake) {
                if (this.first_tileArrayRemake) {
                    this.tile_x_positions.add(0, 0);
                    this.tile_x_positions.add(1, 256);
                    this.tile_x_positions.add(2, 0);
                    this.tile_x_positions.add(3, 256);
                    this.tile_x_positions.add(4, 0);
                    this.tile_x_positions.add(5, 256);
                    this.tile_x_positions.add(6, 0);
                    this.tile_x_positions.add(7, 256);
                    this.tile_x_positions.add(8, 0);
                    this.tile_x_positions.add(9, 256);

                    this.tile_y_positions.add(0, -256);
                    this.tile_y_positions.add(1, -256);
                    this.tile_y_positions.add(2, 0);
                    this.tile_y_positions.add(3, 0);
                    this.tile_y_positions.add(4, 256);
                    this.tile_y_positions.add(5, 256);
                    this.tile_y_positions.add(6, 512);
                    this.tile_y_positions.add(7, 512);
                    this.tile_y_positions.add(8, 768);
                    this.tile_y_positions.add(9, 768);

                    this.first_tileArrayRemake = false;
                } else {
                    this.tile_x_positions.set(0, 0);
                    this.tile_x_positions.set(1, 256);
                    this.tile_x_positions.set(2, 0);
                    this.tile_x_positions.set(3, 256);
                    this.tile_x_positions.set(4, 0);
                    this.tile_x_positions.set(5, 256);
                    this.tile_x_positions.set(6, 0);
                    this.tile_x_positions.set(7, 256);
                    this.tile_x_positions.set(8, 0);
                    this.tile_x_positions.set(9, 256);

                    this.tile_y_positions.set(0, -256);
                    this.tile_y_positions.set(1, -256);
                    this.tile_y_positions.set(2, 0);
                    this.tile_y_positions.set(3, 0);
                    this.tile_y_positions.set(4, 256);
                    this.tile_y_positions.set(5, 256);
                    this.tile_y_positions.set(6, 512);
                    this.tile_y_positions.set(7, 512);
                    this.tile_y_positions.set(8, 768);
                    this.tile_y_positions.set(9, 768);

                    this.tileArrayRemakeNeeded = false;
                }
            }

            if ((int) this.tile_y_positions.get(2) >= 256) {
                this.tileArrayRemakeNeeded = true;
            }*/

/*            if (this.forest_tree_4_y_pos >= 817) {
                this.forest_tree_4_y_pos = -1191;
            } else if (this.forest_tree_3_y_pos >= 817) {
                this.forest_tree_3_y_pos = -1191;
            } else if (this.forest_tree_2_y_pos >= 817) {
                this.forest_tree_2_y_pos = -1191;
            } else if (this.forest_tree_1_y_pos >= 817) {
                this.forest_tree_1_y_pos = -1191;
            }

            if (this.forest_branch_4_y_pos >= 1308) {
                this.forest_branch_4_y_pos = -292;
            } else if (this.forest_branch_3_y_pos >= 860) {
                this.forest_branch_3_y_pos = -740;
            } else if (this.forest_branch_2_y_pos >= 1308) {
                this.forest_branch_2_y_pos = -292;
            } else if (this.forest_branch_1_y_pos >= 860) {
                this.forest_branch_1_y_pos = -740;
            }*/


            if (this.bot_backg_y_pos > 800) {
                //Log.i("GameScreen", "bot_backg"+String.valueOf(this.bot_backg_y_pos));
                //Log.i("GameScreen", "top_backg"+String.valueOf(this.top_backg_y_pos));
                this.bot_backg_y_pos -= (1024*2);
                //Log.i("GameScreen", "bot_backg"+String.valueOf(this.bot_backg_y_pos));
                //Log.i("GameScreen", "top_backg"+String.valueOf(this.top_backg_y_pos));
                //this.top_backg_y_pos = -224;
            } else if (this.top_backg_y_pos > 800) {
                //Log.i("GameScreen", "bot_backg"+String.valueOf(this.bot_backg_y_pos));
                //Log.i("GameScreen", "top_backg"+String.valueOf(this.top_backg_y_pos));
                this.top_backg_y_pos -= (1024*2);
                //Log.i("GameScreen", "bot_backg"+String.valueOf(this.bot_backg_y_pos));
                //Log.i("GameScreen", "top_backg"+String.valueOf(this.top_backg_y_pos));
                //this.bot_backg_y_pos = 0;
            }

            if (this.bot_forestbgtree_y_pos > 800) {
                //Log.i("GameScreen", "bot_tree"+String.valueOf(this.bot_forestbgtree_y_pos));
                //Log.i("GameScreen", "top_tree"+String.valueOf(this.top_forestbgtree_y_pos));
                this.bot_forestbgtree_y_pos -= (995*2);
                //Log.i("GameScreen", "bot_tree"+String.valueOf(this.bot_forestbgtree_y_pos));
                //Log.i("GameScreen", "top_tree"+String.valueOf(this.top_forestbgtree_y_pos));
                //update top_forestbgtree?
            } else if (this.top_forestbgtree_y_pos > 800) {
                //Log.i("GameScreen", "bot_tree"+String.valueOf(this.bot_forestbgtree_y_pos));
                //Log.i("GameScreen", "top_tree"+String.valueOf(this.top_forestbgtree_y_pos));
                this.top_forestbgtree_y_pos -= (995*2);
                //Log.i("GameScreen", "bot_tree"+String.valueOf(this.bot_forestbgtree_y_pos));
                //Log.i("GameScreen", "top_tree"+String.valueOf(this.top_forestbgtree_y_pos));
                //update bot_forestbgtree?
            }

            // CHECK IF WALL REDRAW IS NEEDED, if so check for hazards present
            if (this.bot_walls_y_pos > 800) {
                Log.i("GameScreen", "bot_walls"+String.valueOf(this.bot_walls_y_pos));
                Log.i("GameScreen", "top_walls"+String.valueOf(this.top_walls_y_pos));
                this.walls_blitted += 1;

                this.bot_left_wall_low_hazard = false;
                this.bot_left_wall_high_hazard = false;
                if (this.wallHazardHandler.checkForLeftLowHazard(this.walls_blitted)) {
                    this.bot_left_wall_low_hazard = true;
                } else if (this.wallHazardHandler.checkForLeftHighHazard(this.walls_blitted)) {
                    this.bot_left_wall_high_hazard = true;
                }

                this.bot_right_wall_low_hazard = false;
                this.bot_right_wall_high_hazard = false;
                if (this.wallHazardHandler.checkForRightLowHazard(this.walls_blitted)) {
                    this.bot_right_wall_low_hazard = true;
                } else if (this.wallHazardHandler.checkForRightHighHazard(this.walls_blitted)) {
                    this.bot_right_wall_high_hazard = true;
                }
                
                if (this.current_level == 1) {
                    this.bot_walls_y_pos -= (806*2);
                    if (this.bot_left_wall_low_hazard) {
                        WallHazard hazardRect = new WallHazard(89, (this.bot_walls_y_pos + 397), 60, 52);
                        this.hazardBoundsArray.add(hazardRect);
                    } else if (this.bot_left_wall_high_hazard) {
                        WallHazard hazardRect = new WallHazard(89, (this.bot_walls_y_pos + 4), 60, 52);
                        this.hazardBoundsArray.add(hazardRect);
                    }
                    if (this.bot_right_wall_low_hazard) {
                        WallHazard hazardRect = new WallHazard(331, (this.bot_walls_y_pos + 397), 60, 52);
                        this.hazardBoundsArray.add(hazardRect);
                    } else if (this.bot_right_wall_high_hazard) {
                        WallHazard hazardRect = new WallHazard(331, (this.bot_walls_y_pos + 4), 60, 52);
                        this.hazardBoundsArray.add(hazardRect);
                    }
                } else {
                    this.bot_walls_y_pos -= (1099*2);
                    if (this.bot_left_wall_low_hazard) {
                        WallHazard hazardRect = new WallHazard(59, (this.bot_walls_y_pos + 907), 35, 121);
                        this.hazardBoundsArray.add(hazardRect);
                    } else if (this.bot_left_wall_high_hazard) {
                        WallHazard hazardRect = new WallHazard(59, (this.bot_walls_y_pos + 279), 35, 121);
                        this.hazardBoundsArray.add(hazardRect);
                    }
                    if (this.bot_right_wall_low_hazard) {
                        WallHazard hazardRect = new WallHazard(386, (this.bot_walls_y_pos + 907), 35, 121);
                        this.hazardBoundsArray.add(hazardRect);
                    } else if (this.bot_right_wall_high_hazard) {
                        WallHazard hazardRect = new WallHazard(386, (this.bot_walls_y_pos + 279), 35, 121);
                        this.hazardBoundsArray.add(hazardRect);
                    }
                }

                //Log.i("GameScreen", "bot_walls"+String.valueOf(this.bot_walls_y_pos));
                //Log.i("GameScreen", "top_walls"+String.valueOf(this.top_walls_y_pos));
                //this.top_walls_y_pos = -6;
            } else if (this.top_walls_y_pos > 800) {
                //Log.i("GameScreen", "bot_walls"+String.valueOf(this.bot_walls_y_pos));
                //Log.i("GameScreen", "top_walls"+String.valueOf(this.top_walls_y_pos));
                this.walls_blitted += 1;
                this.top_left_wall_low_hazard = false;
                this.top_left_wall_high_hazard = false;
                if (this.wallHazardHandler.checkForLeftLowHazard(this.walls_blitted)) {
                    this.top_left_wall_low_hazard = true;
                } else if (this.wallHazardHandler.checkForLeftHighHazard(this.walls_blitted)) {
                    this.top_left_wall_high_hazard = true;
                }

                this.top_right_wall_low_hazard = false;
                this.top_right_wall_high_hazard = false;
                if (this.wallHazardHandler.checkForRightLowHazard(this.walls_blitted)) {
                    this.top_right_wall_low_hazard = true;
                } else if (this.wallHazardHandler.checkForRightHighHazard(this.walls_blitted)) {
                    this.top_right_wall_high_hazard = true;
                }

                if (this.current_level == 1) {
                    this.top_walls_y_pos -= (806*2);
                    if (this.top_left_wall_low_hazard) {
                        WallHazard hazardRect = new WallHazard(89, (this.top_walls_y_pos + 397), 60, 52);
                        this.hazardBoundsArray.add(hazardRect);
                    } else if (this.top_left_wall_high_hazard) {
                        WallHazard hazardRect = new WallHazard(89, (this.top_walls_y_pos + 4), 60, 52);
                        this.hazardBoundsArray.add(hazardRect);
                    }
                    if (this.top_right_wall_low_hazard) {
                        WallHazard hazardRect = new WallHazard(331, (this.top_walls_y_pos + 397), 60, 52);
                        this.hazardBoundsArray.add(hazardRect);
                    } else if (this.top_right_wall_high_hazard) {
                        WallHazard hazardRect = new WallHazard(331, (this.top_walls_y_pos + 4), 60, 52);
                        this.hazardBoundsArray.add(hazardRect);
                    }
                } else {
                    this.top_walls_y_pos -= (1099*2);
                    if (this.top_left_wall_low_hazard) {
                        WallHazard hazardRect = new WallHazard(59, (this.top_walls_y_pos + 907), 35, 121);
                        this.hazardBoundsArray.add(hazardRect);
                    } else if (this.top_left_wall_high_hazard) {
                        WallHazard hazardRect = new WallHazard(59, (this.top_walls_y_pos + 279), 35, 121);
                        this.hazardBoundsArray.add(hazardRect);
                    }
                    if (this.top_right_wall_low_hazard) {
                        WallHazard hazardRect = new WallHazard(386, (this.top_walls_y_pos + 907), 35, 121);
                        this.hazardBoundsArray.add(hazardRect);
                    } else if (this.top_right_wall_high_hazard) {
                        WallHazard hazardRect = new WallHazard(386, (this.top_walls_y_pos + 279), 35, 121);
                        this.hazardBoundsArray.add(hazardRect);
                    }
                }
                //Log.i("GameScreen", "bot_walls"+String.valueOf(this.bot_walls_y_pos));
                //Log.i("GameScreen", "top_walls"+String.valueOf(this.top_walls_y_pos));
                //this.bot_walls_y_pos = 0;
            }


            for (int i = 0; i < this.enemy_list.size(); i++) {
                Log.i("GameScreen", "Starting overlap checks");
                if (!this.player1.dying) {// == false) {//FIX FOR CHAR DEATh
                    Log.i("GameScreen", "Char still alive");
                    if (this.enemy_list.get(i).getEnemy_num() == 7) {
                        Log.i("GameScreen", "Checking circle enemy");
                        //Log.i("GameScreen", "Checking Enemy 7");
                        //Log.i("GS", String.valueOf(this.enemy_list.get(i).bounds.x));
                        //Log.i("GS", String.valueOf(this.enemy_list.get(i).bounds.y));
                        for (int z=0; z < this.player1.getCurrentSpriteBounds().size(); z++) {
                            Log.i("GameScreen", "Checking for Overlap1");
                            if (this.checkForOverlap.overlapCircleRectangle((Circle) this.enemy_list.get(i).bounds, this.player1.getCurrentSpriteBounds().get(z))) {
                                this.player1.dying = true;
                                Log.i("OVERLAP FOUND", String.valueOf(this.player1.getX_pos()));
                                // CHANGE TO DIFF SOUND EFFECT
                                if (Settings.soundEnabled) {
                                    death_sound.play(1f);
                                }
                                break;
                            }
                        }
                    } else {
                        Log.i("GameScreen", "Checking non-circle enemies");
                        for (int z = 0; z < this.enemy_list.get(i).bounds_tsil.size(); z++) {
                            Log.i("GameScreen", "Going through each enemy bounds rect");
                            for (int y=0; y < this.player1.getCurrentSpriteBounds().size(); y++) {

                    /*            Log.i("GameScreen", "Checking for Overlap2, every player bounds rect");

                                Log.i("GameScreenPC1", String.valueOf(this.player1.x_pos+","+this.player1.y_pos));

                                Log.i("GameScreenPC2", String.valueOf(this.player1.getCurrentSpriteBounds().get(y).getLowerLeft().getX()+","+
                                        this.player1.getCurrentSpriteBounds().get(y).getLowerLeft().getY()+"..."+
                                this.player1.getCurrentSpriteBounds().get(y).width)+","+
                                this.player1.getCurrentSpriteBounds().get(y).height);*/

                                Rectangle curr_rect = (Rectangle) this.enemy_list.get(i).bounds_tsil.get(z);
                                Log.i("GameScreenEnemy", String.valueOf(curr_rect.getLowerLeft().getX()+","+curr_rect.getLowerLeft().getY()+"..."+curr_rect.width+","+curr_rect.height));
                                if (this.checkForOverlap.overlapRectangles(this.player1.getCurrentSpriteBounds().get(y), (Rectangle) this.enemy_list.get(i).bounds_tsil.get(z))) {
                                    this.player1.dying = true;
                                    Log.i("OVERLAP FOUND", String.valueOf(this.player1.getX_pos()));
                                    // CHANGE TO DIFF SOUND EFFECT
                                    if (Settings.soundEnabled) {
                                        death_sound.play(1f);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            for (int i=0; i < this.hazardBoundsArray.size(); i++) {
                if (!this.player1.dying) {// == false) {
                    for (int z=0; z < this.player1.getCurrentSpriteBounds().size(); z++) {
                        Log.i("GameScreen", "Checking for Overlap3");
                        Log.i("GameScreenPC1", String.valueOf(this.player1.x_pos+","+this.player1.y_pos));
                        Log.i("GameScreenPC2", String.valueOf(this.player1.getCurrentSpriteBounds().get(z).getLowerLeft().getX()+","+
                        this.player1.getCurrentSpriteBounds().get(z).getLowerLeft().getY()+"..."+
                        this.player1.getCurrentSpriteBounds().get(z).width+","+this.player1.getCurrentSpriteBounds().get(z).height));
                        Log.i("GameScreenHazard", String.valueOf(this.hazardBoundsArray.get(i).getLowerLeft().getX()+","+
                        this.hazardBoundsArray.get(i).getLowerLeft().getY()+"..."+this.hazardBoundsArray.get(i).width+","+
                        this.hazardBoundsArray.get(i).height));
                        if (this.checkForOverlap.overlapRectangles(this.player1.getCurrentSpriteBounds().get(z), this.hazardBoundsArray.get(i))) {
                            this.player1.dying = true;
                            Log.i("OVERLAP FOUND", String.valueOf(this.player1.getX_pos()));
                            // CHANGE TO DIFF SOUND EFFECT
                            if (Settings.soundEnabled) {
                                death_sound.play(1f);
                            }
                            break;
                        }
                    }
                }
            }

            //Log.i("GameScreen", "update2, char and enemy");

            if (player1.first_jump) {
                try {
                    if ((current_time - game_start_time) >
                            (enemySpawnEventArray.get(enemy_count).enemy_spawn_time) * 1000) {
                        this.timerRunnable.run();
                    }
                } catch (IndexOutOfBoundsException e) {
                    Log.i("GameScreen", "NO MORE ENEMIES");
                }
                //Log.i("TESTING", String.valueOf(System.currentTimeMillis()));
            }

            // Clean up enemies off screen
            for (int i = 0; i < this.enemy_list.size(); i++) {
                if ((this.enemy_list.get(i).getY_pos() > 820)) {
                    this.enemy_list.remove(i);
                }
            }
            // Clean up hazards off screen
            for(int i=0; i < this.hazardBoundsArray.size(); i++) {
                if (this.hazardBoundsArray.get(i).getY_pos() > 820) {
                    this.hazardBoundsArray.remove(i);
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
            //if (!drawInGameSettings) { //UPDATE WHEN INGAMESETTINGS SCREEN READY
                // If Resume Button Pressed
                if (inBounds(currentEvent, UI_WINDOW_X_POSITION + 33, UI_WINDOW_Y_POSITION + 46, 293, 70)) {
                    resume();
                } else if (inBounds(currentEvent, UI_WINDOW_X_POSITION + 33, UI_WINDOW_Y_POSITION + 146, 293, 70)) {
                    drawInGameSettings = true;
                } else if (inBounds(currentEvent, UI_WINDOW_X_POSITION + 33, UI_WINDOW_Y_POSITION + 244, 323, 70)) {
                    nullify();
                    goToMenu();
                }
            //}
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
            //START OBS
/*            g.drawImage(g.newImage("background_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 0, this.top_backg_y_pos);
            g.drawImage(g.newImage("background_scrolling_image_lowres.png", Graphics.ImageFormat.RGB565), 0, this.bot_backg_y_pos);*/
/*            for (int i=0; i < 10; i++) {
                g.drawImage(g.newImage("SceneOne_Tile.png", Graphics.ImageFormat.RGB565), (int) this.tile_x_positions.get(i), (int) this.tile_y_positions.get(i));
            }*/
            //END OBS
            g.drawImage(g.newImage("Forest_Background_noshrubnotree.png", Graphics.ImageFormat.RGB565), 0, this.top_backg_y_pos);
            g.drawImage(g.newImage("Forest_Background_noshrubnotree.png", Graphics.ImageFormat.RGB565), 0, this.bot_backg_y_pos);

/*            g.drawImage(g.newImage("Forest_Background_noshrubnotree.png", Graphics.ImageFormat.RGB565), 0, this.top_backg_y_pos);
            g.drawImage(g.newImage("Forest_Background_noshrubnotree.png", Graphics.ImageFormat.RGB565), 0, this.bot_backg_y_pos);*/

            g.drawImage(g.newImage("Forest_TreeandBranches.png", Graphics.ImageFormat.RGB565), 170, this.top_forestbgtree_y_pos);
            g.drawImage(g.newImage("Forest_TreeandBranches.png", Graphics.ImageFormat.RGB565), 170, this.bot_forestbgtree_y_pos);

            //START OBS
/*            g.drawImage(g.newImage("SceneOne_MidGround_BranchReversed.png", Graphics.ImageFormat.RGB565), this.forest_branch_reversed_x_pos, this.forest_branch_1_y_pos);
            g.drawImage(g.newImage("SceneOne_MidGround_BranchNormal.png", Graphics.ImageFormat.RGB565), this.forest_branch_normal_x_pos, this.forest_branch_2_y_pos);
            g.drawImage(g.newImage("SceneOne_MidGround_BranchReversed.png", Graphics.ImageFormat.RGB565), this.forest_branch_reversed_x_pos, this.forest_branch_3_y_pos);
            g.drawImage(g.newImage("SceneOne_MidGround_BranchNormal.png", Graphics.ImageFormat.RGB565), this.forest_branch_normal_x_pos, this.forest_branch_4_y_pos);*/

/*            g.drawImage(g.newImage("SceneOne_MidGround_LogReversed.png", Graphics.ImageFormat.RGB565), 251, this.forest_tree_1_y_pos);
            g.drawImage(g.newImage("SceneOne_MidGround_LogNormal.png", Graphics.ImageFormat.RGB565), 243, this.forest_tree_2_y_pos);
            g.drawImage(g.newImage("SceneOne_MidGround_LogReversed.png", Graphics.ImageFormat.RGB565), 251, this.forest_tree_3_y_pos);
            g.drawImage(g.newImage("SceneOne_MidGround_LogNormal.png", Graphics.ImageFormat.RGB565), 243, this.forest_tree_4_y_pos);*/
            //END OBS

            //Log.i("TESTING1", String.valueOf(this.top_backg_y_pos) + "," + String.valueOf(this.bot_backg_y_pos));
                    //Log.i("TESTING2", String.valueOf(this.top_walls_y_pos) + "," + String.valueOf(this.bot_walls_y_pos));

            // Draw two sets of left walls
            if (this.top_left_wall_low_hazard) {
                g.drawImage(g.newImage("Forest_Left_Wallhighres-lowhazard,180px.png", Graphics.ImageFormat.RGB565), FOREST_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            } else if (this.top_left_wall_high_hazard) {
                g.drawImage(g.newImage("Forest_Left_Wallhighres-highhazard,180px.png", Graphics.ImageFormat.RGB565), FOREST_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            } else {
                g.drawImage(g.newImage("Forest_Left_Wallhighres-90px.png", Graphics.ImageFormat.RGB565), FOREST_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            }
            if (this.bot_left_wall_low_hazard) {
                g.drawImage(g.newImage("Forest_Left_Wallhighres-lowhazard,180px.png", Graphics.ImageFormat.RGB565), FOREST_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            } else if (this.bot_left_wall_high_hazard) {
                g.drawImage(g.newImage("Forest_Left_Wallhighres-highhazard,180px.png", Graphics.ImageFormat.RGB565), FOREST_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            } else {
                g.drawImage(g.newImage("Forest_Left_Wallhighres-90px.png", Graphics.ImageFormat.RGB565), FOREST_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            }
            // Draw two sets of right walls
            if (this.top_right_wall_low_hazard) {
                g.drawImage(g.newImage("Forest_Right_Wallhighres-lowhazard,180px.png", Graphics.ImageFormat.RGB565), FOREST_RIGHT_WALL_HAZARD_X_POSITION, this.top_walls_y_pos);
            } else if (this.top_right_wall_high_hazard) {
                g.drawImage(g.newImage("Forest_Right_Wallhighres-highhazard,180px.png", Graphics.ImageFormat.RGB565), FOREST_RIGHT_WALL_HAZARD_X_POSITION, this.top_walls_y_pos);
            } else {
                g.drawImage(g.newImage("Forest_Right_Wallhighres-90px.png", Graphics.ImageFormat.RGB565), FOREST_RIGHT_WALL_X_POSITION, this.top_walls_y_pos);
            }
            if (this.bot_right_wall_low_hazard) {
                g.drawImage(g.newImage("Forest_Right_Wallhighres-lowhazard,180px.png", Graphics.ImageFormat.RGB565), FOREST_RIGHT_WALL_HAZARD_X_POSITION, this.bot_walls_y_pos);
            } else if (this.bot_right_wall_high_hazard) {
                g.drawImage(g.newImage("Forest_Right_Wallhighres-highhazard,180px.png", Graphics.ImageFormat.RGB565), FOREST_RIGHT_WALL_HAZARD_X_POSITION, this.bot_walls_y_pos);
            } else {
                g.drawImage(g.newImage("Forest_Right_Wallhighres-90px.png", Graphics.ImageFormat.RGB565), FOREST_RIGHT_WALL_X_POSITION, this.bot_walls_y_pos);
            }

/*            g.drawImage(g.newImage("Left_Wall-90px.png", Graphics.ImageFormat.RGB565), FOREST_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            g.drawImage(g.newImage("Left_Wall-90px.png", Graphics.ImageFormat.RGB565), FOREST_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            // Draw two sets of right walls
            g.drawImage(g.newImage("Right_Wall-90px.png", Graphics.ImageFormat.RGB565), FOREST_RIGHT_WALL_X_POSITION, this.top_walls_y_pos);
            g.drawImage(g.newImage("Right_Wall-90px.png", Graphics.ImageFormat.RGB565), FOREST_RIGHT_WALL_X_POSITION, this.bot_walls_y_pos);*/
        } else if (current_level == 2) {  // REPLACE IMAGES ONCE ASSETS OBTAINED
            // Now game elements:
            // Draw two sets of scrolling backgrounds
            g.drawImage(g.newImage("RobotFactoryBackgroundhighres.png", Graphics.ImageFormat.RGB565), 0, this.top_backg_y_pos);
            g.drawImage(g.newImage("RobotFactoryBackgroundhighres.png", Graphics.ImageFormat.RGB565), 0, this.bot_backg_y_pos);
            //Log.i("TESTING1", String.valueOf(this.top_backg_y_pos) + "," + String.valueOf(this.bot_backg_y_pos));
            //Log.i("TESTING2", String.valueOf(this.top_walls_y_pos) + "," + String.valueOf(this.bot_walls_y_pos));

            // Draw two sets of left walls
            if (this.top_left_wall_low_hazard) {
                g.drawImage(g.newImage("FactoryLeft_Wallhighres-lowhazard,94px.png", Graphics.ImageFormat.RGB565), FACTORY_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            } else if (this.top_left_wall_high_hazard) {
                g.drawImage(g.newImage("FactoryLeft_Wallhighres-highhazard,94px.png", Graphics.ImageFormat.RGB565), FACTORY_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            } else {
                g.drawImage(g.newImage("FactoryLeft_Wallhighres-94px.png", Graphics.ImageFormat.RGB565), FACTORY_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            }
            if (this.bot_left_wall_low_hazard) {
                g.drawImage(g.newImage("FactoryLeft_Wallhighres-lowhazard,94px.png", Graphics.ImageFormat.RGB565), FACTORY_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            } else if (this.bot_left_wall_high_hazard) {
                g.drawImage(g.newImage("FactoryLeft_Wallhighres-highhazard,94px.png", Graphics.ImageFormat.RGB565), FACTORY_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            } else {
                g.drawImage(g.newImage("FactoryLeft_Wallhighres-94px.png", Graphics.ImageFormat.RGB565), FACTORY_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            }
            // Draw two sets of right walls
            if (this.top_right_wall_low_hazard) {
                g.drawImage(g.newImage("FactoryRight_Wallhighres-lowhazard,94px.png", Graphics.ImageFormat.RGB565), FACTORY_RIGHT_WALL_X_POSITION, this.top_walls_y_pos);
            } else if (this.top_right_wall_high_hazard) {
                g.drawImage(g.newImage("FactoryRight_Wallhighres-highhazard,94px.png", Graphics.ImageFormat.RGB565), FACTORY_RIGHT_WALL_X_POSITION, this.top_walls_y_pos);
            } else {
                g.drawImage(g.newImage("FactoryRight_Wallhighres-94px.png", Graphics.ImageFormat.RGB565), FACTORY_RIGHT_WALL_X_POSITION, this.top_walls_y_pos);
            }
            if (this.bot_right_wall_low_hazard) {
                g.drawImage(g.newImage("FactoryRight_Wallhighres-lowhazard,94px.png", Graphics.ImageFormat.RGB565), FACTORY_RIGHT_WALL_X_POSITION, this.bot_walls_y_pos);
            } else if (this.bot_right_wall_high_hazard) {
                g.drawImage(g.newImage("FactoryRight_Wallhighres-highhazard,94px.png", Graphics.ImageFormat.RGB565), FACTORY_RIGHT_WALL_X_POSITION, this.bot_walls_y_pos);
            } else {
                g.drawImage(g.newImage("FactoryRight_Wallhighres-94px.png", Graphics.ImageFormat.RGB565), FACTORY_RIGHT_WALL_X_POSITION, this.bot_walls_y_pos);
            }

        }
        Log.i("GameScreenWB", String.valueOf(this.walls_blitted));

        for (int i = 0; i < this.enemy_list.size(); i++) {
            falling_enemy current_enemy_draw = (falling_enemy) this.enemy_list.get(i);
            if (current_enemy_draw.getEnemy_num() == 1) {
                g.drawImage(g.newImage(this.enemy_list.get(i).getImageName(), Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
/*            } else if (current_enemy_draw.getEnemy_num() == 2) {
                g.drawImage(g.newImage("enemy_image2.png", Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());*/
            } else if (current_enemy_draw.getEnemy_num() == 3) {
                g.drawImage(g.newImage(this.enemy_list.get(i).getImageName(), Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            } else if (current_enemy_draw.getEnemy_num() == 4) {
                g.drawImage(g.newImage(this.enemy_list.get(i).getImageName(), Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            } else if (current_enemy_draw.getEnemy_num() == 5) {
                g.drawImage(g.newImage(this.enemy_list.get(i).getImageName(), Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            } else if (current_enemy_draw.getEnemy_num() == 6) {
                g.drawImage(g.newImage(this.enemy_list.get(i).getImageName(), Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            } else if (current_enemy_draw.getEnemy_num() == 7) {
                g.drawImage(g.newImage(this.enemy_list.get(i).getImageName(), Graphics.ImageFormat.RGB565), (int) (this.enemy_list.get(i).getX_pos() - 40f), (int) (this.enemy_list.get(i).getY_pos() - 40f));
            } else if (current_enemy_draw.getEnemy_num() == 8) {
                g.drawImage(g.newImage(this.enemy_list.get(i).getImageName(), Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            } else if (current_enemy_draw.getEnemy_num() == 9) {
                g.drawImage(g.newImage(this.enemy_list.get(i).getImageName(), Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            } else {
                g.drawImage(g.newImage(this.enemy_list.get(i).getImageName(), Graphics.ImageFormat.RGB565), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            }
        }
        g.drawImage(g.newImage(this.player1.getSpriteName(), Graphics.ImageFormat.RGB565), (int) this.player1.getX_pos(), (int) this.player1.getY_pos());

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
        if (Settings.soundEnabled) {
            this.game_music.seekBegin();
        }
        this.previousSpawnTime[0] += time_paused;
        this.game_start_time += time_paused;
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
        //UPDATE TO PROPERLY CLEAN UP VARIABLES
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

        if (this.player_holding) {
            if ((this.current_time - this.hold_start_time) > (1000 * 1)) {
                g.drawString((String.valueOf(2)), 40, 700, paint3);
            } else if ((this.current_time - this.hold_start_time) > (1000 * 0.5)) {
                g.drawString((String.valueOf(1)), 40, 700, paint3);
            } else {
                g.drawString((String.valueOf(0)), 40, 700, paint3);
            }
        }



        if (!tutorial_time) {
            g.drawImage(g.newImage("Pause.png", Graphics.ImageFormat.RGB565), 410, 20);
        }

        Log.i("CURRENT TIME", String.valueOf(current_time));
        Log.i("START TIME", String.valueOf(game_start_time));
        Log.i("TIME DIFF", String.valueOf(current_time - game_start_time));

        if (((current_time - game_start_time) > (120*1000)) && transition_incomplete && ! tutorial_time) {
            Log.i("STARTING", String.valueOf(this.opacity_num));
            g.drawARGB(this.opacity_num, 255, 255, 255);
            if (this.opacity_num < 255 && !this.reached_255_opacity) {
                this.opacity_num += 25;
            } else if (this.opacity_num == 255) {
                this.opacity_num -= 25;
                this.reached_255_opacity = true;
                this.current_level = 2;
                this.bot_backg_y_pos = -224;
                this.top_backg_y_pos = -1248;
                this.bot_walls_y_pos = -299;
                this.top_walls_y_pos = -1398;
                this.player1.player_score += 15f;
            } else if (this.opacity_num < 255 && this.reached_255_opacity) {
                this.opacity_num -= 25;
            }
            if (this.opacity_num < 0) {
                transition_incomplete = false;
                Log.i("ENDING", "TRANSITION");
            }
        }

        if (tutorial_time) {
            g.drawString("Tap anywhere to jump", 240, 350, paint5);
            g.drawString("Avoid the obstacles", 240, 400, paint5);
            g.drawString("falling from above!", 240, 430, paint5);
        }

    }

private void drawPausedUI() {
    Graphics g = game.getGraphics();
    // Darken the screen to display the Paused screen.
    g.drawARGB(155, 0, 0, 0);
    g.drawImage(g.newImage("UI-Windowfilledhighres.png", Graphics.ImageFormat.RGB565), UI_WINDOW_X_POSITION, UI_WINDOW_Y_POSITION);
    g.drawImage(g.newImage("Pause_Active.png", Graphics.ImageFormat.RGB565), 410, 20);
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