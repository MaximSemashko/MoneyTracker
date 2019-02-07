package com.loftschool;

import com.loftschool.Api.Item;

public interface ItemsAdapterListener {
    void OnItemClick(Item item, int position);
    void OnItemLongClick(Item item, int position);
}
