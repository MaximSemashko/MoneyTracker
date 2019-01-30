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

import java.util.ArrayList;
import java.util.List;

public class ItemListFragment extends Fragment {

    public static ItemListFragment createItemsFragment(int type){
        ItemListFragment fragment = new ItemListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE_KEY, TYPE_INCOMES);
        fragment.setArguments(bundle);
        return fragment;
    }
    public static final int TYPE_INCOMES = 1;
    public static final int TYPE_COSTS = 2;
    public static final int TYPE_BALANSE = 3;
    public static final int TYPE_UNKNOWN = -1;
    public static final String TYPE_KEY = "type";
    private int type = TYPE_INCOMES;

    private RecyclerView mRecyclerView;
    private ItemListAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter= new ItemListAdapter();

        Bundle bundle=getArguments();
        type = bundle.getInt(TYPE_KEY,TYPE_UNKNOWN);

        if(type==TYPE_UNKNOWN)
            throw new IllegalArgumentException("Unknown type");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.item_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        loadItems();
    }

    private void loadItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("adsj",34));
        mAdapter.setData(items);
    }
}