package me.mxcsyounes.tawaf;

import me.mxcsyounes.tawaf.models.Line2D;
import me.mxcsyounes.tawaf.models.Point2D;

public class DataProvider {


    private static final float HIJR_ISMAIL_TOP_LAT = 21.42256366f;
    private static final float HIJR_ISMAIL_TOP_LON = 39.82620367f;

    private static final float HIJR_ISMAIL_BOTTOM_LAT = 21.42251809f;
    private static final float HIJR_ISMAIL_BOTTOM_LON = 39.82612991f;

    private static final float ROKN_YAMANI_LAT = 21.42243943f;
    private static final float ROKN_YAMANI_LON = 39.82618757f;

    private static final float HAJAR_ASWAD_LAT = 21.42248251f;
    private static final float HAJAR_ASWAD_LON = 39.82625463f;

    private static final Point2D hijrIsmailTopPoint = new Point2D(HIJR_ISMAIL_TOP_LAT, HIJR_ISMAIL_TOP_LON);
    private static final Point2D hijrIsmailBottomPoint = new Point2D(HIJR_ISMAIL_BOTTOM_LAT, HIJR_ISMAIL_BOTTOM_LON);
    private static final Point2D roknYamaniPoint = new Point2D(ROKN_YAMANI_LAT, ROKN_YAMANI_LON);
    private static final Point2D hajarAswadPoint = new Point2D(HAJAR_ASWAD_LAT, HAJAR_ASWAD_LON);

    private static final Line2D hajarLine = new Line2D(hijrIsmailBottomPoint, hajarAswadPoint);
    private static final Line2D RoknLine = new Line2D(hijrIsmailTopPoint, roknYamaniPoint);

    public static Line2D getHajarLine() {
        return hajarLine;
    }

    public static Line2D getRoknLine() {
        return RoknLine;
    }
}
