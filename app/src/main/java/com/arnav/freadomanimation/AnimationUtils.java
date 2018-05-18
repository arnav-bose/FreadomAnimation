package com.arnav.freadomanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Arnav on 26/04/2018 at 12:39.
 */
public class AnimationUtils {

    public static ObjectAnimator translateX(View view, float start, float end, int duration){
        ObjectAnimator objectAnimatorTranslateX = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, start, end);
        objectAnimatorTranslateX.setDuration(duration);
        return objectAnimatorTranslateX;
    }

    public static ObjectAnimator translateY(View view, float start, float end, int duration){
        ObjectAnimator objectAnimatorTranslateY = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, start, end);
        objectAnimatorTranslateY.setDuration(duration);
        return objectAnimatorTranslateY;
    }

    public static AnimatorSet translateRocket(final View view){
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();
        int translateXValue = -(682 + viewWidth) * 2;
        int translateYValue = (682 + viewHeight) * 2;
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(2f);
        ObjectAnimator objectAnimatorRight = translateX(view, translateXValue, 0, 1250);
        objectAnimatorRight.setInterpolator(decelerateInterpolator);
        ObjectAnimator objectAnimatorUp = translateY(view, translateYValue, 0, 1250);
        objectAnimatorUp.setInterpolator(decelerateInterpolator);
        AnimatorSet animatorSetTranslate = new AnimatorSet();
        animatorSetTranslate.playTogether(objectAnimatorRight, objectAnimatorUp);
        return animatorSetTranslate;
    }

    public static ObjectAnimator fadeIn(final View view, int duration){
        ObjectAnimator objectAnimatorFadeIn = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1);
        objectAnimatorFadeIn.setDuration(duration);
        objectAnimatorFadeIn.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        return objectAnimatorFadeIn;
    }

    public static ObjectAnimator[] fadeInMultiple(int duration, int delay, View... views) {
        int numberOfViews = views.length;
        ObjectAnimator[] objectAnimators = new ObjectAnimator[numberOfViews];
        for (int i = 0; i < numberOfViews; i++) {
            final View view = views[i];
            ObjectAnimator objectAnimatorFadeIn = fadeIn(view, duration);
            objectAnimatorFadeIn.setStartDelay(delay * (i + 1));
            objectAnimators[i] = objectAnimatorFadeIn;
        }
        return objectAnimators;
    }

    public static AnimatorSet scaleUpScaleDown(View view, int duration, float startScaleFactor, float endScaleFactor){
        ObjectAnimator objectAnimatorScaleUpX = ObjectAnimator.ofFloat(view, View.SCALE_X, startScaleFactor, endScaleFactor);
        ObjectAnimator objectAnimatorScaleUpY = ObjectAnimator.ofFloat(view, View.SCALE_Y, startScaleFactor, endScaleFactor);
        ObjectAnimator objectAnimatorScaleDownX = ObjectAnimator.ofFloat(view, View.SCALE_X, endScaleFactor, startScaleFactor);
        ObjectAnimator objectAnimatorScaleDownY = ObjectAnimator.ofFloat(view, View.SCALE_Y, endScaleFactor, startScaleFactor);
        AnimatorSet animatorSetScaleUp = new AnimatorSet();
        animatorSetScaleUp.playTogether(objectAnimatorScaleUpX, objectAnimatorScaleUpY);

        AnimatorSet animatorSetScaleDown = new AnimatorSet();
        animatorSetScaleDown.playTogether(objectAnimatorScaleDownX, objectAnimatorScaleDownY);

        AnimatorSet animatorSetScaleUpScaleDown = new AnimatorSet();
        animatorSetScaleUpScaleDown.playSequentially(animatorSetScaleUp, animatorSetScaleDown);
        animatorSetScaleUpScaleDown.setDuration(duration);

        return animatorSetScaleUpScaleDown;
    }
}
