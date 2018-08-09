package me.mxcsyounes.tawaf;

import android.graphics.PointF;
import android.location.Location;

import org.junit.Assert;
import org.junit.Test;

import me.mxcsyounes.tawaf.models.Line2D;
import me.mxcsyounes.tawaf.models.Point2D;

import static org.junit.Assert.*;


public class LineAndPointTests {


    @Test
    public void testPointAndLineFunction() {
        Point2D zero = new Point2D(0, 0);
        Point2D one = new Point2D(1, 1);
        Point2D two = new Point2D(-1, 1);

        assertEquals(2, one.distanceToPoint(two), -1);

        Line2D line2DOne = new Line2D(zero, two);
        assertEquals(0, line2DOne.distanceToPoint(two), 1);
        assertEquals(0, two.distanceToLine(line2DOne), 1);

        Point2D below = new Point2D(0, -2);
        Point2D above = new Point2D(0, 2);

        if (Math.signum(line2DOne.getCoefficientOfGuid()) > 0) {
            assertEquals(Math.sqrt(2), below.distanceToLine(line2DOne), 1);
            assertEquals(-Math.sqrt(2), above.distanceToLine(line2DOne), 1);

            assertEquals(Math.sqrt(2), line2DOne.distanceToPoint(below), 1);
            assertEquals(-Math.sqrt(2), line2DOne.distanceToPoint(above), 1);

            assertEquals(1, Math.signum(line2DOne.distanceToPoint(below)), -1);
            assertEquals(-1, Math.signum(line2DOne.distanceToPoint(above)), -1);
        } else {
            assertEquals(-Math.sqrt(2), below.distanceToLine(line2DOne), 1);
            assertEquals(Math.sqrt(2), above.distanceToLine(line2DOne), 1);

            assertEquals(-Math.sqrt(2), line2DOne.distanceToPoint(below), 1);
            assertEquals(Math.sqrt(2), line2DOne.distanceToPoint(above), 1);

            assertEquals(-1, Math.signum(line2DOne.distanceToPoint(below)), -1);
            assertEquals(1, Math.signum(line2DOne.distanceToPoint(above)), -1);
        }


    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Test
    public void testingKaabaThings() {
        //  above the hajar line
        Point2D at1 = new Point2D(21.42295839f, 39.82619624f);
        Point2D at2 = new Point2D(21.42294591f, 39.82670586f);
        Point2D at3 = new Point2D(21.42299834f, 39.82589315f);

        // below the hajar line
        Point2D bt1 = new Point2D(21.42216438f, 39.82614528f);
        Point2D bt2 = new Point2D(21.42228173f, 39.82655834f);
        Point2D bt3 = new Point2D(21.42237162f, 39.82563834f);

        float HIJR_ISMAIL_TOP_LAT = 21.42256366f;
        float HIJR_ISMAIL_TOP_LON = 39.82620367f;

        float HIJR_ISMAIL_BOTTOM_LAT = 21.42251809f;
        float HIJR_ISMAIL_BOTTOM_LON = 39.82612991f;

        float ROKN_YAMANI_LAT = 21.42243943f;
        float ROKN_YAMANI_LON = 39.82618757f;

        float HAJAR_ASWAD_LAT = 21.42248251f;
        float HAJAR_ASWAD_LON = 39.82625463f;

        Point2D hijrIsmailTopPoint = new Point2D(HIJR_ISMAIL_TOP_LAT, HIJR_ISMAIL_TOP_LON);
        Point2D hijrIsmailBottomPoint = new Point2D(HIJR_ISMAIL_BOTTOM_LAT, HIJR_ISMAIL_BOTTOM_LON);
        Point2D roknYamaniPoint = new Point2D(ROKN_YAMANI_LAT, ROKN_YAMANI_LON);
        Point2D hajarAswadPoint = new Point2D(HAJAR_ASWAD_LAT, HAJAR_ASWAD_LON);

        Line2D hajarLine = new Line2D(hijrIsmailBottomPoint, hajarAswadPoint);
        Line2D RoknLine = new Line2D(hijrIsmailTopPoint, roknYamaniPoint);

        if (Math.signum(hajarLine.getCoefficientOfGuid()) > 0) {

            assertEquals(1, Math.signum(hajarLine.distanceToPoint(bt1)), -1);
            assertEquals(1, Math.signum(hajarLine.distanceToPoint(bt2)), -1);
            assertEquals(1, Math.signum(hajarLine.distanceToPoint(bt3)), -1);
            assertEquals(-1, Math.signum(hajarLine.distanceToPoint(at1)), -1);
            assertEquals(-1, Math.signum(hajarLine.distanceToPoint(at2)), -1);
            assertEquals(-1, Math.signum(hajarLine.distanceToPoint(at3)), -1);
        } else {
            assertEquals(-1, Math.signum(hajarLine.distanceToPoint(bt1)), -1);
            assertEquals(-1, Math.signum(hajarLine.distanceToPoint(bt2)), -1);
            assertEquals(-1, Math.signum(hajarLine.distanceToPoint(bt3)), -1);
            assertEquals(1, Math.signum(hajarLine.distanceToPoint(at1)), -1);
            assertEquals(1, Math.signum(hajarLine.distanceToPoint(at2)), -1);
            assertEquals(1, Math.signum(hajarLine.distanceToPoint(at3)), -1);
        }
    }
}
