package com.health29.ustawi.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.health29.ustawi.R;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.mLottieAnimation)
    LottieAnimationView mLottieAnimationView;

    @BindView(R.id.mAppNameText)
    TextView mTextView;

    Animation mAnimation;

    private static final int SPLASH_TIME = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);

        //Lottie Animation Loop
        setLottieAnimation();

        //App Name Fade In Animation
        mAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_text);
        mTextView.startAnimation(mAnimation);

        //Launch handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            launchActivity(SplashScreenActivity.this, IdentificationActivity.class);
            }
        }, SPLASH_TIME);
    }


    public void setLottieAnimation(){
        mLottieAnimationView.setSpeed(2.0F);
        mLottieAnimationView.setProgress(1.0F);
        mLottieAnimationView.setRepeatMode(LottieDrawable.RESTART);
        mLottieAnimationView.cancelAnimation();
    }

    public void launchActivity(Context context, Class cls){
        Intent intent = new Intent(context, cls);
        startActivity(intent);
        finish();
    }



}