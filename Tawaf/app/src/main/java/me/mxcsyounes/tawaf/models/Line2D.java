package me.mxcsyounes.tawaf.models;

public class Line2D {

    private float a, b, c;


    public Line2D(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Line2D(Point2D pointOne, Point2D pointTwo) {
        this.a = pointTwo.getY() - pointOne.getY();
        this.b = pointOne.getX() - pointTwo.getX();
        this.c = -(a * pointOne.getX() + b * pointOne.getY());
    }

    public float getC() {
        return c;
    }

    public float getB() {
        return b;
    }

    public float getA() {
        return a;
    }

    public double distanceToPoint(Point2D point) {
        double nominator = this.getA() * point.getX() + this.getB() * point.getY() + this.getC();
        double denominator = Math.sqrt(this.getA() * this.getA() + this.getB() * this.getB());
        return nominator / denominator;
    }

    public double getCoefficientOfGuid() {
        return -this.a / this.b;
    }

    @Override
    public String toString() {

        return String.valueOf(this.getA()) + "x " + String.valueOf(this.getA()) + "y " + String.valueOf(this.getC());
    }
}
