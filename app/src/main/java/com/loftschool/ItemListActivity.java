package com.loftschool;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {
    private static final String TAG = "ItemListActivity";
    private RecyclerView mRecyclerView;
    private ItemListAdapter mAdapter;
    private List<Record> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);


        mRecyclerView = findViewById(R.id.item_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter= new ItemListAdapter();
        createData();
        mRecyclerView.setAdapter(mAdapter);

    }

    private void createData() {
        mData.add(new Record("Milk",1));
        mData.add(new Record("Bread",4));
        mData.add(new Record("Eggs",3));
        mData.add(new Record("Water",1));
        mData.add(new Record("Bear",2));
       // mData.add(new Record("Chocolate", (float) 2.3));
        mData.add(new Record("asd",1));
        mData.add(new Record("Courses",10));
    }

    private class ItemListAdapter extends RecyclerView.Adapter<RecordViewHolder>{
        @NonNull
        @Override
        public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            Log.d(TAG, "onCreateViewHolder: "+viewGroup.getChildCount());
            View view = LayoutInflater.from(viewGroup.getContext())
                   .inflate(R.layout.item_record,viewGroup,false);
            return new RecordViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecordViewHolder recordViewHolder, int i) {
            Log.d(TAG, "onBindViewHolder: "+mRecyclerView.getChildCount()+" "+i);
            Record record=mData.get(i);
            recordViewHolder.applyData(record);
        }


        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class RecordViewHolder extends RecyclerView.ViewHolder{

        private final TextView mItemTitle;
        private final TextView mItemPrice;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemTitle=itemView.findViewById(R.id.title);
            mItemPrice=itemView.findViewById(R.id.price);
        }
        public void applyData(Record record){
            Log.d(TAG, "applyData: "+mRecyclerView.getChildLayoutPosition(itemView)+" "+record);
            mItemTitle.setText(record.getTitle());
            mItemPrice.setText(String.valueOf(record.getPrice()));
        }
    }
}
