package com.example.jkt.wall2wall0;

import android.util.Log;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

import java.util.ArrayList;

/**
 * Created by JDK on 3/29/2015.
 */

/**         Dyn constructor:
 *         velocity = new Vector2();
            accel = new Vector2();
            GameObj constructor:
            this.position = new Vector2(x,y);
            this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
 */


public class PlayerChar extends DynamicGameObject {
    Vector2 velocity = new Vector2(0.0f, 0.0f);

    public float x_pos;
    public float y_pos;
    public float width;
    public float height;
    public Rectangle player_rect;
    public float player_score;
    public float landing_y;
    public float vertex_y;
    public int frame_num;

    private String char_direction;
    private String char_facing;
    public boolean jumped;
    public boolean dying;
    public boolean first_jump;
    public boolean too_high1;
    public boolean too_high2;
    private long timer1;
    private boolean timer_started = false;
    private float decreased_y;
    private boolean sliding;

    private ArrayList<Rectangle> leftWallHangArray = new ArrayList<>();
    private ArrayList<Rectangle> rightWallHangArray = new ArrayList<>();
    private ArrayList<Rectangle> right3Array = new ArrayList<>();
    private ArrayList<Rectangle> left3Array = new ArrayList<>();
    private ArrayList<Rectangle> right4Array = new ArrayList<>();
    private ArrayList<Rectangle> left4Array = new ArrayList<>();
    private ArrayList<Rectangle> right5Array = new ArrayList<>();
    private ArrayList<Rectangle> left5Array = new ArrayList<>();
    private ArrayList<Rectangle> right6Array = new ArrayList<>();
    private ArrayList<Rectangle> left6Array = new ArrayList<>();
    private ArrayList<Rectangle> right7Array = new ArrayList<>();
    private ArrayList<Rectangle> left7Array = new ArrayList<>();
    private ArrayList<Rectangle> right8Array = new ArrayList<>();
    private ArrayList<Rectangle> left8Array = new ArrayList<>();
    private ArrayList<Rectangle> right10Array = new ArrayList<>();
    private ArrayList<Rectangle> left10Array = new ArrayList<>();
    private ArrayList<Rectangle> rightSlidingArray = new ArrayList<>();
    private ArrayList<Rectangle> leftSlidingArray = new ArrayList<>();
    private ArrayList<Rectangle> currentSpriteBounds;



    // SPRITE_DIRECTION REFERS TO MOVEMENT OF CHARACTER
    private String SPRITE_LEFT_WALL_HANG = "Sprite2highres_reverse-6perc.png";
    private String SPRITE_RIGHT_WALL_HANG = "Sprite2highres-6perc.png";
    private String SPRITE_RIGHT_DYING_EARLY = "Sprite13highres_reverse-6perc.png";
    private String SPRITE_LEFT_DYING_EARLY = "Sprite13highres-6perc.png";
    private String SPRITE_RIGHT_DYING_LATE = "Sprite14highres_reverse-6perc.png";
    private String SPRITE_LEFT_DYING_LATE = "Sprite14highres-6perc.png";
    private String SPRITE_RIGHT_3 = "Sprite3highres_reverse-6perc.png";
    private String SPRITE_LEFT_3 = "Sprite3highres-6perc.png";
    private String SPRITE_RIGHT_4 = "Sprite4highres_reverse-6perc.png";
    private String SPRITE_LEFT_4 = "Sprite4highres-6perc.png";
    private String SPRITE_RIGHT_5 = "Sprite5highres_reverse-6perc.png";
    private String SPRITE_LEFT_5 = "Sprite5highres-6perc.png";
    private String SPRITE_RIGHT_6 = "Sprite6highres_reverse-6perc.png";
    private String SPRITE_LEFT_6 = "Sprite6highres-6perc.png";
    private String SPRITE_RIGHT_7 = "Sprite7highres_reverse-6perc.png";
    private String SPRITE_LEFT_7 = "Sprite7highres-6perc.png";
    private String SPRITE_RIGHT_8 = "Sprite8highres_reverse-6perc.png";
    private String SPRITE_LEFT_8 = "Sprite8highres-6perc.png";
    private String SPRITE_RIGHT_9 = "Sprite9highres_reverse-6perc.png";
    private String SPRITE_LEFT_9 = "Sprite9highres-6perc.png";
    private String SPRITE_RIGHT_10 = "Sprite10highres_reverse-6perc.png";
    private String SPRITE_LEFT_10 = "Sprite10highres-6perc.png";
    private String SPRITE_RIGHT_11 = "Sprite11highres_reverse-6perc.png";
    private String SPRITE_LEFT_11 = "Sprite11highres-6perc.png";
    private String SPRITE_RIGHT_SLIDING = "Sprite16highres_reverse-6perc.png";
    private String SPRITE_LEFT_SLIDING = "Sprite16highres-6perc.png";


