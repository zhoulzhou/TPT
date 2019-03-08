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

    private float mSlide_left;
    private boolean mIsOpen = false;

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
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsOpen = !mIsOpen;
                flushState();
                flushView();
            }
        });
    }

    private void flushState(){
        if(mIsOpen){
            mSlide_left = mBackgroundBitmap.getWidth() - mSlideBitmap.getWidth();
        }else{
            mSlide_left = 0;
        }
    }

    private void flushView(){
        invalidate();
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
        canvas.drawBitmap(mSlideBitmap,mSlide_left,0,mBitmapPaint);
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
