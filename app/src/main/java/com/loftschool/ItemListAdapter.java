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

    public void setData(List<Item> data){
      this.mData= data;
      notifyDataSetChanged();
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
            mItemTitle.setText(item.name);
            mItemPrice.setText(String.valueOf(item.price));
        }
    }
}

