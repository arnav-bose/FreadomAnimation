package com.arnav.freadomanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {

    @BindView(R.id.iv_cloud)
    ImageView imageViewCloud;
    @BindView(R.id.iv_rocket)
    ImageView imageViewRocket;
    @BindView(R.id.iv_rocket_trail)
    ImageView imageViewRocketTrail;
    @BindView(R.id.tv_logo_f)
    TextView textViewLogoF;
    @BindView(R.id.tv_logo_r)
    TextView textViewLogoR;
    @BindView(R.id.tv_logo_e)
    TextView textViewLogoE;
    @BindView(R.id.tv_logo_a)
    TextView textViewLogoA;
    @BindView(R.id.tv_logo_d)
    TextView textViewLogoD;
    @BindView(R.id.tv_logo_o)
    TextView textViewLogoO;
    @BindView(R.id.tv_logo_m)
    TextView textViewLogoM;
    @BindView(R.id.tv_read)
    TextView textViewRead;
    @BindView(R.id.tv_play)
    TextView textViewPlay;
    @BindView(R.id.tv_go)
    TextView textViewGo;
    @BindView(R.id.btn_explore)
    Button buttonExplore;
    @BindView(R.id.tv_login)
    TextView textViewLogin;
    @BindView(R.id.fl_star_road)
    FrameLayout frameLayoutStarRoad;

    private Handler mHandler;
    private boolean hasScreenAnimationEnded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startScreenAnimation();
            }
        }, 1500);
    }

    private void startScreenAnimation() {
        animateRocket();
        animateCloud();
        animateRocketTrail();
        animateFreadomLogo();
        animateTextBelowLogo();
        animateExploreButton();
        animateLoginTextView();

        mHandler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(!hasScreenAnimationEnded){
                    int delay = 3000;
                    mHandler.postDelayed(this, delay);
                    hasScreenAnimationEnded = true;
                }
                else {
                    int randomDelay = Utils.randomNumber(100, 600);
                    mHandler.postDelayed(this, randomDelay);
                    animateStars();
                }

            }
        };

        runnable.run();

        animateRocketTrailFade();

    }

    private void animateRocket() {
        AnimatorSet animatorSetTranslate = AnimationUtils.translateRocket(imageViewRocket);
        imageViewRocket.setVisibility(View.VISIBLE);
        animatorSetTranslate.start();
    }

    private void animateCloud() {
        ObjectAnimator objectAnimatorFadeIn = AnimationUtils.fadeIn(imageViewCloud, 750);
        objectAnimatorFadeIn.setStartDelay(750);
        objectAnimatorFadeIn.start();
    }

    private void animateRocketTrail() {
        ObjectAnimator objectAnimatorFadeIn = AnimationUtils.fadeIn(imageViewRocketTrail, 750);
        objectAnimatorFadeIn.setStartDelay(500);
        objectAnimatorFadeIn.start();
    }

    private void animateFreadomLogo() {
        final int FREADOM_LOGO_DELAY = 900;
        ObjectAnimator objectAnimatorFadeInF = AnimationUtils.fadeIn(textViewLogoF, 750);
        ObjectAnimator objectAnimatorFadeInR = AnimationUtils.fadeIn(textViewLogoR, 750);
        ObjectAnimator objectAnimatorFadeInE = AnimationUtils.fadeIn(textViewLogoE, 750);
        ObjectAnimator objectAnimatorFadeInA = AnimationUtils.fadeIn(textViewLogoA, 750);
        ObjectAnimator objectAnimatorFadeInD = AnimationUtils.fadeIn(textViewLogoD, 750);
        ObjectAnimator objectAnimatorFadeInO = AnimationUtils.fadeIn(textViewLogoO, 750);
        ObjectAnimator objectAnimatorFadeInM = AnimationUtils.fadeIn(textViewLogoM, 750);
        objectAnimatorFadeInF.setStartDelay(FREADOM_LOGO_DELAY + 100);
        objectAnimatorFadeInR.setStartDelay(FREADOM_LOGO_DELAY + (100 * 2));
        objectAnimatorFadeInE.setStartDelay(FREADOM_LOGO_DELAY + (100 * 3));
        objectAnimatorFadeInA.setStartDelay(FREADOM_LOGO_DELAY + (100 * 4));
        objectAnimatorFadeInD.setStartDelay(FREADOM_LOGO_DELAY + (100 * 5));
        objectAnimatorFadeInO.setStartDelay(FREADOM_LOGO_DELAY + (100 * 6));
        objectAnimatorFadeInM.setStartDelay(FREADOM_LOGO_DELAY + (100 * 7));
        objectAnimatorFadeInF.start();
        objectAnimatorFadeInR.start();
        objectAnimatorFadeInE.start();
        objectAnimatorFadeInA.start();
        objectAnimatorFadeInD.start();
        objectAnimatorFadeInO.start();
        objectAnimatorFadeInM.start();

        /*ObjectAnimator[] objectAnimators = AnimationUtils.fadeInMultiple(
                750,
                150,
                textViewLogoF,
                textViewLogoR,
                textViewLogoE,
                textViewLogoA,
                textViewLogoD,
                textViewLogoO,
                textViewLogoM);
        for(int i = 0; i < objectAnimators.length; i++){
            ObjectAnimator objectAnimator = objectAnimators[i];
            objectAnimator.start();
        }*/
    }

    private void animateTextBelowLogo() {
        ObjectAnimator objectAnimatorFadeInRead = AnimationUtils.fadeIn(textViewRead, 500);
        ObjectAnimator objectAnimatorFadeInPlay = AnimationUtils.fadeIn(textViewPlay, 500);
        ObjectAnimator objectAnimatorFadeInGo = AnimationUtils.fadeIn(textViewGo, 500);

        AnimatorSet animatorSetFadeIn = new AnimatorSet();
        animatorSetFadeIn.playSequentially(objectAnimatorFadeInRead, objectAnimatorFadeInPlay, objectAnimatorFadeInGo);
        animatorSetFadeIn.setStartDelay(2000);
        animatorSetFadeIn.start();
    }

    private void animateExploreButton() {
        ObjectAnimator objectAnimatorTranslateUp = AnimationUtils.translateY(buttonExplore, 240, 0, 500);
        objectAnimatorTranslateUp.setInterpolator(new DecelerateInterpolator());
        objectAnimatorTranslateUp.setStartDelay(3500);
        objectAnimatorTranslateUp.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                buttonExplore.setVisibility(View.VISIBLE);
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
        objectAnimatorTranslateUp.start();
    }

    private void animateLoginTextView() {
        ObjectAnimator objectAnimatorTranslateUp = AnimationUtils.translateY(textViewLogin, 240, 0, 500);
        objectAnimatorTranslateUp.setInterpolator(new DecelerateInterpolator());
        objectAnimatorTranslateUp.setStartDelay(3600);
        objectAnimatorTranslateUp.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                textViewLogin.setVisibility(View.VISIBLE);
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
        objectAnimatorTranslateUp.start();
    }

    private void animateStars() {
        final ImageView imageViewStar = addStarToRoad();
        int starWidth = imageViewStar.getWidth();

        int[] widthHeight = Utils.getScreenWidthHeight(this);

        int randomNumber = Utils.randomNumber(widthHeight[0] - 512, widthHeight[0] + 1024);
        float translateStartX = randomNumber;
        float translateEndX = -(64 * 2);
        float translateStartY = -(64 * 2);
        float translateEndY = randomNumber;

        ObjectAnimator objectAnimatorX = AnimationUtils.translateX(
                imageViewStar,
                translateStartX,
                translateEndX,
                6000);

        ObjectAnimator objectAnimatorY = AnimationUtils.translateY(
                imageViewStar,
                translateStartY,
                translateEndY,
                6000);


        final AnimatorSet animatorSetScaleUpScaleDown = AnimationUtils.scaleUpScaleDown(imageViewStar, 1000, 0.8f, 1.2f);
        animatorSetScaleUpScaleDown.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorSetScaleUpScaleDown.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        AnimatorSet animatorSetTranslate = new AnimatorSet();
        animatorSetTranslate.playTogether(objectAnimatorX, objectAnimatorY);
        animatorSetTranslate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                frameLayoutStarRoad.removeView(imageViewStar);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSetTranslate.start();
        FrameLayout.LayoutParams layoutParamsStars = new FrameLayout.LayoutParams(64, 64);
        imageViewStar.setLayoutParams(layoutParamsStars);
        animatorSetScaleUpScaleDown.start();
    }

    private ImageView addStarToRoad() {
        ImageView imageViewStar = new ImageView(this);
        imageViewStar.setImageResource(R.mipmap.ic_star);

        frameLayoutStarRoad.addView(imageViewStar);
        return imageViewStar;
    }

    private void animateRocketTrailFade() {
        ObjectAnimator objectAnimatorFadeInFadeOut = ObjectAnimator.ofFloat(imageViewRocketTrail, View.ALPHA, 0.25f, 1);
        objectAnimatorFadeInFadeOut.setDuration(2000);
        objectAnimatorFadeInFadeOut.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimatorFadeInFadeOut.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimatorFadeInFadeOut.setStartDelay(3200);
        objectAnimatorFadeInFadeOut.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
