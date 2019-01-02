package com.example.tire.frameanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


import com.example.tire.R;

import java.util.ArrayList;
import java.util.List;

public class FrameAnimationMainActivity extends AppCompatActivity {
    private ImageView imageView;
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
            R.drawable.electric_wind_level_animation_77
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_animation_layout);

        imageView = (ImageView) findViewById(R.id.rear_electro_motor_id);

        startAircondition();
    }

    private void startyellow(){
        initRearElectroMotorYellow();
        mAnimationView_rear_electro_motor_yellow.start(true,100);
    }

    private void startAircondition(){
        initAircondition();
        mAircondition.start(true,100);
    }

    private void initAircondition(){
        mAircondition = new AnimImageView();
        mResource_Aircondition = new ArrayList<Integer>(77);
        for (int i = 0; i < wind_level_drawable.length; ++i) {
            mResource_Aircondition.add(wind_level_drawable[i]);
        }
        mAircondition.setAnimation(imageView,mResource_Aircondition,"aircondition");
    }

    private void initRearElectroMotorYellow(){
        mAnimationView_rear_electro_motor_yellow = new AnimImageView();
        mResourceIdList_rear_electro_motor_yellow = new ArrayList<Integer>();
        for (int i = 0; i < drawable_rear_electro_motor_yellow.length; ++i) {
            mResourceIdList_rear_electro_motor_yellow.add(drawable_rear_electro_motor_yellow[i]);
        }
        mAnimationView_rear_electro_motor_yellow.setAnimation(imageView,mResourceIdList_rear_electro_motor_yellow,"rear electro motor yellow");
    }

    private void testFrameAnimation() {
        if (animation == null) {
            animation = AnimationsContainer.getInstance(R.array.loading_anim, 58).createProgressDialogAnim(imageView);
            animation.start();
        }
    }

}
