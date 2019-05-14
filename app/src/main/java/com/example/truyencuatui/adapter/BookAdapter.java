package com.example.truyencuatui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.truyencuatui.model.Book;
import com.example.truyencuatui.R;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    private ArrayList<Book> listBook;
    private Activity activity;
    private static OnItemClickListener listener;

    public BookAdapter(ArrayList<Book> listBook, Activity activity) {
        this.listBook = listBook;
        this.activity = activity;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainpage, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(BookHolder holder, int position) {
        final Book book = listBook.get(position);
        holder.tv_name.setText(book.getName());

        String value = book.getImage();
        Glide.with(activity).load(value)


                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(300, 400)
                .centerCrop()
                .into(holder.img_Book);


    }

    @Override
    public int getItemCount() {
        return listBook.size();
    }

    class BookHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView img_Book;

        BookHolder(final View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_NewBook_namebook);
            img_Book = (ImageView) itemView.findViewById(R.id.img_NewBook_book);
            int imageWidth = initializeGridLayout();
            img_Book.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageWidth * 16 / 15));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }

    public  int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;

        return screenWidth;
    }
    private int initializeGridLayout() {
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1, activity.getResources().getDisplayMetrics());
        return (int) ((getScreenWidth(activity) - ((3 + 1) * padding)) / 3);

    }
}
