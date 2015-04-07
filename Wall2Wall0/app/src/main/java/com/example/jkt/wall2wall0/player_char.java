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


public class player_char extends DynamicGameObject {
    Vector2 velocity = new Vector2(0.0f, 0.0f);

    public float x_pos;
    public float y_pos;
    public float width;
    public float height;
    public Rectangle player_rect = this.bounds;
    public float player_score;

    private String char_direction;
    private boolean jumped;
    public boolean dying;

    public player_char(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.x_pos = x;
        this.y_pos = y;
        this.width = width;
        this.height = height;
        this.player_score = 0;
        Log.i("player_char", "new player created");
        Log.i("player_char", String.valueOf(getY_pos()));
    }
    public void start_movement() {
        if (!jumped) {
            jumped = true;
            if (this.x_pos < 240) {
                velocity.set(9.0f, 0.0f);
                this.char_direction = "right";
            } else if (this.x_pos > 240) {
                velocity.set(9.0f, 0.0f);
                this.char_direction = "left";
            }

        }
    }

    public void update_char() {
        if (!dying) {
            if (this.char_direction == "right") {
                this.x_pos += velocity.getX();
                this.y_pos = (float) (0.008 * ((this.x_pos - 220) * (this.x_pos - 220)) + 419.2);
                if (velocity.getX() > 0) {
                    this.player_score += 1.4;
                }
                Log.i(String.valueOf(this.getX_pos()), String.valueOf(this.getY_pos()));
            } else if (this.char_direction == "left") {
                this.x_pos -= velocity.getX();
                this.y_pos = (float) (0.008 * ((this.x_pos - 220) * (this.x_pos - 220)) + 419.2);
                if (velocity.getX() > 0) {
                    this.player_score += 1.4;
                }
                Log.i(String.valueOf(this.getX_pos()), String.valueOf(this.getY_pos()));
            }
            // if left wall reached, reattach to left wall and stop velocity
            if (this.x_pos < 80) {
                this.x_pos = 80f;
                this.y_pos = 576f;
                if (velocity.getX() > 0) {
                    this.player_score += 2.3;
                }
                this.velocity.set(0.0f, 0.0f);
                this.char_direction = "none";
                jumped = false;
            }
            if (this.x_pos > 360) {
                this.x_pos = 360f;
                this.y_pos = 576f;
                if (velocity.getX() > 0) {
                    this.player_score += 2.3;
                }
                this.velocity.set(0.0f, 0.0f);
                this.char_direction = "none";
                jumped = false;
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

