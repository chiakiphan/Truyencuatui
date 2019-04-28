package com.example.truyencuatui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


import com.example.truyencuatui.layoutActivity.BookActivity;
import com.example.truyencuatui.layoutActivity.CategoryActivity;
import com.example.truyencuatui.layoutActivity.HistoryActivity;
import com.example.truyencuatui.layoutActivity.LoginActivity;
import com.example.truyencuatui.layoutActivity.RegisterActivity;
import com.example.truyencuatui.layoutActivity.ViewBookActivity;
import com.example.truyencuatui.ui.mainpage.SectionsMainPagerAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static int[] resourceIds = {
            R.layout.fragment_newbook
            ,R.layout.fragment_hotbook
            ,R.layout.fragment_followbook
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        SectionsMainPagerAdapter sectionsPagerAdapter = new SectionsMainPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_main_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.maintab);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:

                return true;
            case R.id.about:

                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent;
        int id = item.getItemId();
        if (id == R.id.nav_login) {
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_register) {
            intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_main) {
            intent = new Intent(MainActivity.this, BookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_newBook) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_calendar) {


            intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_categoryBook) {


            intent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_digioi) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_dothi) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_dowloaded) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_fullBook) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_history) {


            intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_hotBook) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_huyen) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_khoa) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_kiem) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_lichsu) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_quantruong) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tay) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_tienhiep) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_ngon) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_newBook) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
