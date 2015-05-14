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
    private int jumpType;

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
    private float x_change;
    private float y_change;

    private ArrayList<Rectangle> leftWallHangArray = new ArrayList();
    private ArrayList<Rectangle> rightWallHangArray = new ArrayList();
    private ArrayList<Rectangle> right3Array = new ArrayList();
    private ArrayList<Rectangle> left3Array = new ArrayList();
    private ArrayList<Rectangle> right4Array = new ArrayList();
    private ArrayList<Rectangle> left4Array = new ArrayList();
    private ArrayList<Rectangle> right5Array = new ArrayList();
    private ArrayList<Rectangle> left5Array = new ArrayList();
    private ArrayList<Rectangle> right6Array = new ArrayList();
    private ArrayList<Rectangle> left6Array = new ArrayList();
    private ArrayList<Rectangle> right7Array = new ArrayList();
    private ArrayList<Rectangle> left7Array = new ArrayList();
    private ArrayList<Rectangle> right8Array = new ArrayList();
    private ArrayList<Rectangle> left8Array = new ArrayList();
    private ArrayList<Rectangle> right10Array = new ArrayList();
    private ArrayList<Rectangle> left10Array = new ArrayList();
    private ArrayList<Rectangle> rightSlidingArray = new ArrayList();
    private ArrayList<Rectangle> leftSlidingArray = new ArrayList();
    private ArrayList<Rectangle> currentSpriteBounds;
    private ArrayList<ArrayList<Rectangle>> arrayOfArrays;



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
        this.dying = false;
        this.x_change = 0;
        this.y_change = 0;
        this.jumpType = 0;
        this.currentSpriteBounds = leftWallHangArray;
        this.arrayOfArrays = new ArrayList<ArrayList<Rectangle>>();
        this.createPlayerRectArrays();
        Log.i("PlayerChar", String.valueOf(this.player_rect.getLowerLeft().getX()+String.valueOf(this.player_rect.getLowerLeft().getY())));
        //this.h_value = 256;
        //this.k_value = 477.2f;
    }
    public void start_movement(int jump_type) {
        if (!jumped) {
            this.jumpType = jump_type;
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
            if (this.jumpType == 0) {
                this.landing_y = this.y_pos - 79.056f;
                this.vertex_y = this.y_pos - 92.93f;
            } else if (this.jumpType == 1) {
                this.landing_y = this.y_pos - 118.584f;
                this.vertex_y = this.y_pos - 139.39f;
            } else {
                this.landing_y = this.y_pos - 158.11f;
                this.vertex_y = this.y_pos - 185.86f;
            }
            timer_started = false;

        }
    }

    public void update_char() {
        //Log.i("PlayerCharUpdate", String.valueOf(this.x_pos+","+this.y_pos));
        this.x_change = 0;
        this.y_change = 0;
        float old_y_pos = this.y_pos;
        float old_x_pos = this.x_pos;
        if (!dying) {
            if (!timer_started && !jumped && first_jump) {
                timer1 = System.currentTimeMillis();
                timer_started = true;
            } else if (timer_started) {
                if (System.currentTimeMillis() - timer1 > 800) {
                    this.sliding = true;
                    this.velocity.set(0.0f, 1f);
                }
                if (System.currentTimeMillis() - timer1 > 1800) {
                    this.sliding = true;
                    this.velocity.set(0.0f, 2f);
                }
                if (System.currentTimeMillis() - timer1 > 4000) {
                    this.sliding = true;
                    this.velocity.set(0.0f, 4f);
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
                if (this.jumpType == 0) {
                    this.y_pos = (float) (0.003 * ((this.x_pos - 256) * (this.x_pos - 256)) + (this.vertex_y + this.decreased_y)); //276, 164;
                } else if (this.jumpType == 1) {
                    this.y_pos = (float) (0.0045 * ((this.x_pos - 256) * (this.x_pos - 256)) + (this.vertex_y + this.decreased_y));
                    this.player_score += 0.2;
                } else {
                    this.y_pos = (float) (0.006 * ((this.x_pos - 256) * (this.x_pos - 256)) + (this.vertex_y + this.decreased_y));
                    this.player_score += 0.4;
                }
                if (velocity.getX() > 0) {
                    this.player_score += 1.4;
                }
            } else if (this.char_direction == "left") {
                this.x_pos -= velocity.getX();
                if (this.jumpType == 0) {
                    this.y_pos = (float) (0.003 * ((this.x_pos - 168) * (this.x_pos - 168)) + (this.vertex_y + this.decreased_y));
                } else if (this.jumpType == 1) {
                    this.y_pos = (float) (0.0045 * ((this.x_pos - 168) * (this.x_pos - 168)) + (this.vertex_y + this.decreased_y));  // ORIGINAL WAS (float) (0.008 * ((this.x_pos - 220) * (this.x_pos - 220)) + 419.2);
                } else {
                    this.y_pos = (float) (0.006 * ((this.x_pos - 168) * (this.x_pos - 168)) + (this.vertex_y + this.decreased_y));
                }
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
            if (this.x_pos > 324) {
                this.x_pos = 324f;
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
        float new_y_pos = this.y_pos;
        float new_x_pos = this.x_pos;
        this.y_change = (new_y_pos - old_y_pos);
        this.x_change = (new_x_pos - old_x_pos);
        //Log.i("PlayerCharUpdate", String.valueOf(this.x_pos+","+this.y_pos));
        //Log.i("PlayerCharBeforeBounds", String.valueOf(this.x_change+","+this.y_change));
        String useless_string = getSpriteName();
        this.update_bounds();
    }

    public String getSpriteName() {
        if (!this.dying) {
            if (this.sliding) {
                if (this.char_facing == "right") {
                    this.currentSpriteBounds = this.rightSlidingArray;
                    return SPRITE_RIGHT_SLIDING;
                } else if (this.char_facing == "left") {
                    this.currentSpriteBounds = this.leftSlidingArray;
                    return SPRITE_LEFT_SLIDING;
                }
            }
            if (!this.jumped) {
                if (this.x_pos < 240) {
                    this.currentSpriteBounds = this.leftWallHangArray;
                    return SPRITE_LEFT_WALL_HANG;
                } else if (this.x_pos > 240) {
                    this.currentSpriteBounds = this.rightWallHangArray;
                    return SPRITE_RIGHT_WALL_HANG;
                }
            } else {
                if (this.char_direction == "right") {
                    if (this.x_pos < 100) {
                        this.currentSpriteBounds = this.right3Array;
                        return SPRITE_RIGHT_3;
                    } else if (this.x_pos < 130) {
                        this.currentSpriteBounds = this.right4Array;
                        return SPRITE_RIGHT_4;
                    } else if (this.x_pos < 160) {
                        this.currentSpriteBounds = this.right5Array;
                        return SPRITE_RIGHT_5;
                    } else if (this.x_pos < 200) {
                        this.currentSpriteBounds = this.right6Array;
                        return SPRITE_RIGHT_6;
                    } else if (this.x_pos < 240) {
                        this.currentSpriteBounds = this.right7Array;
                        return SPRITE_RIGHT_7;
                    } else if (this.x_pos < 290) {
                        this.currentSpriteBounds = this.right8Array;
                        return SPRITE_RIGHT_8;
/*                    } else if (this.x_pos < 315) {
                        return SPRITE_RIGHT_9;*/
                    } else if (this.x_pos < 330) {
                        this.currentSpriteBounds = this.right10Array;
                        return SPRITE_RIGHT_10;
                    } else {
                        Log.i("PLAYER_CHAR", "FAILED1, RETURNING NULL");
                        return null;
                    }
                    /* else if (this.x_pos < 350) {
                        return SPRITE_RIGHT_11;
                    }*/
                } else {
                    if (this.x_pos > 304) {
                        this.currentSpriteBounds = this.left3Array;
                        return SPRITE_LEFT_3;
                    } else if (this.x_pos > 274) {
                        this.currentSpriteBounds = this.left4Array;
                        return SPRITE_LEFT_4;
                    } else if (this.x_pos > 244) {
                        this.currentSpriteBounds = this.left5Array;
                        return SPRITE_LEFT_5;
                    } else if (this.x_pos > 204) {
                        this.currentSpriteBounds = this.left6Array;
                        return SPRITE_LEFT_6;
                    } else if (this.x_pos > 164) {
                        this.currentSpriteBounds = this.left7Array;
                        return SPRITE_LEFT_7;
                    } else if (this.x_pos > 114) {
                        this.currentSpriteBounds = this.left8Array;
                        return SPRITE_LEFT_8;
/*                    } else if (this.x_pos > 153) {
                        return SPRITE_LEFT_9;*/
                    } else if (this.x_pos > 74) {
                        this.currentSpriteBounds = this.left10Array;
                        return SPRITE_LEFT_10;
                    } else {
                        Log.i("PLAYER_CHAR", "FAILED2, RETURNING NULL");
                        return null;
                    }
                    /* else if (this.x_pos > 74) {
                        return SPRITE_LEFT_11;
                    }*/
                }
            }

        } else {
            //Log.i("PlayerChar", "DYING TRUE");
            if (this.y_pos > 620 && this.char_facing == "right") {
                return SPRITE_RIGHT_DYING_LATE;
            } else if (this.y_pos > 620 && this.char_facing == "left") {
                return SPRITE_LEFT_DYING_LATE;
            } else if (this.y_pos <= 620 && this.char_facing == "right") {
                return SPRITE_RIGHT_DYING_EARLY;
            } else if (this.y_pos <= 620 && this.char_facing == "left") {
                return SPRITE_LEFT_DYING_EARLY;
            } else {
                Log.i("PLAYER_CHAR", "FAILED3, RETURNING NULL");
                return null;
            }
        }
        Log.i("PLAYER_CHAR", "FAILED4, RETURNING NULL");
        return null;
    }

    public void createPlayerRectArrays() {
        this.leftWallHangArray.add(new Rectangle(this.x_pos+1,this.y_pos+12,14,16));
        this.leftWallHangArray.add(new Rectangle(this.x_pos+0,this.y_pos+66,15,36));
        this.leftWallHangArray.add(new Rectangle(this.x_pos+15,this.y_pos+44,18,43));
        this.leftWallHangArray.add(new Rectangle(this.x_pos+34,this.y_pos+58,23,12));
        this.leftWallHangArray.add(new Rectangle(this.x_pos+28,this.y_pos+2,45,44));
        this.arrayOfArrays.add(this.leftWallHangArray);

        this.right3Array.add(new Rectangle(this.x_pos+29,this.y_pos+4,46,40));
        this.right3Array.add(new Rectangle(this.x_pos+5,this.y_pos+46,61,25));
        this.right3Array.add(new Rectangle(this.x_pos+22,this.y_pos+69,19,31));
        this.right3Array.add(new Rectangle(this.x_pos+2,this.y_pos+74,21,35));
        this.arrayOfArrays.add(this.right3Array);

        this.right4Array.add(new Rectangle(this.x_pos+24,this.y_pos+2,42,43));
        this.right4Array.add(new Rectangle(this.x_pos+22,this.y_pos+49,29,64));
        this.right4Array.add(new Rectangle(this.x_pos+51,this.y_pos+59,18,12));
        this.right4Array.add(new Rectangle(this.x_pos+0,this.y_pos+83,23,22));
        this.arrayOfArrays.add(this.right4Array);

        this.right5Array.add(new Rectangle(this.x_pos+18,this.y_pos+3,39,46));
        this.right5Array.add(new Rectangle(this.x_pos+53,this.y_pos+38,15,18));
        this.right5Array.add(new Rectangle(this.x_pos+29,this.y_pos+50,22,69));
        this.right5Array.add(new Rectangle(this.x_pos+1,this.y_pos+19,14,23));
        this.arrayOfArrays.add(this.right5Array);

        this.right6Array.add(new Rectangle(this.x_pos+18,this.y_pos+4,33,100));
        this.right6Array.add(new Rectangle(this.x_pos+2,this.y_pos+23,14,21));
        this.right6Array.add(new Rectangle(this.x_pos+52,this.y_pos+84,9,18));
        this.right6Array.add(new Rectangle(this.x_pos+10,this.y_pos+102,13,20));
        this.arrayOfArrays.add(this.right6Array);

        this.right7Array.add(new Rectangle(this.x_pos+26,this.y_pos+5,27,46));
        this.right7Array.add(new Rectangle(this.x_pos+2,this.y_pos+26,21,25));
        this.right7Array.add(new Rectangle(this.x_pos+27,this.y_pos+57,15,65));
        this.right7Array.add(new Rectangle(this.x_pos+42,this.y_pos+76,13,36));
        this.arrayOfArrays.add(this.right7Array);

        this.right8Array.add(new Rectangle(this.x_pos+3,this.y_pos+20,14,30));
        this.right8Array.add(new Rectangle(this.x_pos+17,this.y_pos+4,37,46));
        this.right8Array.add(new Rectangle(this.x_pos+26,this.y_pos+54,25,34));
        this.right8Array.add(new Rectangle(this.x_pos+43,this.y_pos+90,23,19));
        this.arrayOfArrays.add(this.right8Array);

        this.right10Array.add(new Rectangle(this.x_pos+3,this.y_pos+4,35,45));
        this.right10Array.add(new Rectangle(this.x_pos+10,this.y_pos+50,48,32));
        this.right10Array.add(new Rectangle(this.x_pos+33,this.y_pos+84,22,23));
        this.arrayOfArrays.add(this.right10Array);

        this.rightSlidingArray.add(new Rectangle(this.x_pos+2,this.y_pos+15,18,17));
        this.rightSlidingArray.add(new Rectangle(this.x_pos+29,this.y_pos+5,41,40));
        this.rightSlidingArray.add(new Rectangle(this.x_pos+15,this.y_pos+51,19,39));
        this.rightSlidingArray.add(new Rectangle(this.x_pos+35,this.y_pos+58,22,12));
        this.rightSlidingArray.add(new Rectangle(this.x_pos+3,this.y_pos+67,11,34));
        this.arrayOfArrays.add(this.rightSlidingArray);

        this.rightWallHangArray.add(new Rectangle(this.x_pos+61,this.y_pos+12,14,16));
        this.rightWallHangArray.add(new Rectangle(this.x_pos+61,this.y_pos+66,15,36));
        this.rightWallHangArray.add(new Rectangle(this.x_pos+43,this.y_pos+44,18,43));
        this.rightWallHangArray.add(new Rectangle(this.x_pos+19,this.y_pos+58,23,12));
        this.rightWallHangArray.add(new Rectangle(this.x_pos+3,this.y_pos+2,45,44));
        this.arrayOfArrays.add(this.rightWallHangArray);

        this.left3Array.add(new Rectangle(this.x_pos+2,this.y_pos+4,46,40));
        this.left3Array.add(new Rectangle(this.x_pos+11,this.y_pos+46,61,25));
        this.left3Array.add(new Rectangle(this.x_pos+36,this.y_pos+69,19,31));
        this.left3Array.add(new Rectangle(this.x_pos+54,this.y_pos+74,21,35));
        this.arrayOfArrays.add(this.left3Array);

        this.left4Array.add(new Rectangle(this.x_pos+6,this.y_pos+2,42,43));
        this.left4Array.add(new Rectangle(this.x_pos+21,this.y_pos+49,29,64));
        this.left4Array.add(new Rectangle(this.x_pos+3,this.y_pos+59,18,12));
        this.left4Array.add(new Rectangle(this.x_pos+49,this.y_pos+83,23,22));
        this.arrayOfArrays.add(this.left4Array);

        this.left5Array.add(new Rectangle(this.x_pos+13,this.y_pos+3,39,46));
        this.left5Array.add(new Rectangle(this.x_pos+2,this.y_pos+38,15,18));
        this.left5Array.add(new Rectangle(this.x_pos+19,this.y_pos+50,22,69));
        this.left5Array.add(new Rectangle(this.x_pos+55,this.y_pos+19,14,23));
        this.arrayOfArrays.add(this.left5Array);

        this.left6Array.add(new Rectangle(this.x_pos+10,this.y_pos+4,33,100));
        this.left6Array.add(new Rectangle(this.x_pos+45,this.y_pos+23,14,21));
        this.left6Array.add(new Rectangle(this.x_pos+0,this.y_pos+84,9,18));
        this.left6Array.add(new Rectangle(this.x_pos+38,this.y_pos+102,13,20));
        this.arrayOfArrays.add(this.left6Array);

        this.left7Array.add(new Rectangle(this.x_pos+8,this.y_pos+5,27,46));
        this.left7Array.add(new Rectangle(this.x_pos+38,this.y_pos+26,21,25));
        this.left7Array.add(new Rectangle(this.x_pos+19,this.y_pos+57,15,65));
        this.left7Array.add(new Rectangle(this.x_pos+6,this.y_pos+76,13,36));
        this.arrayOfArrays.add(this.left7Array);

        this.left8Array.add(new Rectangle(this.x_pos+50,this.y_pos+20,14,30));
        this.left8Array.add(new Rectangle(this.x_pos+13,this.y_pos+4,37,46));
        this.left8Array.add(new Rectangle(this.x_pos+16,this.y_pos+54,25,34));
        this.left8Array.add(new Rectangle(this.x_pos+1,this.y_pos+90,23,19));
        this.arrayOfArrays.add(this.left8Array);

        this.left10Array.add(new Rectangle(this.x_pos+24,this.y_pos+4,35,45));
        this.left10Array.add(new Rectangle(this.x_pos+4,this.y_pos+50,48,32));
        this.left10Array.add(new Rectangle(this.x_pos+7,this.y_pos+84,22,23));
        this.arrayOfArrays.add(this.left10Array);

        this.leftSlidingArray.add(new Rectangle(this.x_pos+57,this.y_pos+15,18,17));
        this.leftSlidingArray.add(new Rectangle(this.x_pos+7,this.y_pos+5,41,40));
        this.leftSlidingArray.add(new Rectangle(this.x_pos+43,this.y_pos+51,19,39));
        this.leftSlidingArray.add(new Rectangle(this.x_pos+20,this.y_pos+58,22,12));
        this.leftSlidingArray.add(new Rectangle(this.x_pos+63,this.y_pos+67,11,34));
        this.arrayOfArrays.add(this.leftSlidingArray);



        Log.i("PlayerChar", "RectArrays created");


    }

    public ArrayList<Rectangle> getCurrentSpriteBounds () {
        //Log.i("PlayerChar", "gettingSpriteBounds");
        return this.currentSpriteBounds;
    }

    public void update_bounds() {
        for (int i=0; i < this.arrayOfArrays.size(); i++) {
            for (int z=0; z < this.arrayOfArrays.get(i).size(); z++) {
                this.arrayOfArrays.get(i).get(z).setLowerLeft(
                        this.arrayOfArrays.get(i).get(z).getLowerLeft().getX()+this.x_change,
                        this.arrayOfArrays.get(i).get(z).getLowerLeft().getY()+this.y_change);
            }
        }
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

