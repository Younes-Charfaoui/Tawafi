package me.mxcsyounes.tawaf;

import android.location.Location;

import org.junit.Assert;
import org.junit.Test;

import me.mxcsyounes.tawaf.models.Line2D;
import me.mxcsyounes.tawaf.models.Point2D;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        float CENTER_LAT = 21.422508f;
        float CENTER_LON = 39.826194f;

        float OneLa = 21.42253432f;
        float OneLo = 39.82613167f;

        float TwoLa = 21.42248148f;
        float TwoLo = 39.82625793f;

        float ROKN_YAMANI_LAT = 21.422397f;
        float ROKN_YAMANI_LON = 39.826144f;

        float HAJAR_ASWAD_LAT = 21.422562f;
        float HAJAR_ASWAD_LON = 39.826300f;


        Point2D centerPoint = new Point2D(CENTER_LAT, CENTER_LON);
        Point2D roknYamaniPoint = new Point2D(ROKN_YAMANI_LAT, ROKN_YAMANI_LON);
        Point2D hajarAswadPoint = new Point2D(HAJAR_ASWAD_LAT, HAJAR_ASWAD_LON);

        Point2D p1 = new Point2D(OneLa, OneLo);
        Point2D p2 = new Point2D(TwoLa, TwoLo);


        Line2D line2D = new Line2D(p1, p2);

        // location of my pc
        float M_LAT = 35.363674f;
        float M_LON = 1.312973f;
        Point2D myLocation = new Point2D(M_LAT, M_LON);

        // test points

        //  above the hajar line
        Point2D at1 = new Point2D(21.42295839f, 39.82619624f);
        Point2D at2 = new Point2D(21.42294591f, 39.82670586f);
        Point2D at3 = new Point2D(21.42299834f, 39.82589315f);

        // below the hajar line
        Point2D bt1 = new Point2D(21.42216438f, 39.82614528f);
        Point2D bt2 = new Point2D(21.42228173f, 39.82655834f);
        Point2D bt3 = new Point2D(21.42237162f, 39.82563834f);

        System.out.println(Math.signum(line2D.distanceToPoint(at1)));
        System.out.println(Math.signum(line2D.distanceToPoint(at3)));
        System.out.println(Math.signum(line2D.distanceToPoint(at2)));
        System.out.println(Math.signum(line2D.distanceToPoint(bt1)));
        System.out.println(Math.signum(line2D.distanceToPoint(bt2)));
        System.out.println(Math.signum(line2D.distanceToPoint(bt3)));


    }
}