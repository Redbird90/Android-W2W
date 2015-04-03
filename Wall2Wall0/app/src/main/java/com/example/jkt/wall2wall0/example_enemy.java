package com.example.jkt.wall2wall0;

import android.util.Log;

import com.example.jkt.wall2wall0.math.Vector2;

import java.util.Random;

/**
 * Created by JDK on 3/30/2015.
 */
public class example_enemy extends falling_enemy {
    private final int enemy_num;
    public Vector2 velocity = new Vector2(0.0f, 10.0f);
    Random randGenerator = new Random();

    public example_enemy(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.x_pos = x;
        this.y_pos = y;
        this.width = width;
        this.height = height;
        this.enemy_num = randGenerator.nextInt(3);
        Log.i("example_enemy", "new enemy created");
    }
    public void update_enemy() {
            this.y_pos += velocity.getY();
            this.update_bounds();
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

    public void update_bounds() {
        this.bounds.setLowerLeft(this.x_pos, this.y_pos);
    }
}
