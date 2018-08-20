package me.mxcsyounes.tawaf;

import org.junit.Assert;
import org.junit.Test;

import me.mxcsyounes.tawaf.models.Line2D;
import me.mxcsyounes.tawaf.models.Point2D;

public class PurposeTesting {

    @Test
    public void aboveHajarLine() {

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

        Point2D abHabR = new Point2D(21.42272173f, 39.82637675f);
        Point2D abHbeR = new Point2D(21.42276168f, 39.82601196f);
        Point2D beHbeR = new Point2D(21.42228976f, 39.82594223f);
        Point2D beHabR = new Point2D(21.42230474f, 39.82648571f);



        Assert.assertEquals(1,Math.signum(hajarLine.distanceToPoint(abHabR)),-1);
        Assert.assertEquals(1,Math.signum(RoknLine.distanceToPoint(abHabR)),-1);

        Assert.assertEquals(1,Math.signum(hajarLine.distanceToPoint(abHbeR)),-1);
        Assert.assertEquals(-1,Math.signum(RoknLine.distanceToPoint(abHbeR)),-1);

        Assert.assertEquals(-1,Math.signum(hajarLine.distanceToPoint(beHbeR)),-1);
        Assert.assertEquals(-1,Math.signum(RoknLine.distanceToPoint(beHbeR)),-1);

        Assert.assertEquals(-1,Math.signum(hajarLine.distanceToPoint(beHabR)),-1);
        Assert.assertEquals(1,Math.signum(RoknLine.distanceToPoint(beHabR)),-1);


    }

    @Test
    public void bellowHajarLine() {

    }


}
