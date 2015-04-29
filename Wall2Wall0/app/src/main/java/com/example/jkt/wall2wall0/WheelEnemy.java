package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Circle;
import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by James on 4/16/2015.
 */
public class WheelEnemy extends falling_enemy {

    public Vector2 center;
    public Circle bounds;

    public WheelEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0f, 5.8f);
        // X AND Y ARE THE CENTER OF THE OBJECT NOT THE CORNER
        this.center = new Vector2(x, y);
        this.bounds = new Circle(x, y, (width / 2));
    }

    @Override
    public void update_bounds() {
        this.bounds.center = (this.center.set(x_pos, this.y_pos));
    }
}
