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

    private String char_direction;
    private boolean jumped;

    public player_char(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.x_pos = x;
        this.y_pos = y;
        this.width = width;
        this.height = height;
        Log.i("player_char", "new player created");
    }
    public void start_movement() {
        if (!jumped) {
            jumped = true;
            if (this.x_pos < 385) {
                velocity.set(3.0f, 0.0f);
                this.char_direction = "right";
            } else if (this.x_pos > 385) {
                velocity.set(3.0f, 0.0f);
                this.char_direction = "left";
            }

        }
    }

    public void update_char() {
        if (this.char_direction == "right") {
            this.x_pos += velocity.getX();
            this.y_pos += velocity.getY();
        } else if (this.char_direction == "left") {
            this.x_pos -= velocity.getX();
            this.y_pos -= velocity.getY();
        }
        // if left wall reached, reattach to left wall and stop velocity
        if (this.x_pos <= 140) {
            this.x_pos = 210f;
            this.y_pos = 1113f;
            this.velocity.set(0.0f, 0.0f);
            this.char_direction = "none";
        }
        if (this.x_pos >= 630) {
            this.x_pos = 590f;
            this.y_pos = 1113f;
            this.velocity.set(0.0f, 0.0f);
            this.char_direction = "none";
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

