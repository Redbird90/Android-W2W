package com.example.jkt.wall2wall0;

/**
 * Created by JDK on 3/29/2015.
 */

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

/**         Dyn constructor:
 *         velocity = new Vector2();
             accel = new Vector2();
             GameObj constructor:
             this.position = new Vector2(x,y);
             this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
 */

public class falling_enemy extends DynamicGameObject {

    public float x_pos;
    public float y_pos;
    public float width;
    public float height;

    public falling_enemy(float x, float y, float width, float height) {
        super(x, y, width, height);
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
