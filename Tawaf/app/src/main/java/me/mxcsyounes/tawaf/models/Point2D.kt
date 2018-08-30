package me.mxcsyounes.tawaf.models


data class Point2D(val x: Float, val y: Float) {

    // calculate distance to the point without abs value
    fun distanceToLine(line: Line2D): Double {
        val nominator = line.a * this.x + line.b * this.y + line.c
        val denominator = Math.sqrt((line.a * line.a + line.b * line.b).toDouble())
        return nominator / denominator
    }

    // distance from point to point
    fun distanceToPoint(point2D: Point2D): Double {
        return Math.sqrt(this.square((x - point2D.x).toDouble()) +
                this.square((y - point2D.y).toDouble()))
    }

    // simple square function
    private fun square(number: Double) = number * number

}