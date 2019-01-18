package com.example.tire.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.tire.common.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class SimpleLineChart extends View{
    private Paint mAxisPaint;
    private Paint mLinePaint;
    private Paint mCirclePaint;

    private String[] mXAxisText = {"1","2","3","4","5"};
    private String[] mYAxisText = {"50K","40K","30K","20K","10K"};
    private int mXAxisTextSize = 30;
    private int mYAxisTextSize = 30;

    private Map<Integer,Integer> mPointMap = new HashMap<>();
    String mNoDataMsg = "无可显示的数据";

    int mHeight, mWidth;

    public SimpleLineChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mPointMap.put(0,4);
        mPointMap.put(1,3);
        mPointMap.put(2,0);
        mPointMap.put(3,1);
        mPointMap.put(4,2);

        mAxisPaint = new Paint();
        mAxisPaint.setTextSize(mYAxisTextSize);
        mAxisPaint.setColor(Color.parseColor("#3F51B5"));

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.GREEN);
        mLinePaint.setStrokeWidth(6);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.BLUE);
        mCirclePaint.setStyle(Paint.Style.STROKE);
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
        LogUtils.d("onDrawTimes");
        super.onDraw(canvas);
        if(mXAxisText.length == 0 || mYAxisText.length == 0){
            throw new IllegalArgumentException("X or Y items is null");
        }

        if(mPointMap == null || mPointMap.size() == 0){
            //获取文字的宽度
            int textLength = (int) mAxisPaint.measureText(mNoDataMsg);
            canvas.drawText(mNoDataMsg, mWidth/2 - textLength/2, mHeight/2,mAxisPaint);
        }

        drawYAxis(canvas);
        drawXAxis(canvas);
        drawCircleAndLine(canvas);
    }

    int[] yPoint;
    int yInterval;
    private void drawYAxis(Canvas canvas){
        //存放每个Y轴的坐标
        yPoint  = new int[mYAxisText.length];

        //测量Y轴文字的高度 用来画第一个数
        Paint.FontMetrics fm = mAxisPaint.getFontMetrics();
        int yItemHeight = (int) Math.ceil(fm.descent - fm.ascent);
        LogUtils.d("drawYAxis yItemHeight= " + yItemHeight);

        //计算Y轴 每个刻度的间距
        yInterval = (mHeight - yItemHeight*2)/(mYAxisText.length - 1);
        LogUtils.d("drawYAxis yInterval= " + yInterval);

        for(int i=0; i<mYAxisText.length; i++){
            int y = yItemHeight + yInterval*i;
            LogUtils.d("drawYAxis y= " + y);
            canvas.drawText(mYAxisText[i],0,y,mAxisPaint);
            yPoint[i] = y;
        }
    }

    int[] xPoint;
    int xInterval;
    int xItemWidth;
    private void drawXAxis(Canvas canvas){
        xPoint = new int[mXAxisText.length];

        //计算Y轴开始的原点坐标
        xItemWidth = (int) mAxisPaint.measureText(mXAxisText[1]);

        //X轴的偏移量
        int xOffset = 50;

        //计算X轴刻度的偏移量
        xInterval = (mWidth - xOffset - xItemWidth)/(mXAxisText.length -1);
        LogUtils.d("drawYAxis xInterval= " + xInterval);

        //X轴刻度的Y坐标
        int xItemY = mHeight;

        for(int i=0; i<mXAxisText.length; i++){
            int x= xOffset + xInterval*i;
            LogUtils.d("drawYAxis x1= " + x);
            canvas.drawText(mXAxisText[i],x,xItemY,mAxisPaint);
            x = (int) (x + mAxisPaint.measureText(mXAxisText[i])/2);
            xPoint[i] = x;
            LogUtils.d("drawYAxis x2= " + x);
        }

    }

    float circleRadius= 12;
    private void drawCircleAndLine(Canvas canvas){
        for(int i=0; i<mXAxisText.length; i++){
            LogUtils.d("drawCircleAndLine i= " + i);
            canvas.drawCircle(xPoint[i],yPoint[mPointMap.get(i)],circleRadius,mCirclePaint);
            if(i>0){
                canvas.drawLine(xPoint[i-1],yPoint[mPointMap.get(i-1)],xPoint[i],yPoint[mPointMap.get(i)],mLinePaint);
            }
        }
    }

    private void getTextSize(String str, Paint paint){
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        int w = rect.width();
        int h = rect.height();
        LogUtils.d("w=" + w +"  h="+h);
    }
}
