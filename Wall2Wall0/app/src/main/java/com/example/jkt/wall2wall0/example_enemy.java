package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by JDK on 3/30/2015.
 */
public class example_enemy extends falling_enemy {
    public Vector2 velocity = new Vector2(0.0f, 10.0f);

    public example_enemy(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.x_pos = x;
        this.y_pos = y;
        this.width = width;
        this.height = height;
    }
    public void update_enemy() {
            this.y_pos -= velocity.getY();
    }
}
