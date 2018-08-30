package me.mxcsyounes.tawaf.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_tawaf.*
import me.mxcsyounes.tawaf.DataProvider
import me.mxcsyounes.tawaf.R
import me.mxcsyounes.tawaf.models.Point2D
import me.mxcsyounes.tawaf.models.State

class TawafActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Tawaf"
    }

    //counter of the rounds
    private var mCounter = 0

    //if user tap the start button
    private var mStart = false

    //previous state of the taif.
    private var mPreviousState = 33

    //object to get the state of the taif.
    private var mGlobalState: State? = null

    //core object for getting the location.
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mLocationCallback: LocationCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tawaf)

        mGlobalState = State()

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        mLocationCallback = object : LocationCallback() {

            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult == null) {
                    return
                }
                for (location in locationResult.locations) {
                    Log.i(TAG, "onLocationResult: " + location.toString())
                    if (mStart)
                        checkLocation(location)
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        start_counter_button.setOnClickListener {
            val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
            if (manager != null)
                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    turnOnGpsDialog()
                    return@setOnClickListener
                }

            start_counter_button.visibility = View.GONE
            ad3iya_text_view.visibility = View.VISIBLE

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return@setOnClickListener
            }
            mFusedLocationClient?.lastLocation?.addOnSuccessListener(this, { location ->
                val point2D = Point2D(location.latitude.toString().toFloat(), location.longitude.toString().toFloat())
                val state = State(Math.signum(DataProvider.hajarLine.distanceToPoint(point2D)).toInt(),
                        (Math.signum(DataProvider.roknLine.distanceToPoint(point2D)).toInt()))

                mPreviousState = state.getState()
            })
            mStart = true
        }
    }

    override fun onStart() {
        super.onStart()
        val mLocationRequest = LocationRequest()
        mLocationRequest.interval = 3000
        mLocationRequest.fastestInterval = 5000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    55)
            return
        }
        mFusedLocationClient?.requestLocationUpdates(mLocationRequest, mLocationCallback, null)
    }

    override fun onStop() {
        super.onStop()
        mFusedLocationClient?.removeLocationUpdates(mLocationCallback)
    }

    //helper method to let user activate the GPS
    private fun turnOnGpsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("You have to enable GPS ?")
        builder.setCancelable(false).setPositiveButton("Yes", { _, _ ->
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }).setNegativeButton("No", { dialogInterface, _ -> dialogInterface.cancel() }).show()
    }

    // main method that do all the work.
    private fun checkLocation(location: Location) {
        Log.i(TAG, "checkLocation: " + location.toString())

        if (mPreviousState == 33) return

        val point2D = Point2D(location.latitude.toString().toFloat(),
                location.longitude.toString().toFloat())

        mGlobalState?.stateHajar = (Math.signum(DataProvider.hajarLine.distanceToPoint(point2D)).toInt())
        mGlobalState?.stateRokn = (Math.signum(DataProvider.roknLine.distanceToPoint(point2D)).toInt())

        when (mGlobalState?.getState()) {
            1 -> {
                if (mPreviousState == 4) {
                    mCounter++
                    number_counter_text_view.text = mCounter.toString()
                    mPreviousState = 1
                    douaa_text_view.text = "بسم الله و الله أكبر"
                } else if (mPreviousState != 1) showSomethingWrong()
                // TODO: 20-Aug-18 is between hajar and ismail top
            }

            2 -> {
                if (mPreviousState == 1) mPreviousState = 2
                else if (mPreviousState != 2) showSomethingWrong()
                // TODO: 20-Aug-18 is ismail top and ismail bottom
            }

            3 -> {
                if (mPreviousState == 2) mPreviousState = 3
                else if (mPreviousState != 3) showSomethingWrong()
                // TODO: 20-Aug-18 is ismail bottom and rokn yamani
            }

            4 -> {
                if (mPreviousState == 3) mPreviousState = 4
                else if (mPreviousState != 4) showSomethingWrong()
                douaa_text_view.text = "رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الْآخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّار."
                // TODO: 20-Aug-18 is rokn yamani and hajar
            }
        }
    }

    private fun showSomethingWrong() =
            Toast.makeText(this, R.string.somthing_wrong_happend, Toast.LENGTH_SHORT).show()
}
