package com.error.grrravity.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.error.grrravity.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageFragment extends Fragment {

    // IDs
    @BindView(R.id.fragment_main_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.fragment_page_title) TextView mTextView;



    // Keys for bundle
    private static final String KEY_POSITION="position";


    public PageFragment() { }


    // Create a new instance of PageFragment, and add data to its bundle.
    public static PageFragment newInstance(int position) {

        // Create new fragment
        PageFragment frag = new PageFragment();

        // Create bundle and add it some data
        // TODO : add other data.
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Get layout of PageFragment
        View result = inflater.inflate(R.layout.fragment_page, container, false);

        // Get widgets from layout and serialise it
        LinearLayout rootView= (LinearLayout) result.findViewById(R.id.fragment_page_rootview);
        TextView textView= (TextView) result.findViewById(R.id.fragment_page_title);

        // Get data from Bundle (created in method newInstance)
        int position = 0;
        if (getArguments() != null) {
            position = getArguments().getInt(KEY_POSITION, -1);
        }

        // Update widgets with it
        textView.setText("Page numéro "+position);

        Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+position);

        return result;
    }

}