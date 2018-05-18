package com.example.sebastian.scastro;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sebastian.scastro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private SectionStatePagerAdapter sectionStatePagerAdapter;
    private ViewPager viewPager;

    private ActivityMainBinding activityMainBinding;

    static DateTime dateTime = new DateTime();
    static double latitude = 52;
    static double longitude = 21;
    static int refreshTime = 2000;


    static Sun sun = new Sun();
    static Moon moon = new Moon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.setDateTime(dateTime);

        sectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        thread.start();
        thread2.start();
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionStatePagerAdapter adapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1(), "Fragment1");
        adapter.addFragment(new Fragment2(), "Fragment2");

        viewPager.setAdapter(adapter);

    }

    Thread thread = new Thread() {

        private boolean stopNow = false;

        public void stopNow() {
            stopNow = true;
        }


        @Override
        public void run() {
            try {
                while (!interrupted() && !stopNow) {
                    Thread.sleep(1000);
                    if (!stopNow) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dateTime.refreshTime();
                                System.out.println(moon.getMoonPhase());
                            }
                        });
                    }
                }
            } catch (InterruptedException ignored) {
            }
        }
    };

    Thread thread2 = new Thread() {

        @Override
        public void run() {
            try {
                while (!interrupted()) {
                    Thread.sleep(refreshTime);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dateTime.refreshAllTime();
                            sun.refresh();
                            moon.refresh();
                        }
                    });
                }
            } catch (InterruptedException ignored) {
            }
        }
    };



    public void onClickMenu(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}



