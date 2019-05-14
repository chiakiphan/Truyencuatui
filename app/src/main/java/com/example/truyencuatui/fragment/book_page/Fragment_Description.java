package com.example.truyencuatui.fragment.book_page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.truyencuatui.R;
import com.example.truyencuatui.activity.MainActivity;
import com.example.truyencuatui.fragment.main_page.Fragment_Newbook;
import com.example.truyencuatui.activity.read.BookActivity;

public class Fragment_Description extends Fragment {
    private TextView tv_Author,tv_Team,tv_Des,tv_Status,tv_Kind;
    private ImageView img;
    public Fragment_Description() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookdetail, container, false);
        tv_Author = view.findViewById(R.id.tv_Bookdetail_author);
        tv_Des = view.findViewById(R.id.tv_Bookdetail_descript);
        tv_Status = view.findViewById(R.id.tv_Bookdetail_status);
        tv_Team = view.findViewById(R.id.tv_Bookdetail_team);
        img = view.findViewById(R.id.img_Bookdetail_face);
        tv_Kind = view.findViewById(R.id.tv_Bookdetail_kind);

        Glide.with(getActivity()).load(BookActivity.bundle.getString(Fragment_Newbook.IMG))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(300, 300)
                .centerCrop()
                .into(img);

        tv_Author.setText(BookActivity.bundle.getString(Fragment_Newbook.AUTHOR));
        tv_Team.setText(BookActivity.bundle.getString(Fragment_Newbook.TRANS));
        tv_Status.setText(BookActivity.bundle.getString(Fragment_Newbook.STATUS));
        tv_Des.setText(BookActivity.bundle.getString(Fragment_Newbook.DESCRIPTION));
        tv_Kind.setText(BookActivity.bundle.getString(MainActivity.KIND));
        return view;
    }

}
