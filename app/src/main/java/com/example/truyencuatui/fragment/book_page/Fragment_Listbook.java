package com.example.truyencuatui.fragment.book_page;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.truyencuatui.R;
import com.example.truyencuatui.activity.read.ReadActivity;
import com.example.truyencuatui.fragment.main_page.Fragment_Newbook;
import com.example.truyencuatui.activity.read.BookActivity;
import com.example.truyencuatui.model.BookDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_Listbook extends Fragment {
    private ListView list;
    public static String CONTENT="content";
    public static ArrayList<BookDetail> listBook;
    public static ArrayList<String> listChapter;
    public Fragment_Listbook() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listBook = new ArrayList<>();
        listChapter = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference data = firebaseDatabase.getReference("bookdetail");
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Đang tải");
        progressDialog.show();
        data.orderByChild("bookID").equalTo(BookActivity.bundle.getString(Fragment_Newbook.BOOKID)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Parse dataSnapshot
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    BookDetail book = ds.getValue(BookDetail.class);
                    String key = ds.getKey();
                    book.setIdDetail(key);
                    listBook.add(book);
                    listChapter.add(book.getName());
                }
                progressDialog.hide();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        SystemClock.sleep(1000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_listbook, container, false);
        list = view.findViewById(R.id.list_chapter);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,listChapter);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ReadActivity.class);
                intent.putExtra(CONTENT,position);
                startActivity(intent);

            }
        });
        return view;
    }

}
