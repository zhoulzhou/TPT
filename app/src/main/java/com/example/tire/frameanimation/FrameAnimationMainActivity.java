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
            R.drawable.rear_electro_motor_yellow_01,
            R.drawable.rear_electro_motor_yellow_02,
            R.drawable.rear_electro_motor_yellow_03,
            R.drawable.rear_electro_motor_yellow_04,
            R.drawable.rear_electro_motor_yellow_05,
            R.drawable.rear_electro_motor_yellow_06,
            R.drawable.rear_electro_motor_yellow_07,
            R.drawable.rear_electro_motor_yellow_08,
            R.drawable.rear_electro_motor_yellow_09,
            R.drawable.rear_electro_motor_yellow_10,
            R.drawable.rear_electro_motor_yellow_11,
            R.drawable.rear_electro_motor_yellow_12,
            R.drawable.rear_electro_motor_yellow_13,
            R.drawable.rear_electro_motor_yellow_14,
            R.drawable.rear_electro_motor_yellow_15,
            R.drawable.rear_electro_motor_yellow_16,
            R.drawable.rear_electro_motor_yellow_17,
            R.drawable.rear_electro_motor_yellow_18,
            R.drawable.rear_electro_motor_yellow_19,
            R.drawable.rear_electro_motor_yellow_20,
            R.drawable.rear_electro_motor_yellow_21,
            R.drawable.rear_electro_motor_yellow_22,
            R.drawable.rear_electro_motor_yellow_23,
            R.drawable.rear_electro_motor_yellow_24,
            R.drawable.rear_electro_motor_yellow_25
    };

    private AnimImageView mAnimationView_engine_red_green_to_battery;
    private AnimImageCacheView mRedGreenView;
    private List<Integer> mResourceIdList_engine_red_green_to_battery = null;
    private int[] drawable_engine_red_green_to_battery = {
            R.drawable.engine_red_green_to_battery_01,
            R.drawable.engine_red_green_to_battery_02,
            R.drawable.engine_red_green_to_battery_03,
            R.drawable.engine_red_green_to_battery_04,
            R.drawable.engine_red_green_to_battery_05,
            R.drawable.engine_red_green_to_battery_06,
            R.drawable.engine_red_green_to_battery_07,
            R.drawable.engine_red_green_to_battery_08,
            R.drawable.engine_red_green_to_battery_09,
            R.drawable.engine_red_green_to_battery_10,
            R.drawable.engine_red_green_to_battery_11,
            R.drawable.engine_red_green_to_battery_12,
            R.drawable.engine_red_green_to_battery_13,
            R.drawable.engine_red_green_to_battery_14,
            R.drawable.engine_red_green_to_battery_15,
            R.drawable.engine_red_green_to_battery_16,
            R.drawable.engine_red_green_to_battery_17,
            R.drawable.engine_red_green_to_battery_18,
            R.drawable.engine_red_green_to_battery_19,
            R.drawable.engine_red_green_to_battery_20,
            R.drawable.engine_red_green_to_battery_21,
            R.drawable.engine_red_green_to_battery_22,
            R.drawable.engine_red_green_to_battery_23,
            R.drawable.engine_red_green_to_battery_24,
            R.drawable.engine_red_green_to_battery_25
    };

    private AnimImageView mAircondition;
    private AnimImageCacheView mAirconditionCacheView;
    private List<Integer> mResource_Aircondition = null;
    private int[] wind_level_drawable = {
            R.drawable.electric_wind_level_animation_00,
            R.drawable.electric_wind_level_animation_01,
            R.drawable.electric_wind_level_animation_02,
            R.drawable.electric_wind_level_animation_03,
            R.drawable.electric_wind_level_animation_04,
            R.drawable.electric_wind_level_animation_05,
            R.drawable.electric_wind_level_animation_06,
            R.drawable.electric_wind_level_animation_07,
            R.drawable.electric_wind_level_animation_08,
            R.drawable.electric_wind_level_animation_09,
            R.drawable.electric_wind_level_animation_10,
            R.drawable.electric_wind_level_animation_11,
            R.drawable.electric_wind_level_animation_12,
            R.drawable.electric_wind_level_animation_13,
            R.drawable.electric_wind_level_animation_14,
            R.drawable.electric_wind_level_animation_15,
            R.drawable.electric_wind_level_animation_16,
            R.drawable.electric_wind_level_animation_17,
            R.drawable.electric_wind_level_animation_18,
            R.drawable.electric_wind_level_animation_19,
            R.drawable.electric_wind_level_animation_20,
            R.drawable.electric_wind_level_animation_21,
            R.drawable.electric_wind_level_animation_22,
            R.drawable.electric_wind_level_animation_23,
            R.drawable.electric_wind_level_animation_24,
            R.drawable.electric_wind_level_animation_25,
            R.drawable.electric_wind_level_animation_26,
            R.drawable.electric_wind_level_animation_27,
            R.drawable.electric_wind_level_animation_28,
            R.drawable.electric_wind_level_animation_29,
            R.drawable.electric_wind_level_animation_30,
            R.drawable.electric_wind_level_animation_31,
            R.drawable.electric_wind_level_animation_32,
            R.drawable.electric_wind_level_animation_33,
            R.drawable.electric_wind_level_animation_34,
            R.drawable.electric_wind_level_animation_35,
            R.drawable.electric_wind_level_animation_36,
            R.drawable.electric_wind_level_animation_37,
            R.drawable.electric_wind_level_animation_38,
            R.drawable.electric_wind_level_animation_39,
            R.drawable.electric_wind_level_animation_40,
            R.drawable.electric_wind_level_animation_41,
            R.drawable.electric_wind_level_animation_42,
            R.drawable.electric_wind_level_animation_43,
            R.drawable.electric_wind_level_animation_44,
            R.drawable.electric_wind_level_animation_45,
            R.drawable.electric_wind_level_animation_46,
            R.drawable.electric_wind_level_animation_47,
            R.drawable.electric_wind_level_animation_48,
            R.drawable.electric_wind_level_animation_49,
            R.drawable.electric_wind_level_animation_50,
            R.drawable.electric_wind_level_animation_51,
            R.drawable.electric_wind_level_animation_52,
            R.drawable.electric_wind_level_animation_53,
            R.drawable.electric_wind_level_animation_54,
            R.drawable.electric_wind_level_animation_55,
            R.drawable.electric_wind_level_animation_56,
            R.drawable.electric_wind_level_animation_57,
            R.drawable.electric_wind_level_animation_58,
            R.drawable.electric_wind_level_animation_59,
            R.drawable.electric_wind_level_animation_60,
            R.drawable.electric_wind_level_animation_61,
            R.drawable.electric_wind_level_animation_62,
            R.drawable.electric_wind_level_animation_63,
            R.drawable.electric_wind_level_animation_64,
            R.drawable.electric_wind_level_animation_65,
            R.drawable.electric_wind_level_animation_66,
            R.drawable.electric_wind_level_animation_67,
            R.drawable.electric_wind_level_animation_68,
            R.drawable.electric_wind_level_animation_69,
            R.drawable.electric_wind_level_animation_70,
            R.drawable.electric_wind_level_animation_71,
            R.drawable.electric_wind_level_animation_72,
            R.drawable.electric_wind_level_animation_73,
            R.drawable.electric_wind_level_animation_74,
            R.drawable.electric_wind_level_animation_75,
            R.drawable.electric_wind_level_animation_76,
            R.drawable.electric_wind_level_animation_77,
            R.drawable.electric_wind_level_animation_78,
            R.drawable.electric_wind_level_animation_79,
            R.drawable.electric_wind_level_animation_80,
            R.drawable.electric_wind_level_animation_81,
            R.drawable.electric_wind_level_animation_82,
            R.drawable.electric_wind_level_animation_83,
            R.drawable.electric_wind_level_animation_84,
            R.drawable.electric_wind_level_animation_85,
            R.drawable.electric_wind_level_animation_86,
            R.drawable.electric_wind_level_animation_87,
            R.drawable.electric_wind_level_animation_88,
            R.drawable.electric_wind_level_animation_89,
            R.drawable.electric_wind_level_animation_90,
            R.drawable.electric_wind_level_animation_91,
            R.drawable.electric_wind_level_animation_92,
            R.drawable.electric_wind_level_animation_93,
            R.drawable.electric_wind_level_animation_94,
            R.drawable.electric_wind_level_animation_95,
            R.drawable.electric_wind_level_animation_96,
            R.drawable.electric_wind_level_animation_97,
            R.drawable.electric_wind_level_animation_98,
            R.drawable.electric_wind_level_animation_99
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
        startredGreenCache();
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
            drawableArray[i] = getResources().getDrawable(R.drawable.electric_wind_level_animation_00,null);
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
            bitmapArray[i] = BitmapFactory.decodeResource(getResources(),R.drawable.electric_wind_level_animation_00);
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
            bitmapArray[i] = BitmapFactory.decodeStream(getResources().openRawResource(+R.drawable.electric_wind_level_animation_00));
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
