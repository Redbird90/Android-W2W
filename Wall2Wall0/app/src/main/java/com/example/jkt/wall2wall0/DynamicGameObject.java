package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.math.Vector2;

public class DynamicGameObject extends GameObject {
    public Vector2 velocity;
    public final Vector2 accel;
    
    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }

    public String getImageName() {
        return "DYNGAMEOBJ STRING, OVERRIDE";
    }
}
