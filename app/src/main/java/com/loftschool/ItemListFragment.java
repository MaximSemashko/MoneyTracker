package com.loftschool;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ItemListAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items,container,false);
        mRecyclerView = view.findViewById(R.id.item_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter= new ItemListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}