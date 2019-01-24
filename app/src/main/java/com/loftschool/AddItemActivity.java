package com.loftschool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    private static final String TAG = "AddItemActivity";

    private EditText mItemName;
    private EditText mItemPrice;
    private Button mItemAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        mItemName=findViewById(R.id.item_name);
        mItemPrice=findViewById(R.id.item_price);
        mItemAdd=findViewById(R.id.item_add);

        setTitle(R.string.add_item_title);

        mItemName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mItemAdd.setEnabled(!TextUtils.isEmpty(mItemName.getText().toString())&&!TextUtils.isEmpty(mItemPrice.getText().toString()));
            }
        });

        mItemPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mItemAdd.setEnabled(!TextUtils.isEmpty(mItemName.getText().toString())&&!TextUtils.isEmpty(mItemPrice.getText().toString()));
            }
        });

    }
}
