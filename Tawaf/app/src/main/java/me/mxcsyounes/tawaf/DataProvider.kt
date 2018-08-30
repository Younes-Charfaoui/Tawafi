package me.mxcsyounes.tawaf

import me.mxcsyounes.tawaf.models.Line2D
import me.mxcsyounes.tawaf.models.Point2D

object DataProvider {

    private const val HIJR_ISMAIL_TOP_LAT = 21.42256366f
    private const val HIJR_ISMAIL_TOP_LON = 39.82620367f

    private const val HIJR_ISMAIL_BOTTOM_LAT = 21.42251809f
    private const val HIJR_ISMAIL_BOTTOM_LON = 39.82612991f

    private const val ROKN_YAMANI_LAT = 21.42243943f
    private const val ROKN_YAMANI_LON = 39.82618757f

    private const val HAJAR_ASWAD_LAT = 21.42248251f
    private const val HAJAR_ASWAD_LON = 39.82625463f

    private val hijrIsmailTopPoint = Point2D(HIJR_ISMAIL_TOP_LAT, HIJR_ISMAIL_TOP_LON)
    private val hijrIsmailBottomPoint = Point2D(HIJR_ISMAIL_BOTTOM_LAT, HIJR_ISMAIL_BOTTOM_LON)
    private val roknYamaniPoint = Point2D(ROKN_YAMANI_LAT, ROKN_YAMANI_LON)
    private val hajarAswadPoint = Point2D(HAJAR_ASWAD_LAT, HAJAR_ASWAD_LON)

    val hajarLine = Line2D(hijrIsmailBottomPoint, hajarAswadPoint)
    val roknLine = Line2D(hijrIsmailTopPoint, roknYamaniPoint)
}
