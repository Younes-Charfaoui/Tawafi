package me.mxcsyounes.tawaf.activities;

import android.location.Location;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

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

    private int counter = 0;
    private TextView mCounterTextView, mDouaaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tawaf);

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


}
