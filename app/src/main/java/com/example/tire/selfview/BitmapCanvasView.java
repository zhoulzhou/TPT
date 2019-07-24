package com.example.tire.selfview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class BitmapCanvasView extends View {
    private Bitmap mBitmap;
    private Canvas mBitmapCanvas;
    private Paint mPaint;
    public BitmapCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);

        mBitmap = Bitmap.createBitmap(500,500, Bitmap.Config.ARGB_8888);

        mBitmapCanvas = new Canvas(mBitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.RED);

        mPaint.setTextSize(40);
        mBitmapCanvas.drawText("WELCOME",0,100,mPaint);

        canvas.drawBitmap(mBitmap,0,0,mPaint);
    }
}
