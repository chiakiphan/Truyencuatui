package com.example.truyencuatui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.truyencuatui.activity.MainActivity;
import com.example.truyencuatui.R;
import com.example.truyencuatui.activity.read.BookActivity;
import com.example.truyencuatui.adapter.BookAdapter;
import com.example.truyencuatui.model.Book;

import java.util.ArrayList;

import static com.example.truyencuatui.activity.MainActivity.AUTHOR;
import static com.example.truyencuatui.activity.MainActivity.BOOKID;
import static com.example.truyencuatui.activity.MainActivity.BUNDLE;
import static com.example.truyencuatui.activity.MainActivity.DESCRIPTION;
import static com.example.truyencuatui.activity.MainActivity.IMG;
import static com.example.truyencuatui.activity.MainActivity.STATUS;
import static com.example.truyencuatui.activity.MainActivity.TITLE;
import static com.example.truyencuatui.activity.MainActivity.TRANS;

public class ViewBookActivity extends AppCompatActivity {
    private String value;
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        value = intent.getStringExtra(TITLE);
        getSupportActionBar().setTitle(value);

        recyclerView = findViewById(R.id.recycler_viewbook);
        RecyclerView.LayoutManager manager = new GridLayoutManager(ViewBookActivity.this,3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        getBook(convertValue(value));
    }
    public String convertValue(String s){
        switch (s){
            case "MỚI CẬP NHẬP":
                s = "TRUYỆN MỚI";
                break;
            case "TRUYỆN HOÀN THÀNH":
                s= "FULL";
                break;
            case "TRUYỆN THIẾU NHI":
                s = "16";
                break;
            case "TRUYỆN NGƯỜI LỚN" :
                s ="18+";
                break;
        }
        return s;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_book, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.search_book:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void getBook(String value){
        final ArrayList<Book> list = new ArrayList<>();
        for(Book book : MainActivity.listB){
            if(MainActivity.subString(book.getCategory().toUpperCase(),value) == 1 ){
                list.add(book);
            }
        }
        if(list.isEmpty()){
            for(Book book : MainActivity.listB){
                if(MainActivity.subString(book.getKind().toUpperCase(),value) == 1 ){
                    list.add(book);
                }
            }
        }
        bookAdapter = new BookAdapter(list,this);
        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(ViewBookActivity.this, BookActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(BOOKID, list.get(position).getIdBook());
                bundle.putString(TITLE,list.get(position).getName());
                bundle.putString(DESCRIPTION,list.get(position).getDescript());
                bundle.putString(TRANS,list.get(position).getTeamTrans());
                bundle.putString(AUTHOR,list.get(position).getAuthor());
                bundle.putString(STATUS,list.get(position).getStatus());
                bundle.putString(IMG,list.get(position).getImage());
                intent.putExtra(BUNDLE,bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(bookAdapter);
        bookAdapter.notifyDataSetChanged();
    }

}
