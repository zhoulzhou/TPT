package com.example.tire.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.tire.common.LogUtils;

public class SlideVerifyView extends View {
    private Paint mBottomGrayPaint;
    private Paint mBottomGreenPaint;
    private Paint mSlidePaint;
    private Paint mTextPaint;

    private String mHintText = "滑动验证";
    private String mCompleteText = "验证完成";

    private float mSlideWidth;
    private float mViewWidth;
    private float mViewHeight;

    private float mTextHintWidth;
    private float mTextCompleteWidth;

    private float mCurrentX;
    private boolean mIsSliding;
    private boolean mIsSlidingCompleted;

    private SlideCompleteListener mSlideCompleteListener;

    public SlideVerifyView(Context context) {
        super(context);
    }

    public SlideVerifyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint(){
        mBottomGrayPaint = new Paint();
        mBottomGrayPaint.setAntiAlias(true);
        mBottomGrayPaint.setColor(Color.GRAY);

        mBottomGreenPaint = new Paint();
        mBottomGreenPaint.setAntiAlias(true);
        mBottomGreenPaint.setColor(Color.GREEN);

        mSlidePaint = new Paint();
        mSlidePaint.setAntiAlias(true);
        mSlidePaint.setColor(Color.BLUE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(20);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(widthMeasureSpec);
        int height = measureSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        LogUtils.d("onMeasure width= " + width + " height= " + height);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = getWidth();
        mViewHeight = getHeight();
        mSlideWidth = mViewWidth/5;
        mTextHintWidth = mTextPaint.measureText(mHintText);
        mTextCompleteWidth = mTextPaint.measureText(mCompleteText);
        LogUtils.d("onSizeChanged mViewWidth= " + mViewWidth);
        LogUtils.d("onSizeChanged mViewHeight= " + mViewHeight);
        LogUtils.d("onSizeChanged mSlideWidth= " + mSlideWidth);
        LogUtils.d("onSizeChanged mTextHintWidth= " + mTextHintWidth);
        LogUtils.d("onSizeChanged mTextCompleteWidth= " + mTextCompleteWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画出底部的灰色矩形背景
        canvas.drawRect(0,0,mViewWidth,mViewHeight,mBottomGrayPaint);

        // 根据滑块的坐标位置，画出白色滑块，滑块的坐标根据手触摸到屏幕上坐标位置计算
        canvas.drawRect(mCurrentX,0,mSlideWidth+mCurrentX,mViewHeight,mSlidePaint);

        // 当滑块右侧位置滑动到提示字体左侧时去除掉（不再画出）提示字体
        if(mCurrentX+mSlideWidth <= mViewWidth/2+mTextHintWidth/2){
            canvas.drawText(mHintText,mViewWidth/2-mTextHintWidth/2,mViewHeight/2,mTextPaint);
        }

        // 滑块滑向右侧的过程中，滑块左侧显示出绿色
        canvas.drawRect(0,0,mCurrentX,mViewHeight,mBottomGreenPaint);

        // 当滑块滑动到右侧时，画出滑块上的绿色圆环及验证成功字样
        if(mIsSlidingCompleted){
            //画绿色的圆
            canvas.drawCircle(mViewWidth-mSlideWidth/2,mViewHeight/2,15,mBottomGreenPaint);

           //画验证成功字样
            mTextPaint.setColor(Color.BLACK);
            canvas.drawText(mCompleteText,mViewWidth/2-mTextCompleteWidth/2,mViewHeight/2,mTextPaint);

            //设置滑动完成监听
            if(mSlideCompleteListener != null){
                mSlideCompleteListener.slideComplete();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!mIsSlidingCompleted){
            switch (event.getAction()){
                case MotionEvent.ACTION_MOVE:
                    mCurrentX = event.getX() < 0 ? 0 : event.getX();
                    if(mCurrentX >= mViewWidth-mSlideWidth){
                        mCurrentX = mViewWidth-mSlideWidth;
                    }
                    LogUtils.d("onTouchEvent ACTION_MOVE getX= " + event.getX());
                    LogUtils.d("onTouchEvent ACTION_MOVE mCurrentX= " + mCurrentX);
                    invalidate();
                    break;
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    //up时判断滑动不到最后要回到原点
                    if(mCurrentX < mViewWidth-mSlideWidth-10){
                        mCurrentX = 0;
                        mIsSlidingCompleted = false;
                    }else{
                        mCurrentX = mViewWidth-mSlideWidth;
                        mIsSlidingCompleted = true;
                    }
                    LogUtils.d("onTouchEvent ACTION_UP mCurrentX= " + mCurrentX);
                    invalidate();
                    break;
            }
        }

        return true;
    }

    private int measureSize(int measureSpec){
        int realSize;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);

        if(mode == MeasureSpec.EXACTLY){
            realSize = size;
        }else{
            realSize = 200;
        }
        LogUtils.d("measureSize realSize= " + realSize);
        return realSize;
    }

    /**
     * 声明接口是为了验证完成时进行回掉
     */
    public interface SlideCompleteListener{
        void slideComplete();
    }

    /**
     * 设置监听
     */
    public void setSlideCompleteListener(SlideCompleteListener slideCompleteListener){
        mSlideCompleteListener = slideCompleteListener;
    }
}
