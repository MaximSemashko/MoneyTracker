package com.loftschool;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loftschool.Api.Item;

import java.util.ArrayList;
import java.util.List;

class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.RecordViewHolder>{
    private List<Item> mData = new ArrayList<>();
    private ItemsAdapterListener listener = null;

    public void setListener(ItemsAdapterListener listener){
        this.listener = listener;

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
        recordViewHolder.applyData(item,i,listener,selections.get(i,false));
    }

    public void addItem(Item item){
        mData.add(item);
        notifyItemInserted(mData.size());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<Item> data){
      this.mData= data;
      notifyDataSetChanged();
    }

    private SparseBooleanArray selections = new SparseBooleanArray();


    public void toggleSelection(int position) {
        if (selections.get(position, false)) {
            selections.delete(position);
        } else {
            selections.put(position, true);
        }
        notifyItemChanged(position);
    }

    void clearSelections() {
        selections.clear();
        notifyDataSetChanged();
    }

    int getSelectedItemCount() {
        return selections.size();
    }

    List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selections.size());
        for (int i = 0; i < selections.size(); i++) {
            items.add(selections.keyAt(i));
        }
        return items;
    }

    Item remove(int pos) {
        final Item item = mData.remove(pos);
        notifyItemRemoved(pos);
        return item;
    }


    static class RecordViewHolder extends RecyclerView.ViewHolder{

        private final TextView mItemTitle;
        private final TextView mItemPrice;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemTitle=itemView.findViewById(R.id.record_title);
            mItemPrice=itemView.findViewById(R.id.record_price);
        }
        public void applyData(final Item item, final int position, final ItemsAdapterListener listener, boolean selection){
            mItemTitle.setText(item.name);
            mItemPrice.setText(String.valueOf(item.price));


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                        listener.OnItemClick(item,position);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                   if(listener != null){
                       listener.OnItemLongClick(item,position) ;
                   }
                   return true;
                }
            });

            itemView.setActivated(selection);
        }
    }
}

