package net.hklight.dpireader;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get info
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();

        int screenWidth = 0;
        int screenHeight = 0;
        try {
            display.getSize(size);
            display.getRealSize(size);
            screenWidth = size.x;
            screenHeight = size.y;
        } catch (NoSuchMethodError e) {
            screenHeight = display.getHeight();
            screenWidth = display.getWidth();
        }

        // Data
        int densityDpi = (int)(metrics.density * 160f);
        String physicalSize = String.format("%d x %d", screenWidth, screenHeight);
        String logicalSize = String.format("%.4f x %.4f", screenWidth / metrics.scaledDensity, screenHeight / metrics.scaledDensity);
        String scaledDensity = String.format("%.6f", metrics.scaledDensity);

        // output string
        String output = "";
        output += "\n" + "Screen Physical Size(px): " + physicalSize;
        output += "\n" + "Current DPI: " + densityDpi;
        output += "\n" + "ScaledDensity: " + scaledDensity;
        output += "\n" + "Logical Size(dp): " + logicalSize;
        output += "\n" + "Formula: px = dp * (dpi / 160)";

        ((TextView)findViewById(R.id.textview_desc)).setText(output);
    }

}