    //private final float h_value;
    //private final float k_value;

    public PlayerChar(float x, float y, float width, float height) {
        super(x, y, width, height);
        Log.i("PlayerChar", "Constructing player1");
        this.x_pos = x;
        this.y_pos = y;
        this.width = width;
        this.height = height;
        this.player_score = 0;
        this.player_rect = new Rectangle(x-width/2, y-height/2, width, height);
        this.frame_num = 1;
        this.char_facing = "right";
        this.sliding = false;
        this.currentSpriteBounds = leftWallHangArray;
        this.createPlayerRectArrays();
        Log.i("PlayerChar", String.valueOf(this.player_rect.getLowerLeft().getX()+String.valueOf(this.player_rect.getLowerLeft().getY())));
        //this.h_value = 256;
        //this.k_value = 477.2f;
    }
    public void start_movement() {
        if (!jumped) {
            this.first_jump = true;
            this.jumped = true;
            this.sliding = false;
            if (this.x_pos < 240) {
                this.velocity.set(12.0f, 0.0f);
                this.char_direction = "right";
                this.char_facing = "right";
            } else if (this.x_pos > 240) {
                this.velocity.set(12.0f, 0.0f);
                this.char_direction = "left";
                this.char_facing = "left";
            }
            landing_y = this.y_pos - 76.44f;
            vertex_y = this.y_pos - 108.42f;
            timer_started = false;

        }
    }

    public void update_char() {
        if (!dying) {
            if (!timer_started && !jumped && first_jump) {
                timer1 = System.currentTimeMillis();
                timer_started = true;
            } else if (timer_started) {
                if (System.currentTimeMillis() - timer1 > 1800) {
                    this.sliding = true;
                    this.velocity.set(0.0f, 0.5f);
                }
                if (System.currentTimeMillis() - timer1 > 3000) {
                    this.sliding = true;
                    this.velocity.set(0.0f, 1.0f);
                }
                if (System.currentTimeMillis() - timer1 > 6000) {
                    this.sliding = true;
                    this.velocity.set(0.0f, 2.0f);
                }
            }

            // Adjust when character is too high
            if (this.y_pos <= 526) {
                if (this.y_pos <= 476) {
                    this.setY_pos(this.y_pos + 4.0f);
                    if (jumped) {
                        decreased_y += 4.0f;
                    }
                }
                this.setY_pos(this.y_pos + 2.0f);
                if (jumped) {
                    decreased_y += 2.0f;
                }
            }

            // Add velocities to positions
            if (this.char_direction == "right") {
                this.x_pos += velocity.getX();
                this.y_pos = (float) (0.003 * ((this.x_pos - 256) * (this.x_pos - 256)) + (vertex_y + decreased_y)); //276, 164
                if (velocity.getX() > 0) {
                    this.player_score += 1.4;
                }
            } else if (this.char_direction == "left") {
                this.x_pos -= velocity.getX();
                this.y_pos = (float) (0.003 * ((this.x_pos - 168) * (this.x_pos - 168)) + (vertex_y + decreased_y));  // ORIGINAL WAS (float) (0.008 * ((this.x_pos - 220) * (this.x_pos - 220)) + 419.2);
                if (velocity.getX() > 0) {
                    this.player_score += 1.4;
                }
            } else {
                this.y_pos += this.velocity.getY();
            }
            // if left wall reached, reattach to left wall and stop velocity
            if (this.x_pos < 80) {
                this.x_pos = 80f;
                this.y_pos = landing_y + decreased_y;
                if (velocity.getX() > 0) {
                    this.player_score += 2.3;
                }
                this.velocity.set(0.0f, 0.0f);
                this.char_direction = "none";
                this.char_facing = "right";
                this.jumped = false;
                decreased_y = 0f;
            }
            if (this.x_pos > 344) {
                this.x_pos = 344f;
                this.y_pos = landing_y + decreased_y;
                if (velocity.getX() > 0) {
                    this.player_score += 2.3;
                }
                this.velocity.set(0.0f, 0.0f);
                this.char_direction = "none";
                this.char_facing = "left";
                this.jumped = false;
                decreased_y = 0f;
            }

        } else {
            this.y_pos += 18f;
        }
        this.update_bounds();
    }

