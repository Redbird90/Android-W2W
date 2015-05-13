package com.example.jkt.wall2wall0;

/**
 * Created by JDK on 3/29/2015.
 */

import android.util.Log;

import com.example.jkt.wall2wall0.math.Circle;
import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Shape;
import com.example.jkt.wall2wall0.math.Vector2;

import java.util.ArrayList;

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
    private final int enemy_num;
    public ArrayList<Shape> bounds_tsil = new ArrayList();
    public Shape bounds;

    public boolean player_jumping = false;

    public falling_enemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height);
        this.enemy_num = enemy_num;
        this.x_pos = x;
        this.y_pos = y;
/*        if (this.enemy_num == 7) {
            this.bounds = new Circle(30, y, (width / 2));
        } else {
            this.bounds = new Rectangle(x, y, width, height);
        }*/
    }

    public void update_enemy() {
        if (this.player_jumping) {
            this.y_pos += (this.velocity.getY() + 1.5f);
            this.x_pos += this.velocity.getX();
        } else {
            this.y_pos += this.velocity.getY();
            this.x_pos += this.velocity.getX();
        }
        this.update_bounds();
    }

    public int getEnemy_num() {
        return this.enemy_num;
    }

    public void update_bounds() {
        this.bounds.setLowerLeft(this.x_pos, this.y_pos);
    }

    public void setPlayer_jumping(boolean player_jumping) {
        this.player_jumping = player_jumping;
    }

    public float getX_pos() {
        return this.x_pos;
    }

    public void setX_pos(float x_pos) {
        this.x_pos = x_pos;
    }

    public float getY_pos() {
        return this.y_pos;
    }

    public void setY_pos(float y_pos) {
        this.y_pos = y_pos;
    }

}
