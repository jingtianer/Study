package com.example.reby;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();//主要的就是这三行

        if(actionBar != null){//这三行

            actionBar.hide();//三行

        }

        setContentView(R.layout.activity_welcome);

        imageView = (ImageView)findViewById(R.id.welcome_logo);
        textView = (TextView)findViewById(R.id.welcome_introduction);
        animation = AnimationUtils.loadAnimation(this,R.anim.view_animation);

        imageView.startAnimation(animation);
        textView.startAnimation(animation);

       animation.setAnimationListener(new Animation.AnimationListener() {
           @Override
           public void onAnimationStart(Animation animation) {

           }

           @Override
           public void onAnimationEnd(Animation animation) {

               Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
               startActivity(intent);
               finish();
           }

           @Override
           public void onAnimationRepeat(Animation animation) {

           }
       });

    }



}
