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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_animation_layout);

        imageView = (ImageView) findViewById(R.id.rear_electro_motor_id);

        initRearElectroMotorYellow();
        mAnimationView_rear_electro_motor_yellow.start(true,100);
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
