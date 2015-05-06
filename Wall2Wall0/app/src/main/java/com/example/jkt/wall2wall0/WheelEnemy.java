package com.example.jkt.wall2wall0;

import android.util.Log;

import com.example.jkt.wall2wall0.math.Circle;
import com.example.jkt.wall2wall0.math.Shape;
import com.example.jkt.wall2wall0.math.Vector2;

import java.util.Random;

/**
 * Created by James on 4/16/2015.
 */
public class WheelEnemy extends falling_enemy {

    private final int wheel_orientation;
    private Circle test_circ = new Circle(50, 50, 20);
    private Random randGen = new Random();

    public WheelEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0f, 5.8f);
        // X AND Y ARE THE CENTER OF THE OBJECT NOT THE CORNER
        this.bounds = new Circle(x, y, (width / 2));
        this.bounds_tsil.add(this.bounds);
        this.wheel_orientation = randGen.nextInt(4);
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

    public String getImageName() {
        if (this.wheel_orientation == 0) {
            return "Factory_Wheelhighres_square-80px.png";
        } else if (this.wheel_orientation == 1) {
            return "Factory_Wheelhighres_square_90deg-80px.png";
        } else if (this.wheel_orientation == 1) {
            return "Factory_Wheelhighres_square_180deg-80px.png";
        } else {
            return "Factory_Wheelhighres_square_270deg-80px.png";
        }
    }
}