package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Rectangle;
import com.example.jkt.wall2wall0.math.Vector2;

/**
 * Created by James on 4/16/2015.
 */
public class DroneEnemy extends falling_enemy {

    private boolean start_moving_left;
    private int num_of_updates = 0;
    private boolean stop_movement = false;
    private float prev_x_velocity;

    public DroneEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(2.5f, 5f);
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void update_enemy() {
        this.num_of_updates += 1;
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
        if (!stop_movement && this.num_of_updates % 20 == 0) {
            stop_movement = true;
            prev_x_velocity = this.velocity.getX();
            this.velocity.set(0f, 0f);
        }
        if (stop_movement && (num_of_updates == (num_of_updates%20)+21)) {
            stop_movement = false;
            this.velocity.set(prev_x_velocity, 5f);
        }
    }
}
