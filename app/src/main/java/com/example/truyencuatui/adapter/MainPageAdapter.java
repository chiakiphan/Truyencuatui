package com.example.truyencuatui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.truyencuatui.fragment.main_page.Fragment_Follow;
import com.example.truyencuatui.fragment.main_page.Fragment_Hotbook;
import com.example.truyencuatui.fragment.main_page.Fragment_Newbook;

public class MainPageAdapter extends FragmentStatePagerAdapter {

    public MainPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new Fragment_Newbook();
                break;
            case 1:
                frag = new Fragment_Follow();
                break;
            case 2:
                frag = new Fragment_Hotbook();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "TRUYỆN MỚI";
                break;
            case 1:
                title = "THEO DÕI";
                break;
            case 2:
                title = "TRUYỆN HOT";
                break;
        }
        return title;
    }
}