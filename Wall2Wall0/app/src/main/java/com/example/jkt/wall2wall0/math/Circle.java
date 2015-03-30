package com.example.jkt.wall2wall0.math;

import com.example.jkt.wall2wall0.math.Vector2;

public class Circle {
    public final Vector2 center = new Vector2();
    public float radius;

    public Circle(float x, float y, float radius) {
        this.center.set(x,y);
        this.radius = radius;
    }
}
