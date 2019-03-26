package com.example.tire.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.tire.common.LogUtils;

public class CircleView extends View {
    private Circle mCircle;
    private Paint mPaint;

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCircle = new Circle(100);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mCircle != null){
            canvas.drawCircle(100,100,mCircle.getRadius(),mPaint);
        }
        super.onDraw(canvas);
    }

    //set函数
    public void setCircleRadius(int radius){
        LogUtils.d("setCircleRadius radius= " + radius);
        mCircle.setRadius(radius);
        invalidate();
    }

    //get函数
    public int getCircleRadius(){
        return mCircle.getRadius();
    }
}
