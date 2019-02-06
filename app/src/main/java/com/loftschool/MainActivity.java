package com.loftschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import static com.loftschool.ItemListFragment.ADD_ITEM_REQUEST_CODE;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "MainActivity";

    private ViewPager mViewPager;
    private MainPagesAdapter mMainPagesAdapter;
    private TabLayout mTabLayout;
    private FloatingActionButton mFab;
    private ActionMode mActionMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFab = findViewById(R.id.fab);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = findViewById(R.id.viewPager);
        mViewPager.addOnPageChangeListener(this);
        mMainPagesAdapter = new MainPagesAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(mMainPagesAdapter);

        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);



        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: FAB");

                int currentPage = mViewPager.getCurrentItem();
                String type = null;

                if(currentPage==MainPagesAdapter.PAGE_INCOMES){
                    type = Item.TYPE_INCOMES;
                }
                else if (currentPage == MainPagesAdapter.PAGE_COSTS){
                    type = Item.TYPE_COSTS;
                }
                Intent intent = new Intent(MainActivity.this,AddItemActivity.class);
                intent.putExtra(AddItemActivity.TYPE_KEY,type);
                  startActivityForResult(intent,ADD_ITEM_REQUEST_CODE);
            }
        });

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for(Fragment fragment :getSupportFragmentManager().getFragments()){
            fragment.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
           case MainPagesAdapter.PAGE_INCOMES:
               case MainPagesAdapter.PAGE_COSTS:
            mFab.show();
            break;
            case MainPagesAdapter.PAGE_BALANSE:
                mFab.hide();
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {
        switch (i){
            case ViewPager.SCROLL_STATE_IDLE:
                mFab.setEnabled(true);
                break;
                case ViewPager.SCROLL_STATE_DRAGGING:
                    case ViewPager.SCROLL_STATE_SETTLING:
                        mFab.setEnabled(false);
                        break;
        }
    }

    @Override
    public void onSupportActionModeStarted(@NonNull ActionMode mode) {
        super.onSupportActionModeStarted(mode);
        mFab.hide();
        mActionMode = mode;
        }

    @Override
    public void onSupportActionModeFinished(@NonNull ActionMode mode) {
        super.onSupportActionModeFinished(mode);
        mFab.show();
        mActionMode = null;
    }
    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(this,AuthActivity.class);
        startActivity(intent);
    }
}

