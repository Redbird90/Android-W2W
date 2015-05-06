package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

import java.util.Random;

/**
 * Created by James on 4/16/2015.
 */
public class HammerEnemy extends falling_enemy {

    private final int hammer_orientation;
    private final Rectangle bounds2;
    private Random randGen = new Random();

    public HammerEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(0f, 6.5f);
        this.hammer_orientation = randGen.nextInt(2);
        this.bounds = new Rectangle(x, y+80, 62, 20);
        if (this.hammer_orientation == 0) {
            this.bounds2 = new Rectangle(x+25, y, 33, 79);
        } else {
            this.bounds2 = new Rectangle(x+4, y, 33, 79);
        }
        this.bounds_tsil.add(this.bounds);
        this.bounds_tsil.add(this.bounds2);
    }

    public String getImageName() {
        if (this.hammer_orientation == 0) {
            return "Factory_Hammerhighres-100pxh.png";
        } else {
            return "Factory_Hammerhighres_reverse-100pxh.png";
        }
    }
}
