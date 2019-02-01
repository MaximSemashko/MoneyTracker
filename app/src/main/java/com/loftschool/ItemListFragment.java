package com.loftschool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
    private static final int ADD_ITEM_REQUEST_CODE = 123;
    private String type = TYPE_INCOMES;

    private Api mApi;
    private App mApp;

    private RecyclerView mRecyclerView;
    private ItemListAdapter mAdapter;
    private FloatingActionButton mFab;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter= new ItemListAdapter();

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
        mFab = view.findViewById(R.id.fab);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: FAB");
                Intent intent = new Intent(getContext(),AddItemActivity.class);
                intent.putExtra(AddItemActivity.TYPE_KEY,type);
                startActivityForResult(intent,ADD_ITEM_REQUEST_CODE);
            }
        });

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
        if (requestCode == ADD_ITEM_REQUEST_CODE
                && resultCode == Activity.RESULT_OK){
            Item item = (Item) data.getParcelableExtra("item");
            mAdapter.addItem(item);
            Log.i(TAG, "onActivityResult: "+item.name+" "+item.price);
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
//    @SuppressLint("StaticFieldLeak")
//    private void loadItems(){
//        AsyncTask<Void,Void,List<Item>> asyncTask = new AsyncTask<Void,Void,List<Item>>(){
//            @Override
//            protected List<Item> doInBackground(Void... voids) {
//                Call<List<Item>> call = mApi.getItems(type);
//                try {
//                    List<Item> items =  call.execute()
//                            .body();
//                    return items;
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            //main thread
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                Log.d(TAG, "onPreExecute: "+Thread.currentThread().getName());
//            }
//
//
//            @Override
//            protected void onPostExecute(List<Item> items) {
//                super.onPostExecute(items);
//                if(items!=null)
//                    mAdapter.setData(items);
//            }
//        };
//    asyncTask.execute();
//    }
//    private void loadItems() {
//        new LoadItemsTask(new Handler(callback)).start();
//    }
//
//    private final static int DATA_LOADED = 123;
//    private Handler.Callback callback = new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//         if(msg.what==DATA_LOADED){
//             List<Item> items = (List<Item>)msg.obj;
//             mAdapter.setData(items);
//         }
//         return true;
//        }
//    };
//
//    private class LoadItemsTask implements Runnable{
//
//        private Thread thread;
//        private Handler handler;
//
//        public LoadItemsTask(Handler handler){
//            thread = new Thread(new LoadItemsTask(new Handler()));
//            this.handler = handler;
//        }
//
//        public void start(){
//            thread.start();
//        }
//
//        @Override
//        public void run() {
//
//            Call<List<Item>> call = mApi.getItems(type);
//
//            try {
//                List<Item> items =  call.execute()
//                        .body();
//
//               handler.obtainMessage(DATA_LOADED,items).sendToTarget();
//
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}