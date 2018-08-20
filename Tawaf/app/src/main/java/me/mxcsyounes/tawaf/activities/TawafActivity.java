package me.mxcsyounes.tawaf.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import me.mxcsyounes.tawaf.DataProvider;
import me.mxcsyounes.tawaf.R;
import me.mxcsyounes.tawaf.models.Point2D;
import me.mxcsyounes.tawaf.models.State;

public class TawafActivity extends AppCompatActivity {

    private static final String TAG = "Tawaf";

    //counter of the rounds
    private int mCounter = 0;

    //if user tap the start button
    private boolean mStart;

    //previous state of the taif.
    private int mPreviousState;

    //object to get the state of the taif.
    private State mGlobalState;

    //text of the round and the ad3iya.
    private TextView mCounterTextView, mDouaaTextView;

    //core object for getting the location.
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tawaf);
        //some basic initialization.
        mGlobalState = new State();
        mPreviousState = 33;
        mStart = false;

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    Log.i(TAG, "onLocationResult: " + location.toString());
                    if (mStart)
                        checkLocation(location);
                }
            }
        };

        mCounterTextView = findViewById(R.id.number_counter_text_view);
        mDouaaTextView = findViewById(R.id.douaa_text_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        findViewById(R.id.start_counter_button).setOnClickListener(view -> {
            final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (manager != null)
                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    turnOnGpsDialog();
                    return;
                }

            view.setVisibility(View.GONE);
            findViewById(R.id.ad3iya_text_view).setVisibility(View.VISIBLE);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mFusedLocationClient.getLastLocation().
                    addOnSuccessListener(this, location -> {
                        Point2D point2D = new Point2D(Float.parseFloat(Double.toString(location.getLatitude())),
                                Float.parseFloat(Double.toString(location.getLongitude())));
                        State state = new State((int) Math.signum(DataProvider.getHajarLine().distanceToPoint(point2D)),
                                (int) Math.signum(DataProvider.getRoknLine().distanceToPoint(point2D)));

                        mPreviousState = state.getState();
                    });
            mStart = true;

//            mCounterTextView.setText(String.valueOf(mCounter));
//            switch (mCounter) {
//                case 1:
//                    mDouaaTextView.setText("لا إله إلا اللهُ وحده لا شريك له، له الملكُ وله الحمد وهو على كل شيءٍ قديرٌ");
//                    break;
//                case 2:
//                    mDouaaTextView.setText("اللهم إنا نسألك الهدى والتقى والعفاف والغنى، اللهم أتِ نفسي تقواها وزكها أنت خير من زكاها، “رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الْآخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّار.");
//                    break;
//                case 3:
//                    mDouaaTextView.setText("ربنا آتنا في الدنيا حسنة وفي الآخرة حسنة وقنا عذاب النار");
//                    break;
//                case 4:
//                    mDouaaTextView.setText("لا إله إلا اللهُ وحده لا شريك له، له الملكُ وله الحمد وهو على كل شيءٍ قديرٌ، لا إله إلا اللهُ وحده، أنجز وعدَه، ونصر عبدَه، وهزم الأحزابَ وحدَه");
//                    break;
//                case 5:
//                    mDouaaTextView.setText("اللهم إني فقير، وإني خائف مستجير، فلا تغير جسمي، ولا تبدل اسمي سائلك فقيرك، مسكينك ببابك، فتصدق عليه بالجنة، اللهم إن هذا البيت بيتك، والحرم حرمك، والعبد عبدك، وهذا مقام العائذ بك، المستجير بك من النار، فأعتقني ووالدي وأهلي وولدي وإخواني المؤمنين من النار، يا جواد يا كريم");
//                    break;
//                case 6:
//                    mDouaaTextView.setText("بسم الله و الله أكبر");
//                    break;
//                case 7:
//                    mDouaaTextView.setText("ربنا آتنا في الدنيا حسنة وفي الآخرة حسنة وقنا عذاب النار");
//                    break;
//            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(3000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 55);
            return;
        }
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    //helper method to let user activate the GPS
    private void turnOnGpsDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You have to enable GPS ?");
        builder.setCancelable(false).setPositiveButton("Yes", (dialogInterface, i) ->
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel())
                .show();
    }

    // main method that do all the work.
    private void checkLocation(Location location) {
        Log.i(TAG, "checkLocation: " + location.toString());

        if (mPreviousState == 33) return;
        Point2D point2D = new Point2D(Float.parseFloat(Double.toString(location.getLatitude())),
                Float.parseFloat(Double.toString(location.getLongitude())));

        mGlobalState.setStateHajar((int) Math.signum(DataProvider.getHajarLine().distanceToPoint(point2D)));
        mGlobalState.setStateRokn((int) Math.signum(DataProvider.getRoknLine().distanceToPoint(point2D)));

        switch (mGlobalState.getState()) {
            case 1:
                if (mPreviousState == 4) {
                    mCounter++;
                    mCounterTextView.setText(String.valueOf(mCounter));
                    mPreviousState = 1;
                    mDouaaTextView.setText("بسم الله و الله أكبر");
                } else if (mPreviousState != 1)
                    Toast.makeText(this, R.string.somthing_wrong_happend, Toast.LENGTH_SHORT).show();
                // TODO: 20-Aug-18 is between hajar and ismail top
                break;
            case 2:
                if (mPreviousState == 1) mPreviousState = 2;
                else if (mPreviousState != 2)
                    Toast.makeText(this, R.string.somthing_wrong_happend, Toast.LENGTH_SHORT).show();
                // TODO: 20-Aug-18 is ismail top and ismail bottom
                break;
            case 3:

                if (mPreviousState == 2) mPreviousState = 3;
                else if (mPreviousState != 3)
                    Toast.makeText(this, R.string.somthing_wrong_happend, Toast.LENGTH_SHORT).show();
                // TODO: 20-Aug-18 is ismail bottom and rokn yamani
                break;
            case 4:
                if (mPreviousState == 3) mPreviousState = 4;
                else if (mPreviousState != 4)
                    Toast.makeText(this, R.string.somthing_wrong_happend, Toast.LENGTH_SHORT).show();
                mDouaaTextView.setText("رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الْآخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّار.");

                // TODO: 20-Aug-18 is rokn yamani and hajar
                break;
        }
    }


}
