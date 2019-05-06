package com.example.tire.selfview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.tire.R;


public class SelfViewActivity extends AppCompatActivity {
    LineChartView lineChartView;

    private final static int MSG_UPDATE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_view_activity_layout);
        lineChartView = (LineChartView) findViewById(R.id.line_chart);
        lineChartView.setData();

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
        lineChartView.setData();
    }
}
