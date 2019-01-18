package com.example.tire.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.tire.common.LogUtils;

public class SimpleLineChart extends View{
    private Paint mXAxisPaint;
    private Paint mYAxisPaint;
    private Paint mLinePaint;

    int mHeight, mWidth;

    public SimpleLineChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtils.d("onMeasureTimes");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize;
        }else if(widthMode == MeasureSpec.AT_MOST){
            throw new IllegalArgumentException("width must be EXACTLY,you should set like android:width=\\\"200dp\\");
        }

        if(heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        }else if(heightMode == MeasureSpec.AT_MOST){
            throw new IllegalArgumentException("height must be EXACTLY");
        }

        LogUtils.d("onMeasure mWidth= " + mWidth + " mHeight= " + mHeight);
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
