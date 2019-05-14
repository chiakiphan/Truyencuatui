package com.example.truyencuatui.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;

import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.truyencuatui.R;
import com.example.truyencuatui.activity.read.BookActivity;
import com.example.truyencuatui.activity.read.ReadActivity;
import com.example.truyencuatui.activity.task.MyAsync;
import com.example.truyencuatui.activity.user.LoginActivity;
import com.example.truyencuatui.activity.user.RegisterActivity;
import com.example.truyencuatui.adapter.BookAdapter;
import com.example.truyencuatui.adapter.MainPageAdapter;
import com.example.truyencuatui.model.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String TITLE="title",DESCRIPTION="descript",BOOKID="idbook",IMG="img",AUTHOR="author",TRANS="team",STATUS="status";
    public static String KIND="kind",LINK="link";
    public static ArrayList<Book> listB ;
    private BookAdapter bookAdapter;
    public static String BUNDLE="Bundle";
    MyAsync myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        MainActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(MainActivity.this);

        ViewPager viewPager = findViewById(R.id.view_main_pager);
        TabLayout tabs = findViewById(R.id.maintab);
        FragmentManager manager = getSupportFragmentManager();
        MainPageAdapter adapter = new MainPageAdapter(manager);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        getAllBook();

    }

    private void getAllBook() {
        listB = new ArrayList<>();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("book");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Parse dataSnapshot
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Book book = ds.getValue(Book.class);
                    String key = ds.getKey();
                    book.setIdBook(key);
                    listB.add(book);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static int subString(String test, String text){
        String []s = test.split(",");
        for(String k: s){
            if(k.equals(text)) return 1;
        }
        return 0;
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
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:

                return true;
            case R.id.about:
                Toast.makeText(this,"Đăng nhập để sử dụng chức năng này",Toast.LENGTH_SHORT).show();
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

        } else if (id == R.id.nav_newBook) {

            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"MỚI CẬP NHẬP");
            startActivity(intent);
        } else if (id == R.id.nav_categoryBook) {


            intent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_digioi) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"DỊ GIỚI");
            startActivity(intent);
        } else if (id == R.id.nav_dothi) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"ĐÔ THỊ");
            startActivity(intent);
        } else if (id == R.id.nav_dowloaded) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"TRUYỆN ĐÃ TẢI");
            startActivity(intent);
        }else if (id == R.id.nav_fullBook) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"TRUYỆN HOÀN THÀNH");
            startActivity(intent);
        } else if (id == R.id.nav_13) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"TRUYỆN THIẾU NHI");
            startActivity(intent);
        }else if (id == R.id.nav_18) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"TRUYỆN NGƯỜI LỚN");
            startActivity(intent);
        }else if (id == R.id.nav_history) {


            intent = new Intent(MainActivity.this, HistoryActivity.class);
            intent.putExtra(TITLE,"LỊCH SỬ ĐỌC");
            startActivity(intent);
        } else if (id == R.id.nav_hotBook) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"TRUYỆN HOT");
            startActivity(intent);
        }else if (id == R.id.nav_huyen) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"HUYỀN HUYỄN");
            startActivity(intent);
        } else if (id == R.id.nav_khoa) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"KHOA HUYỄN");
            startActivity(intent);
        } else if (id == R.id.nav_kiem) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"KIẾM HIỆP");
            startActivity(intent);
        }else if (id == R.id.nav_lichsu) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"LỊCH SỬ");
            startActivity(intent);
        } else if (id == R.id.nav_quantruong) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"QUAN TRƯỜNG");
            startActivity(intent);
        } else if (id == R.id.nav_tay) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"PHƯƠNG TÂY");
            startActivity(intent);
        }else if (id == R.id.nav_tienhiep) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"TIÊN HIỆP");
            startActivity(intent);
        } else if (id == R.id.nav_ngon) {


            intent = new Intent(MainActivity.this, ViewBookActivity.class);
            intent.putExtra(TITLE,"NGÔN TÌNH");
            startActivity(intent);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
