package com.example.truyencuatui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.truyencuatui.fragment.book_page.Fragment_Description;
import com.example.truyencuatui.fragment.book_page.Fragment_Listbook;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new Fragment_Description();
                break;
            case 1:
                frag = new Fragment_Listbook();
                break;

        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "GIỚI THIỆU";
                break;
            case 1:
                title = "DS CHƯƠNG";
                break;

        }
        return title;
    }
}