package me.mxcsyounes.tawaf

import android.graphics.PointF
import android.location.Location

import org.junit.Assert
import org.junit.Test

import me.mxcsyounes.tawaf.models.Line2D
import me.mxcsyounes.tawaf.models.Point2D

import org.junit.Assert.*


class LineAndPointTests {


    @Test
    fun testPointAndLineFunction() {
        val zero = Point2D(0f, 0f)
        val one = Point2D(1f, 1f)
        val two = Point2D(-1f, 1f)

        assertEquals(2.0, one.distanceToPoint(two), -1.0)

        val line2DOne = Line2D(zero, two)
        assertEquals(0.0, line2DOne.distanceToPoint(two), 1.0)
        assertEquals(0.0, two.distanceToLine(line2DOne), 1.0)

        val below = Point2D(0f, -2f)
        val above = Point2D(0f, 2f)

        if (Math.signum(line2DOne.getCoefficientOfGuid()) > 0) {
            assertEquals(Math.sqrt(2.0), below.distanceToLine(line2DOne), 1.0)
            assertEquals(-Math.sqrt(2.0), above.distanceToLine(line2DOne), 1.0)

            assertEquals(Math.sqrt(2.0), line2DOne.distanceToPoint(below), 1.0)
            assertEquals(-Math.sqrt(2.0), line2DOne.distanceToPoint(above), 1.0)

            assertEquals(1.0, Math.signum(line2DOne.distanceToPoint(below)), -1.0)
            assertEquals(-1.0, Math.signum(line2DOne.distanceToPoint(above)), -1.0)
        } else {
            assertEquals(-Math.sqrt(2.0), below.distanceToLine(line2DOne), 1.0)
            assertEquals(Math.sqrt(2.0), above.distanceToLine(line2DOne), 1.0)

            assertEquals(-Math.sqrt(2.0), line2DOne.distanceToPoint(below), 1.0)
            assertEquals(Math.sqrt(2.0), line2DOne.distanceToPoint(above), 1.0)

            assertEquals(-1.0, Math.signum(line2DOne.distanceToPoint(below)), -1.0)
            assertEquals(1.0, Math.signum(line2DOne.distanceToPoint(above)), -1.0)
        }


    }

    @Test
    fun testingKaabaThings() {
        //  above the hajar line
        val at1 = Point2D(21.42295839f, 39.82619624f)
        val at2 = Point2D(21.42294591f, 39.82670586f)
        val at3 = Point2D(21.42299834f, 39.82589315f)

        // below the hajar line
        val bt1 = Point2D(21.42216438f, 39.82614528f)
        val bt2 = Point2D(21.42228173f, 39.82655834f)
        val bt3 = Point2D(21.42237162f, 39.82563834f)

        val HIJR_ISMAIL_TOP_LAT = 21.42256366f
        val HIJR_ISMAIL_TOP_LON = 39.82620367f

        val HIJR_ISMAIL_BOTTOM_LAT = 21.42251809f
        val HIJR_ISMAIL_BOTTOM_LON = 39.82612991f

        val ROKN_YAMANI_LAT = 21.42243943f
        val ROKN_YAMANI_LON = 39.82618757f

        val HAJAR_ASWAD_LAT = 21.42248251f
        val HAJAR_ASWAD_LON = 39.82625463f

        val hijrIsmailTopPoint = Point2D(HIJR_ISMAIL_TOP_LAT, HIJR_ISMAIL_TOP_LON)
        val hijrIsmailBottomPoint = Point2D(HIJR_ISMAIL_BOTTOM_LAT, HIJR_ISMAIL_BOTTOM_LON)
        val roknYamaniPoint = Point2D(ROKN_YAMANI_LAT, ROKN_YAMANI_LON)
        val hajarAswadPoint = Point2D(HAJAR_ASWAD_LAT, HAJAR_ASWAD_LON)

        val hajarLine = Line2D(hijrIsmailBottomPoint, hajarAswadPoint)
        val RoknLine = Line2D(hijrIsmailTopPoint, roknYamaniPoint)

        if (Math.signum(hajarLine.getCoefficientOfGuid()) > 0) {
            assertEquals(1.0, Math.signum(hajarLine.distanceToPoint(bt1)), -1.0)
            assertEquals(1.0, Math.signum(hajarLine.distanceToPoint(bt2)), -1.0)
            assertEquals(1.0, Math.signum(hajarLine.distanceToPoint(bt3)), -1.0)
            assertEquals(-1.0, Math.signum(hajarLine.distanceToPoint(at1)), -1.0)
            assertEquals(-1.0, Math.signum(hajarLine.distanceToPoint(at2)), -1.0)
            assertEquals(-1.0, Math.signum(hajarLine.distanceToPoint(at3)), -1.0)
        } else {
            // coefficient of hajarLine is below zero
            assertEquals(-1.0, Math.signum(hajarLine.distanceToPoint(bt1)), -1.0)
            assertEquals(-1.0, Math.signum(hajarLine.distanceToPoint(bt2)), -1.0)
            assertEquals(-1.0, Math.signum(hajarLine.distanceToPoint(bt3)), -1.0)
            assertEquals(1.0, Math.signum(hajarLine.distanceToPoint(at1)), -1.0)
            assertEquals(1.0, Math.signum(hajarLine.distanceToPoint(at2)), -1.0)
            assertEquals(1.0, Math.signum(hajarLine.distanceToPoint(at3)), -1.0)
        }
    }
}
