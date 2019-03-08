package com.example.tire.selfview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.tire.R;

public class SlideSwitch extends View {
    private Bitmap mBackgroundBitmap;
    private Bitmap mSlideBitmap;

    private float mViewWidth, mViewHeight;

    private Paint mBitmapPaint;

    public SlideSwitch(Context context) {
        super(context);
    }

    public SlideSwitch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mBackgroundBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.switch_background);
        mSlideBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.switch_slide);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        mViewHeight = measureSize(heightMeasureSpec);
//        mViewWidth = measureSize(widthMeasureSpec);
//        setMeasuredDimension((int) mViewWidth, (int) mViewHeight);
        setMeasuredDimension(mBackgroundBitmap.getWidth(),mBackgroundBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBackgroundBitmap,0,0,mBitmapPaint);

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
