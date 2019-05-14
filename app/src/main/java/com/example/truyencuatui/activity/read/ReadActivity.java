package com.example.truyencuatui.activity.read;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.truyencuatui.R;
import com.example.truyencuatui.fragment.book_page.Fragment_Listbook;

public class ReadActivity extends AppCompatActivity {
    private TextView tv_Book;
    private WebView web;
    private int SWIPE_LONG = 100;
    private int SWIPE_SPEED = 100;
    private int position;
    final String mimeType = "text/html";
    final String encoding = "UTF-8";
    private String title;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Intent intent = getIntent();

        web = findViewById(R.id.web);
        position = intent.getIntExtra(Fragment_Listbook.CONTENT,1);
        String html = " "+Fragment_Listbook.listBook.get(position).getContent();
        web.loadDataWithBaseURL("", html, mimeType, encoding, "");

        title = Fragment_Listbook.listBook.get(position).getName();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        gestureDetector = new GestureDetector(this,new CatchEvent());
        web.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    class CatchEvent extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > SWIPE_LONG && Math.abs(velocityX) > SWIPE_SPEED){
                if(position < Fragment_Listbook.listBook.size()) position++;
                String html = " "+Fragment_Listbook.listBook.get(position).getContent();
                web.loadDataWithBaseURL("", html, mimeType, encoding, "");
                title = Fragment_Listbook.listBook.get(position).getName();
                getSupportActionBar().setTitle(title);
            }
            if(e2.getX() - e1.getX() > SWIPE_LONG && Math.abs(velocityX) > SWIPE_SPEED){
                if(position>0) position--;
                String html = " "+Fragment_Listbook.listBook.get(position).getContent();
                title = Fragment_Listbook.listBook.get(position).getName();
                web.loadDataWithBaseURL("", html, mimeType, encoding, "");
                getSupportActionBar().setTitle(title);
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
