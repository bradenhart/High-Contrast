package com.bradenhart.hctester;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by bradenhart on 17/06/15.
 */
public class ProfileScreen extends Activity {

    private final String LOGTAG = "ProfileScreen class";
    private DisplayMetrics metrics;
    private int maxBarWidth;
    private int width;
    private View p1, p2, p3;
    private LinearLayout p1Border, p2Border, p3Border;
    private TextView p1Value, p2Value, p3Value;
    private int progressPadding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        progressPadding = (int) metrics.scaledDensity * 15;
        maxBarWidth = width - (2 * progressPadding);

        p1Border = (LinearLayout) findViewById(R.id.progressbar1_border);
        p2Border = (LinearLayout) findViewById(R.id.progressbar2_border);
        p3Border = (LinearLayout) findViewById(R.id.progressbar3_border);

        p1 = p1Border.findViewById(R.id.progressbar1);
        p2 = p2Border.findViewById(R.id.progressbar2);
        p3 = p3Border.findViewById(R.id.progressbar3);

        p1Value = (TextView) findViewById(R.id.p1_values);
        p2Value = (TextView) findViewById(R.id.p2_values);
        p3Value = (TextView) findViewById(R.id.p3_values);

        setUpProgressBar(p1, p1Value, 3, 8);
        setUpProgressBar(p2, p2Value, 2, 10);
        setUpProgressBar(p3, p3Value, 0, 7);

    }

    private void setUpProgressBar(final View view, final TextView tView,  final int achieved, final int total) {
        final int unitWidth = maxBarWidth / total;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(unitWidth * achieved, (int) metrics.scaledDensity * 25);
        view.setLayoutParams(params);

        tView.setText(achieved + "/" + total);
        tView.setClickable(true);
        tView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String progText = String.valueOf(tView.getText().charAt(0));
                int val = Integer.parseInt(progText);
                if (val < total) {
                    val++;
                    tView.setText(val + "/" + total);
                    view.getLayoutParams().width = unitWidth * val;
                }
            }
        });


    }

}
