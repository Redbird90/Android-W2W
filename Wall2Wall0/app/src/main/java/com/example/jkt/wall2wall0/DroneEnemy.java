package com.example.jkt.wall2wall0;

import android.util.Log;

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
    private int start_at_update;
    private int divisor;
    private boolean shakebool;

    public DroneEnemy(float x, float y, float width, float height, int enemy_num) {
        super(x, y, width, height, enemy_num);
        this.velocity = new Vector2(2.5f, 3f);
        this.bounds = new Rectangle(x, y, width, height);
        this.bounds_tsil.add(this.bounds);
        this.divisor = 30;
    }

    @Override
    public void update_enemy() {
        this.num_of_updates += 1;
        Log.i("DroneEnemy", String.valueOf(this.num_of_updates));
        Log.i("DroneEnemy", String.valueOf(this.velocity.getX()+","+this.velocity.getY()));
        if (this.player_jumping) {
            this.y_pos += (velocity.getY() + 1.5f);
            this.x_pos += velocity.getX();
            this.update_bounds();
        } else {
            this.y_pos += velocity.getY();
            this.x_pos += velocity.getX();
            this.update_bounds();
        }
        if (this.velocity.getX() == 3f) {
            this.start_moving_left = true;
        } else if (this.velocity.getX() == -3f) {
            this.start_moving_left = false;
        }
        if (this.start_moving_left && !stop_movement) {
            float updated_x1 = this.velocity.getX() - 0.25f;
            this.velocity.set(updated_x1, this.velocity.getY());
        } else if (!this.start_moving_left && !stop_movement) {
            float updated_x2 = this.velocity.getX() + 0.25f;
            this.velocity.set(updated_x2, this.velocity.getY());
        }
        if (!stop_movement && this.num_of_updates % this.divisor == 0) {
            this.stop_movement = true;
            this.prev_x_velocity = this.velocity.getX();
            this.velocity.set(0f, 0f);
            this.start_at_update = this.num_of_updates + 20;
            this.divisor = this.start_at_update + 30;
        }
        if (this.stop_movement && (this.num_of_updates == this.start_at_update)) {
            this.stop_movement = false;
            this.velocity.set(prev_x_velocity, 3f);
        } else if (this.stop_movement) {
            if (this.shakebool) {
                this.x_pos += 1f;
                this.shakebool = false;
            } else {
                this.x_pos -= 1f;
                this.shakebool = true;
            }
            
        }
        Log.i("DroneEnemy", String.valueOf(this.velocity.getX()+","+this.velocity.getY()));
    }

    public int getImageName() {
        // NOT NEEDED
        return 0;
        //return "Factory_Dronehighres-95px.png";
    }
}
