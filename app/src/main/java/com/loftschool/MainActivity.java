package com.loftschool;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ViewPager mViewPager;
    private MainPagesAdapter mMainPagesAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);
        mMainPagesAdapter = new MainPagesAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(mMainPagesAdapter);

        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
