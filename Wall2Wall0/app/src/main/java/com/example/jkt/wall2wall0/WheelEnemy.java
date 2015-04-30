package com.example.jkt.wall2wall0;

import android.util.Log;

import com.example.jkt.wall2wall0.math.Circle;
import com.example.jkt.wall2wall0.math.Shape;
import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by James on 4/16/2015.
 */
public class WheelEnemy extends falling_enemy {

    private Circle test_circ = new Circle(50, 50, 20);

    public WheelEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0f, 5.8f);
        // X AND Y ARE THE CENTER OF THE OBJECT NOT THE CORNER
        this.bounds = new Circle(x, y, (width / 2));
    }

    @Override
    public void update_bounds() {
        test_circ.setCenter(80, 80);
        Log.i("WheelEnemy", "Using local update_bounds()");
        Log.i("new x and y", String.valueOf(this.x_pos)+","+String.valueOf(this.y_pos));
        //this.bounds.center = this.bounds.center.set(this.x_pos, this.y_pos);
        //bounds.center = new Vector2(this.getX_pos(), this.getY_pos());
        this.bounds.setCenter(this.getX_pos(), this.getY_pos());
        //Log.i("WheelEnemy", String.valueOf(bounds.center.getY()));
    }
}