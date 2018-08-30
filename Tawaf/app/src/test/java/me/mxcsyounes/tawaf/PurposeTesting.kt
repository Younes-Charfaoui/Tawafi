package me.mxcsyounes.tawaf

import org.junit.Assert
import org.junit.Test

import me.mxcsyounes.tawaf.models.Line2D
import me.mxcsyounes.tawaf.models.Point2D

class PurposeTesting {

    @Test
    fun aboveHajarLine() {

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

        val abHabR = Point2D(21.42272173f, 39.82637675f)
        val abHbeR = Point2D(21.42276168f, 39.82601196f)
        val beHbeR = Point2D(21.42228976f, 39.82594223f)
        val beHabR = Point2D(21.42230474f, 39.82648571f)



        Assert.assertEquals(1.0, Math.signum(hajarLine.distanceToPoint(abHabR)), -1.0)
        Assert.assertEquals(1.0, Math.signum(RoknLine.distanceToPoint(abHabR)), -1.0)

        Assert.assertEquals(1.0, Math.signum(hajarLine.distanceToPoint(abHbeR)), -1.0)
        Assert.assertEquals(-1.0, Math.signum(RoknLine.distanceToPoint(abHbeR)), -1.0)

        Assert.assertEquals(-1.0, Math.signum(hajarLine.distanceToPoint(beHbeR)), -1.0)
        Assert.assertEquals(-1.0, Math.signum(RoknLine.distanceToPoint(beHbeR)), -1.0)

        Assert.assertEquals(-1.0, Math.signum(hajarLine.distanceToPoint(beHabR)), -1.0)
        Assert.assertEquals(1.0, Math.signum(RoknLine.distanceToPoint(beHabR)), -1.0)


    }

    @Test
    fun bellowHajarLine() {

    }


}
