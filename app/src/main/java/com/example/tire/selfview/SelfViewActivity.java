package com.example.tire.selfview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.tire.R;

public class SelfViewActivity extends AppCompatActivity {
    private double[] mFuelPointValue = {};
    private double[] mElecPointValue = {};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_view_activity_layout);
        LineChartView lineChartView = (LineChartView) findViewById(R.id.line_chart);
        lineChartView.setData(mFuelPointValue,mElecPointValue,2);
    }
}
