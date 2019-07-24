package com.example.tire.selfview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class XfermodeView extends View {
    private int width = 400;
    private int height = 400;
    private Bitmap srcBitmap;
    private Bitmap dstBitmap;
    private Paint mPaint;

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        srcBitmap = makeSrc(width,height);
        dstBitmap = makeDst(width,height);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GREEN);

        int layoutId = canvas.saveLayer(0,0,width*2,height*2,mPaint,Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(dstBitmap,0,0,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap,width/2,height/2,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layoutId);
    }

    //画圆形
    private Bitmap makeDst(int w, int h){
        Bitmap bitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFCC44);

        c.drawOval(new RectF(0,0,w,h),p);
        return bitmap;
    }

    //画正方形
    private Bitmap makeSrc(int w, int h){
        Bitmap bitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFF66AAFF);

        c.drawRect(0,0,w,h,p);
        return bitmap;
    }
}
