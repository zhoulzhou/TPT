package com.example.tire.frameanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.tire.R;

public class FrameAnimationMainActivity extends AppCompatActivity {
    private ImageView imageView;
    AnimationsContainer.FramesSequenceAnimation animation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testFrameAnimation();
    }

    private void testFrameAnimation() {
        if (animation == null) {
            animation = AnimationsContainer.getInstance(R.array.loading_anim, 58).createProgressDialogAnim(imageView);
            animation.start();
        }
    }

}
