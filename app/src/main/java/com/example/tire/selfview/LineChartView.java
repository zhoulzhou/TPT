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
    private final static int PWOER_TYPE_FUEL = 0;
    private final static int PWOER_TYPE_EV = 1;
    private final static int PWOER_TYPE_HEV = 2;
    private final static String FUEL_AXIS = "L";
    private final static String ELEC_AXIS = "kW.h";
    private Context mContext;
    private int mViewWidth;// 宽
    private int mViewHeight;// 高
    private int mBarSpace;// 纵线间距
    private int mLeftRightBlank;
    private int mTopMargin;
    private int mBottomMargin;
    private int mMoreLineEnd;

    private Paint mFuelLinePaint;// 左线条画笔
    private Paint mElecLinePaint;// 右线条画笔
    private Paint mElecTextPaint;
    private Paint mFuelTextPaint;

    private final static int mPointNumber = 72;
    private double[] mFuelPointValue;
    private double[] mElecPointValue;
    private final int[] mFuelAxis = {15, 10, 5, 0};
    private final int[] mElecAxis = {30, 20, 10, 0};
    private int x_unit[];
    private int mPowerType;
    private int deviationValue;
    private int mBottomTextMarginBottom;
    private int mBottomTextMarginLeftRight;

    public void setData(double[] fuelPointValue, double[] elecPointValue, int powertype) {
        if ((powertype > PWOER_TYPE_HEV) || (powertype < PWOER_TYPE_FUEL)) {
            mPowerType = PWOER_TYPE_FUEL;
        }
        mPowerType = powertype;
        mFuelPointValue = fuelPointValue;
        mElecPointValue = elecPointValue;
        invalidate();
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        mFuelLinePaint = new Paint();
        mFuelLinePaint.setStyle(Paint.Style.FILL);
        mFuelLinePaint.setColor(context.getResources().getColor(
                R.color.fuel_consume_color, null));
        mFuelLinePaint.setAntiAlias(true);
        mFuelLinePaint.setStrokeWidth((float) 2.0);

        mElecLinePaint = new Paint();
        mElecLinePaint.setStyle(Paint.Style.FILL);
        mElecLinePaint.setColor(context.getResources().getColor(
                R.color.elec_consume_color, null));
        mElecLinePaint.setAntiAlias(true);
        mElecLinePaint.setStrokeWidth((float) 2.0);

        mElecTextPaint = new Paint();
        mElecTextPaint.setTextAlign(Paint.Align.LEFT);
        mElecTextPaint.setStyle(Paint.Style.FILL);
        mElecTextPaint.setColor(context.getResources().getColor(
                R.color.text_color_yellow_dark, null));
        mElecTextPaint.setAntiAlias(true);
        mElecTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.line_chart_text_size_normal));

        mFuelTextPaint = new Paint();
        mFuelTextPaint.setTextAlign(Paint.Align.RIGHT);
        mFuelTextPaint.setStyle(Paint.Style.FILL);
        mFuelTextPaint.setColor(context.getResources().getColor(
                R.color.text_color_blue_light));
        mFuelTextPaint.setAntiAlias(true);
        mFuelTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.line_chart_text_size_normal));

        mLeftRightBlank = getResources().getDimensionPixelSize(R.dimen.line_chart_left_right_blank);
        mBottomMargin = getResources().getDimensionPixelSize(R.dimen.line_chart_bottom_margin);
        mMoreLineEnd = getResources().getDimensionPixelSize(R.dimen.line_chart_more_line_end);
        deviationValue = getResources().getDimensionPixelSize(R.dimen.line_chart_deviation_value);
        mBottomTextMarginBottom = getResources().getDimensionPixelSize(R.dimen.line_chart_bottom_text_margin_bottom);
        mBottomTextMarginLeftRight = getResources().getDimensionPixelSize(R.dimen.line_chart_bottom_text_margin_left_right);
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
        android.util.Log.d(TAG, "onDraw mFuelPointValue = " + mFuelPointValue.length + " mElecPointValue=" + mElecPointValue.length);
        if (mFuelPointValue.length != 0 && mElecPointValue.length != 0) {
            mBarSpace = (((mViewWidth - mLeftRightBlank * 2 - 2 * deviationValue) / (mPointNumber - 1)) / 5) * 4;
            mTopMargin = getResources().getDimensionPixelSize(R.dimen.line_chart_top_margin);
            x_unit = new int[mPointNumber];
            for (int i = 0; i < mPointNumber; i++) {
                x_unit[i] = mLeftRightBlank + deviationValue + mBarSpace * i;
            }
            //drawTitle(canvas);
            drawHorizontalLine(canvas);
            drawVerticalAxisLine(canvas);
            drawVerticalAxisText(canvas);
            drawValues(canvas);
        } else {
//            drawHorizontalLine(canvas);
//            drawVerticalAxisLine(canvas);
//            drawVerticalAxisText(canvas);
//            drawFuelAxisText(canvas);
//            drawEleclAxisText(canvas);
            drawNoData(canvas);
        }
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
        canvas.drawText(mContext.getResources().getString(R.string.travelinfo_no_point_data), mViewWidth / 2,
                (mTopMargin + mViewHeight - mBottomMargin) / 2, noDataTextPaint);
    }

    private void drawHorizontalLine(Canvas canvas) {
        // 画横坐标
        LogUtils.d("drawHorizontalLine");
        Paint alphaLinePaint = new Paint();// Paint the translucent lines
        alphaLinePaint.setStyle(Paint.Style.FILL);
        alphaLinePaint.setColor(mContext.getResources().getColor(
                android.R.color.holo_blue_bright, null));
        alphaLinePaint.setAntiAlias(true);
        alphaLinePaint.setAlpha(150);
        for (int i = 1; i < 9; i++) {
            canvas.drawLine(mLeftRightBlank + deviationValue, mTopMargin + ((mViewHeight - mTopMargin - mBottomMargin) * i / 8), mViewWidth - mLeftRightBlank - deviationValue, mTopMargin + ((mViewHeight - mTopMargin - mBottomMargin) * i / 8),
                    alphaLinePaint);
        }

    }

    private void drawVerticalAxisLine(Canvas canvas){
        LogUtils.d("drawVerticalAxisLine");
        // 画竖坐标的线
        Paint gradientWhiteLine = new Paint();
        LinearGradient backGradient = new LinearGradient(mLeftRightBlank, mTopMargin,
                mLeftRightBlank, mViewHeight - mBottomMargin + mMoreLineEnd, new int[]{Color.BLACK, mContext.getResources().getColor(
                R.color.fuel_consume_color, null), Color.BLACK}, null, Shader.TileMode.CLAMP);
        gradientWhiteLine.setShader(backGradient);
        gradientWhiteLine.setStyle(Paint.Style.FILL);
        gradientWhiteLine.setColor(mContext.getResources().getColor(
                R.color.fuel_consume_color, null));
        gradientWhiteLine.setAntiAlias(true);
        gradientWhiteLine.setStrokeWidth((float) 2.0);
        canvas.drawLine(mLeftRightBlank, mTopMargin,
                mLeftRightBlank, mViewHeight - mBottomMargin + mMoreLineEnd,
                gradientWhiteLine);

        Paint gradientBlueLine = new Paint();
        LinearGradient blueBackGradient = new LinearGradient(mViewWidth - mLeftRightBlank, mTopMargin,
                mViewWidth - mLeftRightBlank, mViewHeight - mBottomMargin + mMoreLineEnd, new int[]{Color.BLACK, mContext.getResources().getColor(
                R.color.elec_consume_color, null), Color.BLACK}, null, Shader.TileMode.CLAMP);
        gradientBlueLine.setShader(blueBackGradient);
        gradientBlueLine.setStyle(Paint.Style.FILL);
        gradientBlueLine.setColor(mContext.getResources().getColor(
                R.color.elec_consume_color, null));
        gradientBlueLine.setAntiAlias(true);
        gradientBlueLine.setStrokeWidth((float) 2.0);
        canvas.drawLine(mViewWidth - mLeftRightBlank, mTopMargin,
                mViewWidth - mLeftRightBlank, mViewHeight - mBottomMargin + mMoreLineEnd,
                gradientBlueLine);
    }

    private void drawVerticalAxisText(Canvas canvas) {
        // 画竖坐标的数字
        LogUtils.d("drawVerticalAxisText");
        int offset = mContext.getResources().getDimensionPixelSize(R.dimen.line_chart_axis_text_offset);
        for (int i = 0; i < mFuelAxis.length; i++) {
            if (mPowerType == PWOER_TYPE_HEV) {
                if (i == mFuelAxis.length - 1) {
                    canvas.drawText(String.valueOf(mFuelAxis[i]) + FUEL_AXIS, mLeftRightBlank - mMoreLineEnd * 3 / 5,
                            mTopMargin + ((mViewHeight - mTopMargin - mBottomMargin) * (i + 1) / 4 + getTextHeight(String.valueOf(mFuelAxis[i])) / 2) + offset, mFuelTextPaint);
                    canvas.drawText(String.valueOf(mElecAxis[i]) + ELEC_AXIS, mViewWidth - mLeftRightBlank + mMoreLineEnd * 3 / 5,
                            mTopMargin + ((mViewHeight - mTopMargin - mBottomMargin) * (i + 1) / 4) + getTextHeight(String.valueOf(mElecAxis[i])) / 2 + offset, mElecTextPaint);
                } else {
                    canvas.drawText(String.valueOf(mFuelAxis[i]), mLeftRightBlank - mMoreLineEnd * 3 / 5,
                            mTopMargin + ((mViewHeight - mTopMargin - mBottomMargin) * (i + 1) / 4 + getTextHeight(String.valueOf(mFuelAxis[i])) / 2) + offset, mFuelTextPaint);
                    canvas.drawText(String.valueOf(mElecAxis[i]), mViewWidth - mLeftRightBlank + mMoreLineEnd * 3 / 5,
                            mTopMargin + ((mViewHeight - mTopMargin - mBottomMargin) * (i + 1) / 4) + getTextHeight(String.valueOf(mElecAxis[i])) / 2 + offset, mElecTextPaint);
                }

            } else {
                if (mPowerType == PWOER_TYPE_FUEL) {
                    if (i == mFuelAxis.length - 1) {
                        canvas.drawText(String.valueOf(mFuelAxis[i]) + FUEL_AXIS, mLeftRightBlank - mMoreLineEnd * 3 / 5,
                                mTopMargin + ((mViewHeight - mTopMargin - mBottomMargin) * (i + 1) / 4 + getTextHeight(String.valueOf(mFuelAxis[i])) / 2) + offset, mFuelTextPaint);
                    } else {
                        canvas.drawText(String.valueOf(mFuelAxis[i]), mLeftRightBlank - mMoreLineEnd * 3 / 5,
                                mTopMargin + ((mViewHeight - mTopMargin - mBottomMargin) * (i + 1) / 4 + getTextHeight(String.valueOf(mFuelAxis[i])) / 2) + offset, mFuelTextPaint);
                    }
                } else {
                    if (i == mFuelAxis.length - 1) {
                        canvas.drawText(String.valueOf(mElecAxis[i]) + ELEC_AXIS, mViewWidth - mLeftRightBlank + mMoreLineEnd * 3 / 5,
                                mTopMargin + ((mViewHeight - mTopMargin - mBottomMargin) * (i + 1) / 4) + getTextHeight(String.valueOf(mElecAxis[i])) / 2 + offset, mElecTextPaint);
                    } else {
                        canvas.drawText(String.valueOf(mElecAxis[i]), mViewWidth - mLeftRightBlank + mMoreLineEnd * 3 / 5,
                                mTopMargin + ((mViewHeight - mTopMargin - mBottomMargin) * (i + 1) / 4) + getTextHeight(String.valueOf(mElecAxis[i])) / 2 + offset, mElecTextPaint);
                    }
                }
            }

        }
    }

    /**
     * 画代表数值的小圆点
     *
     * @param canvas the point
     */
    private void drawValues(Canvas canvas) {
        if (mPowerType == PWOER_TYPE_HEV) {
            int fuelPoint_y[] = new int[mFuelPointValue.length];
            for (int i = 0; i < mFuelPointValue.length; i++) {
                if (mFuelPointValue[i] < 0) {
                    fuelPoint_y[i] = mViewHeight - mBottomMargin;
                } else {
                    float mFuelMaxValue = 20f;
                    fuelPoint_y[i] = (int) (mViewHeight - (mViewHeight - mTopMargin - mBottomMargin)
                            / mFuelMaxValue * mFuelPointValue[i] - mBottomMargin);
                }
                android.util.Log.d(TAG, "drawValues mFuelPoints =" + x_unit[i] + " ," + fuelPoint_y[i]);
                canvas.drawCircle(x_unit[i], fuelPoint_y[i], 2, mFuelLinePaint);
            }
            int elecPoint_y[] = new int[mElecPointValue.length];
            for (int i = 0; i < mElecPointValue.length; i++) {
                if (mElecPointValue[i] < 0) {
                    elecPoint_y[i] = mViewHeight - mBottomMargin;
                } else {
                    float mElecMaxValue = 40f;
                    elecPoint_y[i] = (int) (mViewHeight - (mViewHeight - mTopMargin - mBottomMargin)
                            / mElecMaxValue * mElecPointValue[i] - mBottomMargin);
                }
                android.util.Log.d(TAG, "drawValues elecPoints =" + x_unit[i] + " ," + elecPoint_y[i]);
                canvas.drawCircle(x_unit[i], elecPoint_y[i], 2, mElecLinePaint);
            }
            drawLine(canvas, fuelPoint_y, mFuelLinePaint);
            drawLine(canvas, elecPoint_y, mElecLinePaint);
            drawFuelAxisText(canvas);
            drawEleclAxisText(canvas);
            //drawAverageText(canvas, mFuelPointValue, fuelPoint_y, fuelRightTextPaint, mContext.getResources().getString(R.string.travelinfo_fuel_unit));
            //drawAverageText(canvas, mElecPointValue, elecPoint_y, mElecTextPaint, mContext.getResources().getString(R.string.travelinfo_elec_unit));
        } else {
            if (mPowerType == PWOER_TYPE_FUEL) {
                int fuelPoint_y[] = new int[mFuelPointValue.length];
                for (int i = 0; i < mFuelPointValue.length; i++) {
                    if (mFuelPointValue[i] < 0) {
                        fuelPoint_y[i] = mViewHeight - mBottomMargin;
                    } else {
                        float mFuelMaxValue = 20f;
                        fuelPoint_y[i] = (int) (mViewHeight - (mViewHeight - mTopMargin - mBottomMargin)
                                / mFuelMaxValue * mFuelPointValue[i] - mBottomMargin);
                    }
                    android.util.Log.d(TAG, "drawValues mFuelPoints =" + x_unit[i] + " ," + fuelPoint_y[i]);
                    canvas.drawCircle(x_unit[i], fuelPoint_y[i], 2, mFuelLinePaint);
                }
                drawLine(canvas, fuelPoint_y, mFuelLinePaint);
                drawFuelAxisText(canvas);
                //drawAverageText(canvas, mFuelPointValue, fuelPoint_y, fuelRightTextPaint, mContext.getResources().getString(R.string.travelinfo_fuel_unit));
            } else {
                int elecPoint_y[] = new int[mElecPointValue.length];
                for (int i = 0; i < mElecPointValue.length; i++) {
                    if (mElecPointValue[i] < 0) {
                        elecPoint_y[i] = mViewHeight - mBottomMargin;
                    } else {
                        float mElecMaxValue = 40f;
                        elecPoint_y[i] = (int) (mViewHeight - (mViewHeight - mTopMargin - mBottomMargin)
                                / mElecMaxValue * mElecPointValue[i] - mBottomMargin);
                    }
                    android.util.Log.d(TAG, "drawValues elecPoints =" + x_unit[i] + " ," + elecPoint_y[i]);
                    canvas.drawCircle(x_unit[i], elecPoint_y[i], 2, mElecLinePaint);
                }
                drawLine(canvas, elecPoint_y, mElecLinePaint);
                drawEleclAxisText(canvas);
                //drawAverageText(canvas, mElecPointValue, elecPoint_y, mElecTextPaint, mContext.getResources().getString(R.string.travelinfo_elec_unit));
            }
        }
    }

    /**
     * 画小圆点间的连线
     *
     * @param canvas which is to draw on
     */
    private void drawLine(Canvas canvas, int[] points, Paint paint) {
        for (int i = 0; i < points.length - 1; i++) {
            canvas.drawLine(x_unit[i], points[i], x_unit[i + 1], points[i + 1],
                    paint);
        }

    }

    private void drawAverageText(Canvas canvas, double[] values, int[] point_y, Paint paint, String useUnit) {
        double sum = 0;
        for (double i : values) {
            sum += i;
        }
        double doubleAverage = sum / values.length;
        DecimalFormat unit = new DecimalFormat(".#");
        String average = unit.format(doubleAverage) + " " + useUnit;
        canvas.drawText(average, x_unit[point_y.length - 1] + getTextWidth(average),
                point_y[point_y.length - 1] + getTextHeight(average) / 2, paint);
    }

    private void drawFuelAxisText(Canvas canvas) {
        LogUtils.d("drawFuelAxisText");
        Paint fuelTextPaint = new Paint();
        fuelTextPaint.setTextAlign(Paint.Align.LEFT);
        fuelTextPaint.setStyle(Paint.Style.FILL);
        fuelTextPaint.setColor(mContext.getResources().getColor(
                R.color.fuel_consume_text_color, null));
        fuelTextPaint.setAntiAlias(true);
        fuelTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.line_chart_axis_text_size_normal));
        canvas.drawText(getResources().getString(R.string.travelinfo_average_50_fuel), mBottomTextMarginLeftRight, mViewHeight - mBottomTextMarginBottom, fuelTextPaint);
    }

    private void drawEleclAxisText(Canvas canvas) {
        LogUtils.d("drawEleclAxisText");
        Paint elecTextPaint = new Paint();
        elecTextPaint.setTextAlign(Paint.Align.RIGHT);
        elecTextPaint.setStyle(Paint.Style.FILL);
        elecTextPaint.setColor(mContext.getResources().getColor(
                R.color.elec_consume_text_color, null));
        elecTextPaint.setAntiAlias(true);
        elecTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.line_chart_axis_text_size_normal));
        canvas.drawText(getResources().getString(R.string.travelinfo_average_50_elec), mViewWidth - mBottomTextMarginLeftRight, mViewHeight - mBottomTextMarginBottom, elecTextPaint);
    }


    private static int getTextWidth(String displayText) {
        Paint textPaint = new Paint();
        Rect rect = new Rect();
        textPaint.getTextBounds(displayText, 0, 1, rect);
        return rect.width();
    }

    private static int getTextHeight(String displayText) {
        Paint textPaint = new Paint();
        Rect rect = new Rect();
        textPaint.getTextBounds(displayText, 0, 1, rect);
        return rect.height();
    }
}
