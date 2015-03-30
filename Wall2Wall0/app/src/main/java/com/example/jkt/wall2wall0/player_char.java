package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by JDK on 3/29/2015.
 */
public class player_char extends DynamicGameObject {
    Vector2 velocity = new Vector2(0.0f, 0.0f);
    private float x_pos;
    private float y_pos;
    private String char_direction;

    public player_char(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    public void start_movement(String direction) {
        this.char_direction = direction;
    }
    public void update_char() {
        boolean moving_right;
        if (this.char_direction == "right") {
            this.x_pos += velocity.getX();
            this.y_pos += velocity.getY();
        } else if (this.char_direction == "left") {
            this.x_pos -= velocity.getX();
            this.y_pos -= velocity.getY();
        }
    }
    public void stop_movement() {
        this.velocity.set(0.0f, 0.0f);
    }
}

