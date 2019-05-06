package com.example.tire.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.tire.R;
import com.example.tire.common.LogUtils;

import java.text.DecimalFormat;


public class LineChartView extends View {
    private static final String TAG = "LineChartView";
    private Context mContext;
    private int mViewWidth;// 宽
    private int mViewHeight;// 高
    private int mLeftRightBlank;
    private int mBottomMargin;

    public void setData() {
        invalidate();
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        mLeftRightBlank = getResources().getDimensionPixelSize(R.dimen.line_chart_left_right_blank);
        mBottomMargin = getResources().getDimensionPixelSize(R.dimen.line_chart_bottom_margin);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mViewWidth = MeasureSpec.getSize(widthMeasureSpec);
        mViewHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mViewWidth, mViewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.d("onDraw");
        drawHorizontalLine(canvas);
        drawNoData(canvas);
    }

    //应该使用全局变量
    Paint noDataTextPaint = new Paint();;
    private void drawNoData(Canvas canvas) {
        LogUtils.d("drawNoData");

        noDataTextPaint.setTextAlign(Paint.Align.CENTER);
        noDataTextPaint.setStyle(Paint.Style.FILL);
        noDataTextPaint.setColor(mContext.getResources().getColor(
                android.R.color.black));
        noDataTextPaint.setAntiAlias(true);
        noDataTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.line_chart_text_size_no_data));
        LogUtils.d("drawNoData noDataTextPaint " + noDataTextPaint.toString());
        canvas.drawText(mContext.getResources().getString(R.string.travelinfo_no_point_data), mViewWidth / 2, mViewHeight / 2, noDataTextPaint);
    }

    private void drawHorizontalLine(Canvas canvas) {
        // 画横坐标
        LogUtils.d("drawHorizontalLine");
        Paint alphaLinePaint = new Paint();// Paint the translucent lines
        alphaLinePaint.setStyle(Paint.Style.FILL);
        alphaLinePaint.setColor(mContext.getResources().getColor(
                R.color.red, null));
        alphaLinePaint.setAntiAlias(true);
        alphaLinePaint.setAlpha(150);
        for (int i = 1; i < 9; i++) {
            canvas.drawLine(mLeftRightBlank , (mViewHeight - mBottomMargin) * i / 8,
                    mViewWidth - mLeftRightBlank, (mViewHeight - mBottomMargin) * i / 8, alphaLinePaint);
        }

    }


    private int getTextWidth(String displayText) {
        Paint textPaint = new Paint();
        Rect rect = new Rect();
        textPaint.getTextBounds(displayText, 0, 1, rect);
        return rect.width();
    }

    private int getTextHeight(String displayText) {
        Paint textPaint = new Paint();
        Rect rect = new Rect();
        textPaint.getTextBounds(displayText, 0, 1, rect);
        return rect.height();
    }
}