    public String getSpriteName() {
        if (!this.dying) {
            if (this.sliding) {
                if (this.char_facing == "right") {
                    this.currentSpriteBounds = rightSlidingArray;
                    return SPRITE_RIGHT_SLIDING;
                } else if (this.char_facing == "left") {
                    this.currentSpriteBounds = leftSlidingArray;
                    return SPRITE_LEFT_SLIDING;
                }
            }
            if (!this.jumped) {
                if (this.x_pos < 240) {
                    this.currentSpriteBounds = leftWallHangArray;
                    return SPRITE_LEFT_WALL_HANG;
                } else if (this.x_pos > 240) {
                    this.currentSpriteBounds = rightWallHangArray;
                    return SPRITE_RIGHT_WALL_HANG;
                }
            } else {
                if (this.char_direction == "right") {
                    if (this.x_pos < 90) {
                        this.currentSpriteBounds = right3Array;
                        return SPRITE_RIGHT_3;
                    } else if (this.x_pos < 130) {
                        this.currentSpriteBounds = right4Array;
                        return SPRITE_RIGHT_4;
                    } else if (this.x_pos < 170) {
                        this.currentSpriteBounds = right5Array;
                        return SPRITE_RIGHT_5;
                    } else if (this.x_pos < 210) {
                        this.currentSpriteBounds = right6Array;
                        return SPRITE_RIGHT_6;
                    } else if (this.x_pos < 256) {
                        this.currentSpriteBounds = right7Array;
                        return SPRITE_RIGHT_7;
                    } else if (this.x_pos < 320) {
                        this.currentSpriteBounds = right8Array;
                        return SPRITE_RIGHT_8;
/*                    } else if (this.x_pos < 315) {
                        return SPRITE_RIGHT_9;*/
                    } else if (this.x_pos < 340) {
                        this.currentSpriteBounds = right10Array;
                        return SPRITE_RIGHT_10;
                    }/* else if (this.x_pos < 350) {
                        return SPRITE_RIGHT_11;
                    }*/
                } else if (this.char_direction == "left") {
                    if (this.x_pos > 334) {
                        this.currentSpriteBounds = left3Array;
                        return SPRITE_LEFT_3;
                    } else if (this.x_pos > 294) {
                        this.currentSpriteBounds = left4Array;
                        return SPRITE_LEFT_4;
                    } else if (this.x_pos > 254) {
                        this.currentSpriteBounds = left5Array;
                        return SPRITE_LEFT_5;
                    } else if (this.x_pos > 214) {
                        this.currentSpriteBounds = left6Array;
                        return SPRITE_LEFT_6;
                    } else if (this.x_pos > 168) {
                        this.currentSpriteBounds = left7Array;
                        return SPRITE_LEFT_7;
                    } else if (this.x_pos > 104) {
                        this.currentSpriteBounds = left8Array;
                        return SPRITE_LEFT_8;
/*                    } else if (this.x_pos > 153) {
                        return SPRITE_LEFT_9;*/
                    } else if (this.x_pos > 93) {
                        this.currentSpriteBounds = left10Array;
                        return SPRITE_LEFT_10;
                    }/* else if (this.x_pos > 74) {
                        return SPRITE_LEFT_11;
                    }*/
                }
            }

        } else {
            } if (this.y_pos > 620 && this.char_facing == "right") {
                return SPRITE_RIGHT_DYING_LATE;
            } else if (this.y_pos > 620 && this.char_facing == "left") {
                return SPRITE_LEFT_DYING_LATE;
            } else if (this.y_pos <= 620 && this.char_facing == "right") {
                return SPRITE_RIGHT_DYING_EARLY;
            } else if (this.y_pos <= 620 && this.char_facing == "left") {
            return SPRITE_LEFT_DYING_EARLY;
        } else {
            Log.i("PLAYER_CHAR", "FAILED, RETURNING NULL");
            return null;
        }
    }

