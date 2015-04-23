package com.example.jkt.wall2wall0;

/**
 * Created by JDK on 4/21/2015.
 */
public class SpawnEvent {

    int enemy_type;
    int enemy_x_location;
    int enemy_spawn_time;

    public SpawnEvent(int type, int location, int time) {
        this.enemy_type = type;
        this.enemy_x_location = location;
        this.enemy_spawn_time = time;
    }

}
