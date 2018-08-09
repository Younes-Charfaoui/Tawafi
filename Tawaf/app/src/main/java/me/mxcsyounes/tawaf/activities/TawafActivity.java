package me.mxcsyounes.tawaf.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import me.mxcsyounes.tawaf.R;
import me.mxcsyounes.tawaf.models.Line2D;
import me.mxcsyounes.tawaf.models.Point2D;

public class TawafActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

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
    private static final String TAG = "Tawaf";

    private int counter = 0;
    private TextView mCounterTextView, mDouaaTextView;

    private GoogleApiClient mGoogleClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tawaf);

        mGoogleClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mCounterTextView = findViewById(R.id.number_counter_text_view);
        mDouaaTextView = findViewById(R.id.douaa_text_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        findViewById(R.id.start_counter_button).setOnClickListener(v -> {
            counter++;

            mCounterTextView.setText(String.valueOf(counter));
            switch (counter) {
                case 1:
                    mDouaaTextView.setText("لا إله إلا اللهُ وحده لا شريك له، له الملكُ وله الحمد وهو على كل شيءٍ قديرٌ");
                    break;
                case 2:
                    mDouaaTextView.setText("اللهم إنا نسألك الهدى والتقى والعفاف والغنى، اللهم أتِ نفسي تقواها وزكها أنت خير من زكاها، “رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الْآخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّار.");
                    break;
                case 3:
                    mDouaaTextView.setText("ربنا آتنا في الدنيا حسنة وفي الآخرة حسنة وقنا عذاب النار");
                    break;
                case 4:
                    mDouaaTextView.setText("لا إله إلا اللهُ وحده لا شريك له، له الملكُ وله الحمد وهو على كل شيءٍ قديرٌ، لا إله إلا اللهُ وحده، أنجز وعدَه، ونصر عبدَه، وهزم الأحزابَ وحدَه");
                    break;
                case 5:
                    mDouaaTextView.setText("اللهم إني فقير، وإني خائف مستجير، فلا تغير جسمي، ولا تبدل اسمي سائلك فقيرك، مسكينك ببابك، فتصدق عليه بالجنة، اللهم إن هذا البيت بيتك، والحرم حرمك، والعبد عبدك، وهذا مقام العائذ بك، المستجير بك من النار، فأعتقني ووالدي وأهلي وولدي وإخواني المؤمنين من النار، يا جواد يا كريم");
                    break;
                case 6:
                    mDouaaTextView.setText("بسم الله و الله أكبر");
                    break;
                case 7:
                    mDouaaTextView.setText("ربنا آتنا في الدنيا حسنة وفي الآخرة حسنة وقنا عذاب النار");
                    break;
            }
        });
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            // public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 55);
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleClient.disconnect();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, "onLocationChanged: " + location.toString());
    }
}
