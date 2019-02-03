package com.loftschool;

public interface ItemsAdapterListener {
    void OnItemClick(Item item, int position);
    void OnItemLongClick(Item item, int position);
}
