package cat.urv.deim.asm.p2.common;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import cat.urv.deim.asm.p2.common.ui.login.LoginActivity;

public class TutorialActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    private void finishTutorial() {
        SharedPreferences preferences =
                getSharedPreferences("PREFERENCES", MODE_PRIVATE);

        preferences.edit()
                .putBoolean("isFirstRun", false).apply();

        Intent intent = new Intent(TutorialActivity.this, LoginActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager);
        /*
         * The pager adapter, which provides the pages to the view pager widget.
         */
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

        final ProgressBar initialProgressBar = findViewById(R.id.progressBar);
        initialProgressBar.setProgress(0);
        CountDownTimer initialCountDownTimer;

        initialCountDownTimer = new CountDownTimer(1500, 15) {
            @Override
            public void onTick(long millisUntilFinished) {
                initialProgressBar.setProgress(Math.min(initialProgressBar.getProgress()+1, 34));
            }

            @Override
            public void onFinish() {
                initialProgressBar.setProgress(34);
                //Do what you want
            }
        };
        initialCountDownTimer.start();

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            int lastPosition = -1;
            ProgressBar mProgressBar;
            boolean rightScroll;
            boolean leftScroll;
            CountDownTimer mCountDownTimer;

            @Override
            public void onPageSelected(final int position) {
                rightScroll = false;
                leftScroll = false;
                mProgressBar = findViewById(R.id.progressBar);
                if (lastPosition > position) {
                    leftScroll = true;
                } else if (lastPosition < position) {
                    mProgressBar.setProgress((position)*33+1);
                    rightScroll = true;
                }
                mCountDownTimer = new CountDownTimer(1500, 15) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (leftScroll) {
                            mProgressBar.setProgress(Math.max(mProgressBar.getProgress()-1, (position+1)*33+1));
                        } else if (rightScroll) {
                            mProgressBar.setProgress(Math.min(mProgressBar.getProgress()+1, (position+1)*33+1));
                        }
                    }

                    @Override
                    public void onFinish() {
                        mProgressBar.setProgress((position + 1) * 33 + 1);
                        //Do what you want

                    }
                };
                mCountDownTimer.start();
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final TextView skip_tutorial1 = findViewById(R.id.textSkipTutorial);
        final ImageView skip_tutorial2 = findViewById(R.id.ivSkipTutorial);

        skip_tutorial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishTutorial();
            }
        });

        skip_tutorial2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishTutorial();
            }
        });




    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 Tutorial1Fragment objects, in
     * sequence.
     */
    public static class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        ScreenSlidePagerAdapter(FragmentManager fm) {
            //noinspection deprecation
            super(fm);

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new Tutorial1Fragment();
                case 1: return new Tutorial2Fragment();
                default: return new Tutorial3Fragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}