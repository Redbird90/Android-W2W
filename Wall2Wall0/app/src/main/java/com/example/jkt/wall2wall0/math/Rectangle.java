package com.example.jkt.wall2wall0.math;

public class Rectangle {
    public final Vector2 lowerLeft;
    public float width, height;

    public Vector2 getLowerLeft() {
        return lowerLeft;
    }

    public void setLowerLeft(float x, float y) {
        this.lowerLeft.set(x, y);
    }

    public Rectangle(float x, float y, float width, float height) {
        this.lowerLeft = new Vector2(x,y);
        this.width = width;
        this.height = height;
    }
}
