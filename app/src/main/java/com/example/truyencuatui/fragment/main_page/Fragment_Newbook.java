package com.example.truyencuatui.fragment.main_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truyencuatui.activity.MainActivity;
import com.example.truyencuatui.adapter.BookAdapter;
import com.example.truyencuatui.model.Book;
import com.example.truyencuatui.R;
import com.example.truyencuatui.activity.read.BookActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_Newbook extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    public static String TITLE="title",DESCRIPTION="descript",BOOKID="idbook",IMG="img",AUTHOR="author",TRANS="team",STATUS="status";
    public static String BUNDLE="Bundle";
    public Fragment_Newbook(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newbook,container,false);
        recyclerView = view.findViewById(R.id.recycler_newbook);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        getAllBook();
        return view;
    }
    public void getAllBook(){
        final ArrayList<Book> list = new ArrayList<>();
        for(Book book : MainActivity.listB){
            if(MainActivity.subString(book.getCategory(),"Truyện mới") == 1){
                list.add(book);
            }
        }
        bookAdapter = new BookAdapter(list,getActivity());
        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(getActivity(), BookActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(BOOKID, list.get(position).getIdBook());
                bundle.putString(TITLE,list.get(position).getName());
                bundle.putString(DESCRIPTION,list.get(position).getDescript());
                bundle.putString(TRANS,list.get(position).getTeamTrans());
                bundle.putString(AUTHOR,list.get(position).getAuthor());
                bundle.putString(STATUS,list.get(position).getStatus());
                bundle.putString(MainActivity.KIND,list.get(position).getKind());
                bundle.putString(MainActivity.LINK,list.get(position).getLinkDowload());
                bundle.putString(IMG,list.get(position).getImage());
                intent.putExtra(BUNDLE,bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(bookAdapter);
        bookAdapter.notifyDataSetChanged();
    }

}
