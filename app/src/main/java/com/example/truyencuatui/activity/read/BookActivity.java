package com.example.truyencuatui.activity.read;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.truyencuatui.R;
import com.example.truyencuatui.fragment.book_page.Fragment_Description;
import com.example.truyencuatui.fragment.main_page.Fragment_Newbook;
import com.example.truyencuatui.adapter.PagerAdapter;

public class BookActivity extends AppCompatActivity{
    public static Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);

        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(Fragment_Newbook.BUNDLE);

        android.support.v7.widget.Toolbar t =  findViewById(R.id.toolbar_book);
        setSupportActionBar(t);
        getSupportActionBar().setTitle(bundle.getString(Fragment_Newbook.TITLE));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}