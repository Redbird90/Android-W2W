package com.example.jkt.wall2wall0;

import android.util.Log;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by James on 4/16/2015.
 */
public class LogEnemy extends falling_enemy {

    private Rectangle bounds2;
    private Rectangle bounds3;

    public LogEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0.0f, 4.8f);
        this.bounds = new Rectangle(x+8, y+34, 20, 15);
        this.bounds2 = new Rectangle(x+34, y+14, 29, 21);
        this.bounds3 = new Rectangle(x+74, y+8, 20, 25);
        this.bounds_tsil.add(bounds);
        this.bounds_tsil.add(bounds2);
        this.bounds_tsil.add(bounds3);
    }

    public String getImageName() {
        return "LogEnemyhighres-100px.png";
    }
}
