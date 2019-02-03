package com.loftschool;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import static com.loftschool.Item.TYPE_COSTS;
import static com.loftschool.Item.TYPE_INCOMES;

public class MainPagesAdapter extends FragmentPagerAdapter {
    public static final int PAGE_INCOMES = 0;
    public static final int PAGE_COSTS = 1;
    public static final int PAGE_BALANSE = 2;

    private String [] mTabTitles;

    public MainPagesAdapter(FragmentManager fm, Context context) {
        super(fm);

        mTabTitles=context.getResources().getStringArray(R.array.tab_title);

    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case PAGE_INCOMES:{
               return ItemListFragment.createItemsFragment(TYPE_INCOMES);
            }
            case PAGE_COSTS:{
                return ItemListFragment.createItemsFragment(TYPE_COSTS);
            }
            case PAGE_BALANSE:{
                return new BalanseFragment();
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
