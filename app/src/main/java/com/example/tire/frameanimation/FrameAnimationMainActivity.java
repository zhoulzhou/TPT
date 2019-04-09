package com.example.tire.frameanimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


import com.example.tire.R;
import com.example.tire.common.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class FrameAnimationMainActivity extends AppCompatActivity {
    private ImageView airconditionview;
    private ImageView yellowview;
    private ImageView redView;
    AnimationsContainer.FramesSequenceAnimation animation;

    private AnimImageView mAnimationView_rear_electro_motor_yellow;
    private AnimImageCacheView mYellowCacheView;
    private List<Integer> mResourceIdList_rear_electro_motor_yellow = null;
    private int[] drawable_rear_electro_motor_yellow = {
    };

    private AnimImageView mAnimationView_engine_red_green_to_battery;
    private AnimImageCacheView mRedGreenView;
    private List<Integer> mResourceIdList_engine_red_green_to_battery = null;
    private int[] drawable_engine_red_green_to_battery = {
    };

    private AnimImageView mAircondition;
    private AnimImageCacheView mAirconditionCacheView;
    private List<Integer> mResource_Aircondition = null;
    private int[] wind_level_drawable = {
    };


    int number;
    Drawable[] drawableArray;
    Bitmap[] bitmapArray;
    int mAirImageCount = 100;
    int mYellowImageCount = 25;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_animation_layout);

        yellowview = (ImageView) findViewById(R.id.rear_electro_motor_id);
        airconditionview = (ImageView) findViewById(R.id.air_condition);
        redView = (ImageView) findViewById(R.id.red_green_battery);

//        startAircondition();
//        startyellow();
//        startredGreen();
//        testDrawable();
//        testDrawable2();
//        testBitmap();
//        testDecodeStream();
//        startAirconditionCache();
        startyellowCache();
