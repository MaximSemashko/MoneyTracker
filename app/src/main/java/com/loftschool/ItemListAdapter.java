package com.loftschool;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.RecordViewHolder>{
    private List<Item> mData = new ArrayList<>();


    public ItemListAdapter() {
        createData();
    }

    @NonNull
    @Override
    public ItemListAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_record,viewGroup,false);
        return new ItemListAdapter.RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.RecordViewHolder recordViewHolder, int i) {
        Item item = mData.get(i);
        recordViewHolder.applyData(item);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void createData() {
        mData.add(new Item("Milk",1));
        mData.add(new Item("Bread",4));
        mData.add(new Item("Eggs",3));
        mData.add(new Item("Water",1));
        mData.add(new Item("Bear",2));
        mData.add(new Item("Chocolate", (float) 2.3));
        mData.add(new Item("asd",1));
        mData.add(new Item("Courses",10));
        mData.add(new Item("Milk",1));
        mData.add(new Item("Bread",4));
        mData.add(new Item("Eggs",3));
        mData.add(new Item("Water",1));
        mData.add(new Item("Bear",2));
        mData.add(new Item("Chocolate", (float) 2.3));
        mData.add(new Item("asd",1));
        mData.add(new Item("Courses",10));
        mData.add(new Item("Milk",1));
        mData.add(new Item("Bread",4));
        mData.add(new Item("Eggs",3));
        mData.add(new Item("Water",1));
        mData.add(new Item("Bear",2));
        mData.add(new Item("Chocolate", (float) 2.3));
        mData.add(new Item("asd",1));
        mData.add(new Item("Courses",10));
    }

     static class RecordViewHolder extends RecyclerView.ViewHolder{

        private final TextView mItemTitle;
        private final TextView mItemPrice;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemTitle=itemView.findViewById(R.id.record_title);
            mItemPrice=itemView.findViewById(R.id.record_price);
        }
        public void applyData(Item item){
            mItemTitle.setText(item.getTitle());
            mItemPrice.setText(String.valueOf(item.getPrice()));
        }
    }
}

