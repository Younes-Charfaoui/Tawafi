package me.mxcsyounes.tawaf.models;

import android.graphics.PointF;

public class Point2D {

    private float x, y;

    public Point2D(float x, float y) {
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

    public double distanceToPoint(Point2D point2D) {
        return Math.sqrt(this.square(this.getX() - point2D.getX()) +
                this.square(this.getY() - point2D.getY()));
    }

    private double square(double number) {
        return number * number;
    }
}