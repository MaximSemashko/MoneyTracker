package com.loftschool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.loftschool.Item.TYPE_INCOMES;
import static com.loftschool.Item.TYPE_UNKNOWN;

public class ItemListFragment extends Fragment {

    private static final String TAG = "ItemListFragment";
    public static ItemListFragment createItemsFragment(String type){
        ItemListFragment fragment = new ItemListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE_KEY, TYPE_INCOMES);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static final String TYPE_KEY = "type";
    public static final int ADD_ITEM_REQUEST_CODE = 123;
    private String type = TYPE_INCOMES;

    private Api mApi;
    private App mApp;

    private RecyclerView mRecyclerView;
    private ItemListAdapter mAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter= new ItemListAdapter();
        mAdapter.setListener(new AdapterListener());

        Bundle bundle=getArguments();
        type = bundle.getString(TYPE_KEY,TYPE_UNKNOWN);

        if(type.equals(TYPE_UNKNOWN))
            throw new IllegalArgumentException("Unknown type");

        mApp = (App) getActivity().getApplication();

        mApi = mApp.getApi();
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


        swipeRefreshLayout = view.findViewById(R.id.refresh);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.CYAN,Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        loadItems();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Item item = (Item) data.getParcelableExtra("item");
            if(item.type.equals(type)) {
                mAdapter.addItem(item);
            }
        }
    }

    private void loadItems(){
        Call<List<Item>> call = mApi.getItems(type);
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                mAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    // ACTION MODE

    private ActionMode mActionMode;

    private void removeSelectedItems(){
        for(int i = mAdapter.getSelectedItems().size()-1;i>=0;i--)
            mAdapter.remove(mAdapter.getSelectedItems().get(i));
        mActionMode.finish();
    }

    private class AdapterListener implements ItemsAdapterListener{

        @Override
        public void OnItemClick(Item item, int position) {
        if(isInActionMode())
            toogleSelection(position);
        }

        @Override
        public void OnItemLongClick(Item item, int position) {
            if(isInActionMode()) {
                return;
            }
            mActionMode =  ((AppCompatActivity) getActivity()).startSupportActionMode(mCallback);
            toogleSelection(position);
        }

        private void toogleSelection(int position){
           mAdapter.toogleSelection(position);
        }

        private boolean isInActionMode(){
            return mActionMode!=null;
        }
    }

    private ActionMode.Callback mCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = new MenuInflater(getContext());
            inflater.inflate(R.menu.items_menu,menu);
            mActionMode = actionMode;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.remove:
                    showDialog();
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mAdapter.clearSelection();
            mActionMode = null;
        }
    };

    private void showDialog() {
        ConfiramtionDialog confiramtionDialog =new ConfiramtionDialog();
        confiramtionDialog.show(getFragmentManager(),"Confiramtion dialog");
    }
}