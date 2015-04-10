package com.example.jkt.wall2wall0;

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


public class player_char extends DynamicGameObject {
    Vector2 velocity = new Vector2(0.0f, 0.0f);

    public float x_pos;
    public float y_pos;
    public float width;
    public float height;
    public Rectangle player_rect = this.bounds;
    public float player_score;
    public float landing_y;
    public float vertex_y;

    private String char_direction;
    public boolean jumped;
    public boolean dying;
    public boolean first_jump;
    public boolean too_high1;
    public boolean too_high2;
    private long timer1;
    private boolean timer_started = false;
    private float decreased_y;

    public player_char(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.x_pos = x;
        this.y_pos = y;
        this.width = width;
        this.height = height;
        this.player_score = 0;
    }
    public void start_movement() {
        if (!jumped) {
            first_jump = true;
            jumped = true;
            if (this.x_pos < 240) {
                velocity.set(12.0f, 0.0f);
                this.char_direction = "right";
            } else if (this.x_pos > 240) {
                velocity.set(12.0f, 0.0f);
                this.char_direction = "left";
            }
            landing_y = this.y_pos - 78.4f;
            vertex_y = this.y_pos - 96.04f;
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
                    this.velocity.set(0.0f, 0.5f);
                }
                if (System.currentTimeMillis() - timer1 > 3000) {
                    this.velocity.set(0.0f, 1.0f);
                }
                if (System.currentTimeMillis() - timer1 > 6000) {
                    this.velocity.set(0.0f, 2.0f);
                }
            }

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

            if (this.char_direction == "right") {
                this.x_pos += velocity.getX();
                this.y_pos = (float) (0.0025 * ((this.x_pos - 276) * (this.x_pos - 276)) + (vertex_y + decreased_y));
                if (velocity.getX() > 0) {
                    this.player_score += 1.4;
                }
            } else if (this.char_direction == "left") {
                this.x_pos -= velocity.getX();
                this.y_pos = (float) (0.0025 * ((this.x_pos - 164) * (this.x_pos - 164)) + (vertex_y + decreased_y));  // ORIGINAL WAS (float) (0.008 * ((this.x_pos - 220) * (this.x_pos - 220)) + 419.2);
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
                jumped = false;
                decreased_y = 0f;
            }
            if (this.x_pos > 360) {
                this.x_pos = 360f;
                this.y_pos = landing_y + decreased_y;
                if (velocity.getX() > 0) {
                    this.player_score += 2.3;
                }
                this.velocity.set(0.0f, 0.0f);
                this.char_direction = "none";
                jumped = false;
                decreased_y = 0f;
            }

        } else {
            this.y_pos += 18f;
        }
        this.update_bounds();
    }

    public void update_bounds() {
        this.bounds.setLowerLeft(this.x_pos, this.y_pos);
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

