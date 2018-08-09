package me.mxcsyounes.tawaf.activities;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.mxcsyounes.tawaf.R;
import me.mxcsyounes.tawaf.models.Line2D;
import me.mxcsyounes.tawaf.models.Point2D;

public class TawafActivity extends AppCompatActivity {

    private static final float CENTER_LAT = 21.422508f;
    private static final float CENTER_LON = 39.826194f;

    private static final float ROKN_YAMANI_LAT = 21.422397f;
    private static final float ROKN_YAMANI_LON = 39.826144f;

    private static final float HAJAR_ASWAD_LAT = 21.422562f;
    private static final float HAJAR_ASWAD_LON = 39.826300f;

    private static final Point2D centerPoint = new Point2D(CENTER_LAT, CENTER_LON);
    private static final Point2D roknYamaniPoint = new Point2D(ROKN_YAMANI_LAT, ROKN_YAMANI_LON);
    private static final Point2D hajarAswadPoint = new Point2D(HAJAR_ASWAD_LAT, HAJAR_ASWAD_LON);

    private static final Line2D hajarLine = new Line2D(centerPoint, hajarAswadPoint);
    private static final Line2D RoknLine = new Line2D(centerPoint, roknYamaniPoint);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tawaf);
    }


}
