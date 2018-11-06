package com.example.tire.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tire.R;

import java.text.DecimalFormat;

public class TwoTextView extends LinearLayout{
    private TextView mPressureText;
    private TextView mTemperatureText;

    public TwoTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.two_text_view_layout,this);
        mPressureText = view.findViewById(R.id.pressure_text);
        mTemperatureText = view.findViewById(R.id.temperature_text);
    }

    StringBuffer pressureSB = new StringBuffer();
    StringBuffer temperatureSB = new StringBuffer();
    public void setValue(float pressure, float temperature){
        pressureSB.append(format(pressure));
        pressureSB.append(" Bar");
        mPressureText.setText(pressureSB);

        temperatureSB.append(format(temperature));
        temperatureSB.append(" °C");
        mTemperatureText.setText(temperatureSB);
    }

    DecimalFormat df = new DecimalFormat(".0");
    private String format(float f){
        return df.format(f);
    }
}
