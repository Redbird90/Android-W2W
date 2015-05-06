package com.example.jkt.wall2wall0;

import android.util.Log;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

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
        this.x_pos = x;
        this.y_pos = y;
        this.width = width;
        this.height = height;
        this.player_score = 0;
        this.player_rect = new Rectangle(x-width/2, y-height/2, width, height);
        this.frame_num = 1;
        this.char_facing = "right";
        this.sliding = false;
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
                    return SPRITE_RIGHT_SLIDING;
                } else if (this.char_facing == "left") {
                    return SPRITE_LEFT_SLIDING;
                }
            }
            if (!this.jumped) {
                if (this.x_pos < 240) {
                    return SPRITE_LEFT_WALL_HANG;
                } else if (this.x_pos > 240) {
                    return SPRITE_RIGHT_WALL_HANG;
                }
            } else {
                if (this.char_direction == "right") {
                    if (this.x_pos < 90) {
                        return SPRITE_RIGHT_3;
                    } else if (this.x_pos < 130) {
                        return SPRITE_RIGHT_4;
                    } else if (this.x_pos < 170) {
                        return SPRITE_RIGHT_5;
                    } else if (this.x_pos < 210) {
                        return SPRITE_RIGHT_6;
                    } else if (this.x_pos < 256) {
                        return SPRITE_RIGHT_7;
                    } else if (this.x_pos < 320) {
                        return SPRITE_RIGHT_8;
/*                    } else if (this.x_pos < 315) {
                        return SPRITE_RIGHT_9;*/
                    } else if (this.x_pos < 340) {
                        return SPRITE_RIGHT_10;
                    } else if (this.x_pos < 350) {
                        return SPRITE_RIGHT_11;
                    }
                } else if (this.char_direction == "left") {
                    if (this.x_pos > 334) {
                        return SPRITE_LEFT_3;
                    } else if (this.x_pos > 294) {
                        return SPRITE_LEFT_4;
                    } else if (this.x_pos > 254) {
                        return SPRITE_LEFT_5;
                    } else if (this.x_pos > 214) {
                        return SPRITE_LEFT_6;
                    } else if (this.x_pos > 168) {
                        return SPRITE_LEFT_7;
                    } else if (this.x_pos > 104) {
                        return SPRITE_LEFT_8;
/*                    } else if (this.x_pos > 153) {
                        return SPRITE_LEFT_9;*/
                    } else if (this.x_pos > 93) {
                        return SPRITE_LEFT_10;
                    } else if (this.x_pos > 74) {
                        return SPRITE_LEFT_11;
                    }
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

