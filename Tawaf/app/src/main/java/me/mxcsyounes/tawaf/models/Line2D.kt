package me.mxcsyounes.tawaf.models;

class Line2D {

    val a: Float
    val b: Float
    val c: Float

    constructor(a: Float, b: Float, c: Float) {
        this.a = a
        this.b = b
        this.c = c
    }

    constructor(pointOne: Point2D, pointTwo: Point2D) {
        this.a = pointTwo.y - pointOne.y;
        this.b = pointOne.x - pointTwo.x
        this.c = -(a * pointOne.x + b * pointOne.y)
    }

    fun distanceToPoint(point: Point2D): Double {
        val nominator = a * point.x + b * point.y + c
        val denominator = Math.sqrt((a * a + b * b).toDouble());
        return nominator / denominator
    }

    fun getCoefficientOfGuid() = a / b
}
