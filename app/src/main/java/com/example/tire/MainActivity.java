package com.example.tire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.tire.model.TirePressureDetectionModel;
import com.example.tire.presenter.ITirePressureDetectionPresenter;
import com.example.tire.presenter.TirePressureDetectionPresenter;
import com.example.tire.view.ITirePressureDetectionView;
import com.example.tire.view.TwoTextView;

public class MainActivity extends AppCompatActivity implements ITirePressureDetectionView{
    private TwoTextView mTirePressureFLText, mTirePressureFRText;
    private TwoTextView mTirePressureBLText, mTirePressureBRText;
    private ImageView mTirePressureFL, mTirePressureFR;
    private ImageView mTirePressureBL, mTirePressureBR;
    private ITirePressureDetectionPresenter mTirePressureDetectionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTirePressureFLText = (TwoTextView) findViewById(R.id.tire_fl_text);
        mTirePressureFRText = (TwoTextView) findViewById(R.id.tire_fr_text);
        mTirePressureBLText = (TwoTextView) findViewById(R.id.tire_bl_text);
        mTirePressureBRText = (TwoTextView) findViewById(R.id.tire_br_text);
        mTirePressureFL = (ImageView) findViewById(R.id.tire_fl);
        mTirePressureFR = (ImageView) findViewById(R.id.tire_fr);
        mTirePressureBL = (ImageView) findViewById(R.id.tire_bl);
        mTirePressureBR = (ImageView) findViewById(R.id.tire_br);

        mTirePressureDetectionPresenter = new TirePressureDetectionPresenter(TirePressureDetectionModel.getInstance());
        mTirePressureDetectionPresenter.attachView(this);
        mTirePressureDetectionPresenter.startTireDetection();
    }

    @Override
    public void showTirePressureFL(float pressure, float temperature) {
        mTirePressureFLText.setValue(pressure,temperature);
        if(isDangerous(pressure,temperature)){
            mTirePressureFL.setImageResource(R.color.red);
        }else{
            mTirePressureFL.setImageResource(R.color.blue);
        }
    }

    @Override
    public void showTirePressureFR(float pressure, float temperature) {
        mTirePressureFRText.setValue(pressure,temperature);
        if(isDangerous(pressure,temperature)){
            mTirePressureFR.setImageResource(R.color.red);
        }else{
            mTirePressureFL.setImageResource(R.color.blue);
        }
    }

    @Override
    public void showTirePressureBL(float pressure, float temperature) {
        mTirePressureBLText.setValue(pressure,temperature);
        if(isDangerous(pressure,temperature)){
            mTirePressureBL.setImageResource(R.color.red);
        }else{
            mTirePressureFL.setImageResource(R.color.blue);
        }
    }

    @Override
    public void showTirePressureBR(float pressure, float temperature) {
        mTirePressureBRText.setValue(pressure,temperature);
        if(isDangerous(pressure,temperature)){
            mTirePressureBR.setImageResource(R.color.red);
        }else{
            mTirePressureFL.setImageResource(R.color.blue);
        }
    }

    private boolean isDangerous(float pressure, float temperature){
        return pressure < 2 || pressure > 8 || temperature < 20 || temperature > 80;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTirePressureDetectionPresenter.stopTireDetection();
        mTirePressureDetectionPresenter.detachView();
    }
}
