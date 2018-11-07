package com.example.tire.view;

import android.annotation.SuppressLint;
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

    public TwoTextView(Context context){
        this(context,null);
    }

    public TwoTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.two_text_view_layout,this);
        mPressureText = (TextView) view.findViewById(R.id.pressure_text);
        mTemperatureText = (TextView) view.findViewById(R.id.temperature_text);
    }

    @SuppressLint("StringFormatInvalid")
    public void setValue(float pressure, float temperature){
//        mPressureText.setText( format(pressure)+ " Bar");
//        mTemperatureText.setText(format(temperature) + " °C");
        mPressureText.setText(getResources().getString(R.string.pressure_text,pressure));
        mTemperatureText.setText(getResources().getString(R.string.temperature_text,temperature));
    }

    DecimalFormat df = new DecimalFormat(".0");
    private String format(float f){
        return df.format(f);
    }
}