//        startredGreenCache();
    }

    private void startyellow(){
        initRearElectroMotorYellow();
        mAnimationView_rear_electro_motor_yellow.start(true,100);
    }

    private void startyellowCache(){
        initRearElectroMotorYellowCache();
        mYellowCacheView.start(true,100);
    }

    private void startAirconditionCache(){
        initAirconditionCache();
        mAirconditionCacheView.start(true,100);
    }

    private void startAircondition(){
        initAircondition();
        mAircondition.start(true,65);
    }

    private void startredGreen(){
        initEngineRedGreenToBattery();
        mAnimationView_engine_red_green_to_battery.start(true,100);
    }

    private void startredGreenCache(){
        initEngineRedGreenToBatteryCache();
        mRedGreenView.start(true,100);
    }

    private void initAirconditionTest(){
        int size = 1000;
        int tatalSize = size * 78;
        int index ;
        mAircondition = new AnimImageView();
        mResource_Aircondition = new ArrayList<Integer>(tatalSize);
        for (int i = 0; i < tatalSize; ++i) {

            if(i < 78){
                index = i;
            }else{
                index = i % 78;
            }

            LogUtils.d("initAircondition index= " + index);
            mResource_Aircondition.add(wind_level_drawable[index]);
        }
        mAircondition.setAnimation(airconditionview,mResource_Aircondition,"aircondition");
    }

    private void initAircondition(){
        mAircondition = new AnimImageView();
        mResource_Aircondition = new ArrayList<Integer>(mAirImageCount);
        for (int i = 0; i < mAirImageCount; ++i) {
            mResource_Aircondition.add(wind_level_drawable[i]);
        }
        mAircondition.setAnimation(airconditionview,mResource_Aircondition,"aircondition");
    }

    private void initAirconditionCache(){
        mAirconditionCacheView = new AnimImageCacheView();
        mResource_Aircondition = new ArrayList<Integer>(mAirImageCount);
        for (int i = 0; i < mAirImageCount; ++i) {
            mResource_Aircondition.add(wind_level_drawable[i]);
        }
        mAirconditionCacheView.setAnimation(airconditionview,mResource_Aircondition,"aircondition");
    }

    private void initRearElectroMotorYellow(){
        mAnimationView_rear_electro_motor_yellow = new AnimImageView();
        mResourceIdList_rear_electro_motor_yellow = new ArrayList<Integer>(mYellowImageCount);
        for (int i = 0; i < mYellowImageCount; ++i) {
            mResourceIdList_rear_electro_motor_yellow.add(drawable_rear_electro_motor_yellow[i]);
        }
        mAnimationView_rear_electro_motor_yellow.setAnimation(yellowview,mResourceIdList_rear_electro_motor_yellow,"rear electro motor yellow");
    }

    private void initRearElectroMotorYellowCache(){
        mYellowCacheView = new AnimImageCacheView();
        mResourceIdList_rear_electro_motor_yellow = new ArrayList<Integer>(mYellowImageCount);
        for (int i = 0; i < mYellowImageCount; ++i) {
            mResourceIdList_rear_electro_motor_yellow.add(drawable_rear_electro_motor_yellow[i]);
        }
        mYellowCacheView.setAnimation(yellowview,mResourceIdList_rear_electro_motor_yellow,"rear electro motor yellow");
    }

    private void initEngineRedGreenToBattery(){
        mAnimationView_engine_red_green_to_battery = new AnimImageView();
        mResourceIdList_engine_red_green_to_battery = new ArrayList<Integer>();
        for (int i = 0; i < drawable_engine_red_green_to_battery.length; ++i) {
            mResourceIdList_engine_red_green_to_battery.add(drawable_engine_red_green_to_battery[i]);
        }
        mAnimationView_engine_red_green_to_battery.setAnimation(redView,mResourceIdList_engine_red_green_to_battery,"engine red green to battery ");
    }

    private void initEngineRedGreenToBatteryCache(){
        mRedGreenView = new AnimImageCacheView();
        mResourceIdList_engine_red_green_to_battery = new ArrayList<Integer>();
        for (int i = 0; i < drawable_engine_red_green_to_battery.length; ++i) {
            mResourceIdList_engine_red_green_to_battery.add(drawable_engine_red_green_to_battery[i]);
        }
        mRedGreenView.setAnimation(redView,mResourceIdList_engine_red_green_to_battery,"engine red green to battery ");
    }

    private void testFrameAnimation() {
//        if (animation == null) {
//            animation = AnimationsContainer.getInstance(R.array.loading_anim, 58).createProgressDialogAnim(imageView);
//            animation.start();
//        }
    }

    private void testDrawable(){
        number = 10000;
        drawableArray = new Drawable[number];
        for(int i=0; i<number; i++){
            LogUtils.d("测试第" + (i+1) + "张图片");
            long start = System.currentTimeMillis();
//            drawableArray[i] = getResources().getDrawable(,null);
            airconditionview.setImageDrawable(drawableArray[i]);
            long end = System.currentTimeMillis();
            LogUtils.d("testDrawable getDrawable time= " + (end - start));
        }
    }

    private void testDrawable2(){
        number = 100;
        drawableArray = new Drawable[number];
        for(int i=0; i<number; i++){
            LogUtils.d("测试第" + (i+1) + "张图片");
            long start = System.currentTimeMillis();
            drawableArray[i] = getResources().getDrawable(wind_level_drawable[i], null);
            long end = System.currentTimeMillis();
            LogUtils.d("testDrawable getDrawable time= " + (end - start));
        }
    }

    private void testBitmap(){
        number = 1000;
        bitmapArray = new Bitmap[number];
        for (int i=0; i<number; i++){
            LogUtils.d("测试第" + (i+1) + "张图片");
            long start = System.currentTimeMillis();
//            bitmapArray[i] = BitmapFactory.decodeResource(getResources(),R.drawable.);
            long end = System.currentTimeMillis();
            LogUtils.d("testDrawable getDrawable time= " + (end - start));
        }
    }

    private void testDecodeStream(){
        number = 1000;
        bitmapArray = new Bitmap[number];
        for(int i=0; i<number; i++){
            LogUtils.d("测试第" + (i+1) + "张图片");
            long start = System.currentTimeMillis();
//            bitmapArray[i] = BitmapFactory.decodeStream(getResources().openRawResource(+R.drawable.));
            long end = System.currentTimeMillis();
            LogUtils.d("testDrawable getDrawable time= " + (end - start));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("FrameAnimationMainActivity onPause");
        if(mAircondition != null){
            mAircondition.stop();
        }
        if(mAirconditionCacheView != null){
            mAirconditionCacheView.stop();
        }
        if(mAnimationView_rear_electro_motor_yellow != null){
            mAnimationView_rear_electro_motor_yellow.stop();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d("FrameAnimationMainActivity onStop");
    }
}