    public void createPlayerRectArrays() {
        this.leftWallHangArray.add(new Rectangle(1,12,14,16));
        this.leftWallHangArray.add(new Rectangle(0,66,15,36));
        this.leftWallHangArray.add(new Rectangle(15,44,18,43));
        this.leftWallHangArray.add(new Rectangle(34,58,23,12));
        this.leftWallHangArray.add(new Rectangle(28,2,45,44));

        this.right3Array.add(new Rectangle(29,4,46,40));
        this.right3Array.add(new Rectangle(5,46,61,25));
        this.right3Array.add(new Rectangle(22,69,19,31));
        this.right3Array.add(new Rectangle(2,74,21,35));

        this.right4Array.add(new Rectangle(24,2,42,43));
        this.right4Array.add(new Rectangle(22,49,29,64));
        this.right4Array.add(new Rectangle(51,59,18,12));
        this.right4Array.add(new Rectangle(0,83,23,22));

        this.right5Array.add(new Rectangle(18,3,39,46));
        this.right5Array.add(new Rectangle(53,38,15,18));
        this.right5Array.add(new Rectangle(29,50,22,69));
        this.right5Array.add(new Rectangle(1,19,14,23));

        this.right6Array.add(new Rectangle(18,4,33,100));
        this.right6Array.add(new Rectangle(2,23,14,21));
        this.right6Array.add(new Rectangle(52,84,9,18));
        this.right6Array.add(new Rectangle(10,102,13,20));

        this.right7Array.add(new Rectangle(26,5,27,46));
        this.right7Array.add(new Rectangle(2,26,21,25));
        this.right7Array.add(new Rectangle(27,57,15,65));
        this.right7Array.add(new Rectangle(42,76,13,36));

        this.right8Array.add(new Rectangle(3,20,14,30));
        this.right8Array.add(new Rectangle(17,4,37,46));
        this.right8Array.add(new Rectangle(26,54,25,34));
        this.right8Array.add(new Rectangle(43,90,23,19));

        this.right10Array.add(new Rectangle(3,4,35,45));
        this.right10Array.add(new Rectangle(10,50,48,32));
        this.right10Array.add(new Rectangle(33,84,22,23));

        this.rightSlidingArray.add(new Rectangle(2,15,18,17));
        this.rightSlidingArray.add(new Rectangle(29,5,41,40));
        this.rightSlidingArray.add(new Rectangle(15,51,19,39));
        this.rightSlidingArray.add(new Rectangle(35,58,22,12));
        this.rightSlidingArray.add(new Rectangle(3,67,11,34));

        this.rightWallHangArray.add(new Rectangle(61,12,14,16));
        this.rightWallHangArray.add(new Rectangle(61,66,15,36));
        this.rightWallHangArray.add(new Rectangle(43,44,18,43));
        this.rightWallHangArray.add(new Rectangle(19,58,23,12));
        this.rightWallHangArray.add(new Rectangle(3,2,45,44));

        this.left3Array.add(new Rectangle(2,4,46,40));
        this.left3Array.add(new Rectangle(11,46,61,25));
        this.left3Array.add(new Rectangle(36,69,19,31));
        this.left3Array.add(new Rectangle(54,74,21,35));

        this.left4Array.add(new Rectangle(6,2,42,43));
        this.left4Array.add(new Rectangle(21,49,29,64));
        this.left4Array.add(new Rectangle(3,59,18,12));
        this.left4Array.add(new Rectangle(49,83,23,22));

        this.left5Array.add(new Rectangle(13,3,39,46));
        this.left5Array.add(new Rectangle(2,38,15,18));
        this.left5Array.add(new Rectangle(19,50,22,69));
        this.left5Array.add(new Rectangle(55,19,14,23));

        this.left6Array.add(new Rectangle(10,4,33,100));
        this.left6Array.add(new Rectangle(45,23,14,21));
        this.left6Array.add(new Rectangle(0,84,9,18));
        this.left6Array.add(new Rectangle(38,102,13,20));

        this.left7Array.add(new Rectangle(8,5,27,46));
        this.left7Array.add(new Rectangle(38,26,21,25));
        this.left7Array.add(new Rectangle(19,57,15,65));
        this.left7Array.add(new Rectangle(6,76,13,36));
        
        this.left8Array.add(new Rectangle(50,20,14,30));
        this.left8Array.add(new Rectangle(13,4,37,46));
        this.left8Array.add(new Rectangle(16,54,25,34));
        this.left8Array.add(new Rectangle(1,90,23,19));

        this.left10Array.add(new Rectangle(24,4,35,45));
        this.left10Array.add(new Rectangle(4,50,48,32));
        this.left10Array.add(new Rectangle(7,84,22,23));

        this.leftSlidingArray.add(new Rectangle(57,15,18,17));
        this.leftSlidingArray.add(new Rectangle(7,5,41,40));
        this.leftSlidingArray.add(new Rectangle(43,51,19,39));
        this.leftSlidingArray.add(new Rectangle(20,58,22,12));
        this.leftSlidingArray.add(new Rectangle(63,67,11,34));
        Log.i("PlayerChar", "RectArrays created");


    }

    public ArrayList<Rectangle> getCurrentSpriteBounds () {
        //Log.i("PlayerChar", "gettingSpriteBounds");
        return this.currentSpriteBounds;
    }

    public void update_bounds() {
        this.player_rect.setLowerLeft(this.x_pos, this.y_pos);
    }

    public void stop_movement() {// CURRENTLY OBSOLETE
        this.velocity.set(0.0f, 0.0f);
    }

    public float getX_pos() {
        return x_pos;
    }

    public void setX_pos(float x_pos) {
        this.x_pos = x_pos;
    }

    public float getY_pos() {
        return y_pos;
    }

    public void setY_pos(float y_pos) {
        this.y_pos = y_pos;
    }

}

