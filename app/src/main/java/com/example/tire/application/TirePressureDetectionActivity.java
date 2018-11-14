package com.example.tire.application;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tire.R;
import com.example.tire.common.LogUtils;
import com.example.tire.common.SPrefUtils;
import com.example.tire.datafactory.DataService;
import com.example.tire.datafactory.TireTableOperator;
import com.example.tire.view.TirePressureDetectionFragment;

public class TirePressureDetectionActivity extends AppCompatActivity {
    private Intent mServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertTireDBFirst();

        if(savedInstanceState == null) {
            FragmentManager mFragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            TirePressureDetectionFragment tirePressureDetectionFragment = new TirePressureDetectionFragment();
            fragmentTransaction.add(R.id.fragment_layout, tirePressureDetectionFragment, "tire_pressure_detection");
            fragmentTransaction.commit();
        }
        mServiceIntent = new Intent(this,DataService.class);
        startService(mServiceIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("TirePressureDetectionActivity onDestroy");
        stopService(mServiceIntent);
    }

    private void insertTireDBFirst(){
        if(!SPrefUtils.getBoolean(this,SPrefUtils.IS_INSERT_DB_FIRST,false)){
            TireTableOperator.getInstance().insert();
            SPrefUtils.setBoolean(this,SPrefUtils.IS_INSERT_DB_FIRST,true);
        }
    }

}
