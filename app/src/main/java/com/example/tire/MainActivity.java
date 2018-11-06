package com.example.tire;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tire.view.TirePressureDetectionFragment;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            FragmentManager mFragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            TirePressureDetectionFragment tirePressureDetectionFragment = new TirePressureDetectionFragment();
            fragmentTransaction.add(R.id.fragment_layout, tirePressureDetectionFragment, "tire_pressure_detection");
            fragmentTransaction.commit();
        }

    }
}
