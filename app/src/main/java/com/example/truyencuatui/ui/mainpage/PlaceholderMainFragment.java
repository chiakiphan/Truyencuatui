package com.example.truyencuatui.ui.mainpage;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truyencuatui.ui.main.PageViewModel;

import static com.example.truyencuatui.layoutActivity.BookActivity.resourceIds;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderMainFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderMainFragment newInstance(int index) {
        PlaceholderMainFragment fragment = new PlaceholderMainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        int index = getArguments().getInt(ARG_SECTION_NUMBER);
        View rootView = inflater.inflate(resourceIds[index], container, false);
        return rootView;

    }
}