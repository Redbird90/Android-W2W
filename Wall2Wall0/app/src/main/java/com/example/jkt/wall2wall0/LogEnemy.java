package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by James on 4/16/2015.
 */
public class LogEnemy extends falling_enemy {

    private Rectangle bounds2;

    public LogEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0.0f, 4.8f);
        this.bounds = new Rectangle(x+35, y+1, 65, 38);
        this.bounds2 = new Rectangle(x, y+16, 47, 43);
        this.bounds_tsil.add(bounds);
        this.bounds_tsil.add(bounds2);
    }

    public String getImageName() {
        return "LogEnemy-100px.png";
    }
}
