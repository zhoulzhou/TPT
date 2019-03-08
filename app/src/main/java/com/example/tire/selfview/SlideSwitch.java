package com.example.tire.selfview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SlideSwitch extends View {
    private Bitmap mBackgroundBitmap;
    private Bitmap mSlideBitmap;

    private float mViewWidth, mViewHeight;

    public SlideSwitch(Context context) {
        super(context);
    }

    public SlideSwitch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewHeight = measureSize(heightMeasureSpec);
        mViewWidth = measureSize(widthMeasureSpec);
        setMeasuredDimension((int) mViewWidth, (int) mViewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private int measureSize(int measureSpec){
        int realSize = 10;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);

        if(mode == MeasureSpec.EXACTLY){
            realSize = size;
        }

        return realSize;
    }

}
