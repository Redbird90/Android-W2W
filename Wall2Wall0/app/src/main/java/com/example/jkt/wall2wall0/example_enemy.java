package com.example.jkt.wall2wall0;

import android.util.Log;

import com.example.jkt.wall2wall0.math.Vector2;

import java.util.Random;

/**
 * Created by JDK on 3/30/2015.
 */
public class example_enemy extends falling_enemy {
    private final int enemy_num;
    public Vector2 velocity = new Vector2(0.0f, 3.0f); // CHANGED ON 4/6

    public int getEnemy_num() {
        return enemy_num;
    }

    public example_enemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height);
        this.x_pos = x;
        this.y_pos = y;
        this.width = width;
        this.height = height;
        this.enemy_num = enemy_num;
        Log.i("example_enemy", "new enemy created");
        Log.i("TESTING", "enemy_num" + String.valueOf((this.enemy_num)) + " and bounds " + String.valueOf(this.bounds.getLowerLeft())+","+String.valueOf(this.bounds.width)+","+String.valueOf(this.bounds.height));
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
