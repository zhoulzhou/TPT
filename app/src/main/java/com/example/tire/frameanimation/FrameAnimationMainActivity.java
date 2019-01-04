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
    AnimationsContainer.FramesSequenceAnimation animation;

    private AnimImageView mAnimationView_rear_electro_motor_yellow;
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

    private AnimImageView mAircondition;
    private List<Integer> mResource_Aircondition = null;
    private int[] wind_level_drawable = {
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
            R.drawable.electric_wind_level_animation_50
    };


    int number;
    Drawable[] drawableArray;
    Bitmap[] bitmapArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_animation_layout);

        yellowview = (ImageView) findViewById(R.id.rear_electro_motor_id);
        airconditionview = (ImageView) findViewById(R.id.air_condition);

//        startAircondition();
//        startyellow();
//        testDrawable();
        testDrawable2();
//        testBitmap();
//        testDecodeStream();
    }

    private void startyellow(){
        initRearElectroMotorYellow();
        mAnimationView_rear_electro_motor_yellow.start(true,100);
    }

    private void startAircondition(){
        initAircondition();
        mAircondition.start(false,20);
    }

    private void initAirconditionTest(){
        int size = 1000;
        int tatalSize = size * 100;
        int index ;
        mAircondition = new AnimImageView();
        mResource_Aircondition = new ArrayList<Integer>(tatalSize);
        for (int i = 0; i < tatalSize; ++i) {

            if(i < 100){
                index = i;
            }else{
                index = i % 100;
            }

            LogUtils.d("initAircondition index= " + index);
            mResource_Aircondition.add(wind_level_drawable[index]);
        }
        mAircondition.setAnimation(airconditionview,mResource_Aircondition,"aircondition");
    }

    private void initAircondition(){
        mAircondition = new AnimImageView();
        mResource_Aircondition = new ArrayList<Integer>(100);
        for (int i = 0; i < wind_level_drawable.length; ++i) {
            mResource_Aircondition.add(wind_level_drawable[i]);
        }
        mAircondition.setAnimation(airconditionview,mResource_Aircondition,"aircondition");
    }

    private void initRearElectroMotorYellow(){
        mAnimationView_rear_electro_motor_yellow = new AnimImageView();
        mResourceIdList_rear_electro_motor_yellow = new ArrayList<Integer>(25);
        for (int i = 0; i < drawable_rear_electro_motor_yellow.length; ++i) {
            mResourceIdList_rear_electro_motor_yellow.add(drawable_rear_electro_motor_yellow[i]);
        }
        mAnimationView_rear_electro_motor_yellow.setAnimation(yellowview,mResourceIdList_rear_electro_motor_yellow,"rear electro motor yellow");
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
        drawableArray = new Drawable[100];
        for(int i=0; i<100; i++){
            LogUtils.d("测试第" + (i+1) + "张图片");
            long start = System.currentTimeMillis();
            drawableArray[i] = getResources().getDrawable(wind_level_drawable[i],null);
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

}
