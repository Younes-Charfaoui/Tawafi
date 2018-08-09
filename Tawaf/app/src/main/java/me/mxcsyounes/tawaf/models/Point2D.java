package me.mxcsyounes.tawaf.models;

public class Point2D {

    private float x, y;

    public Point2D(float y, float x) {
        this.x = x;
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public double distanceToLine(Line2D line) {
        double nominator = line.getA() * this.x + line.getB() * this.y + line.getC();
        double denominator = Math.sqrt(line.getA() * line.getA() + line.getB() * line.getB());
        return nominator / denominator;
    }
}