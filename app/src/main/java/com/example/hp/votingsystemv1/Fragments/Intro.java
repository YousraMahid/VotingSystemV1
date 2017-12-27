package com.example.hp.votingsystemv1.Fragments;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.hp.votingsystemv1.R;
import com.github.paolorotolo.appintro.AppIntro;

public class Intro extends AppIntro {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(SlideMaker.newInstance(R.layout.home, 0));
        addSlide(SlideMaker.newInstance(R.layout.registration, 1));
        addSlide(SlideMaker.newInstance(R.layout.vote, 2));
        addSlide(SlideMaker.newInstance(R.layout.work, 3));


        showSkipButton(false);
        setFlowAnimation();

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();

    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        if(newFragment == null) return;
        if((newFragment != null)&&((SlideMaker) newFragment).getScreenId() == 0) {
            setIndicatorColor(getResources().getColor(R.color.colorIcons), getResources().getColor(R.color.colorIcons));
            setNextArrowColor(getResources().getColor(R.color.colorSlide1));
            setColorDoneText(getResources().getColor(R.color.colorIcons));
            setBarColor(getResources().getColor(R.color.colorSlide1));
            setSeparatorColor(getResources().getColor(R.color.colorSlide1));
        } else if (((SlideMaker) newFragment).getScreenId() == 1) {

            setIndicatorColor(getResources().getColor(R.color.colorIcons), getResources().getColor(R.color.colorIcons));
            setNextArrowColor(getResources().getColor(R.color.colorSlide2));
            setColorDoneText(getResources().getColor(R.color.colorIcons));
            setBarColor(getResources().getColor(R.color.colorSlide2));
            setSeparatorColor(getResources().getColor(R.color.colorSlide2));


        } else if (((SlideMaker) newFragment).getScreenId() == 2) {

            setIndicatorColor(getResources().getColor(R.color.colorIcons), getResources().getColor(R.color.colorIcons));
            setNextArrowColor(getResources().getColor(R.color.colorSlide3));
            setColorDoneText(getResources().getColor(R.color.colorIcons));
            setBarColor(getResources().getColor(R.color.colorSlide3));
            setSeparatorColor(getResources().getColor(R.color.colorSlide3));

        } else if (((SlideMaker) newFragment).getScreenId() == 3) {

            setIndicatorColor(getResources().getColor(R.color.colorIcons), getResources().getColor(R.color.colorIcons));
            setNextArrowColor(getResources().getColor(R.color.colorSlide4));
            setColorDoneText(getResources().getColor(R.color.colorIcons));
            setBarColor(getResources().getColor(R.color.colorSlide4));
            setSeparatorColor(getResources().getColor(R.color.colorSlide4));

        }

    }

}




