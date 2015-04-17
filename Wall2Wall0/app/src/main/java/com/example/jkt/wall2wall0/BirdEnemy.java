package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by James on 4/16/2015.
 */
public class BirdEnemy extends falling_enemy {

    public Vector2 velocity = new Vector2(3f, 4.5f);
    private boolean start_moving_left;

    public BirdEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
    }

    @Override
    public void update_enemy() {
        if (this.player_jumping) {
            this.y_pos += (velocity.getY() + 1.5f);
            this.x_pos += velocity.getX();
            this.update_bounds();
        } else {
            this.y_pos += velocity.getY();
            this.x_pos += velocity.getX();
            this.update_bounds();
        }
        if (this.velocity.getX() == 2f) {
            this.start_moving_left = true;
        } else if (this.velocity.getX() == -2f) {
            this.start_moving_left = false;
        }
        if (this.start_moving_left) {
            float updated_x1 = this.velocity.getX() - 0.25f;
            this.velocity.set(updated_x1, this.velocity.getY());
        } else if (!this.start_moving_left) {
            float updated_x2 = this.velocity.getX() + 0.25f;
            this.velocity.set(updated_x2, this.velocity.getY());
        }
    }
}
