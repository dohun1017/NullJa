package com.nullja.nullja;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private static int PAGE_NUMBER = 3;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return PageOneFrag.newInstance();
            case 1:
                return PageTwoFrag.newInstance();
            case 2:
                return PageThreeFrag.newInstance();
            default:
                return null;
        }
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "최근 핫플";
            case 1:
                return "주변 핫플";
            case 2:
                return "핫플 추가";
            default:
                return null;
        }
    }
}
