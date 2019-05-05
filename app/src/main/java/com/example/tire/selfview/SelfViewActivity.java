package com.example.tire.selfview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.tire.R;

import java.util.HashMap;
import java.util.logging.MemoryHandler;

public class SelfViewActivity extends AppCompatActivity {
    LineChartView lineChartView;

    private double[] mFuelPointValue = {};
    private double[] mElecPointValue = {};

    private final static int MSG_UPDATE = 1;
    private Message mMassage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_view_activity_layout);
        lineChartView = (LineChartView) findViewById(R.id.line_chart);
        lineChartView.setData(mFuelPointValue, mElecPointValue, 2);

        mHandler.sendEmptyMessageDelayed(MSG_UPDATE,3000);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_UPDATE:
                    update();
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE,3000);
                    break;
                default:
                    break;
            }
        }
    };

    private void update(){
        lineChartView.setData(mFuelPointValue, mElecPointValue, 2);
    }
}
