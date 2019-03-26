package com.example.tire.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import com.example.tire.R;
import com.example.tire.common.LogUtils;

public class AnimationTestActivity extends AppCompatActivity {
    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_test_layout);
        mView = findViewById(R.id.an_view);
//        an1();
//        an2();
//        an3();
//        an4();
        an5();
    }

    private void an1(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(20,600);
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatCount(3);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                LogUtils.d("curValue= " + curValue);
                ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
                layoutParams.width = layoutParams.height = curValue;
                mView.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.start();
    }

    private void an2(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,400);
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatCount(1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                LogUtils.d("curValue= " + curValue);
                mView.layout(curValue,curValue,curValue+mView.getWidth(),curValue+mView.getHeight());
            }
        });
        valueAnimator.start();
    }

    private void an3(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mView,"alpha", 1,0,1);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }

    private void an4(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mView,"rotationX", 0,270,50);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }

    private void an5(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mView,"translationX", 0,200,-200,0);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }
}
