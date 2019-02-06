package com.loftschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    private static final String TAG = "AddItemActivity";
    public static final String TYPE_KEY = "type";

    private EditText mItemName;
    private EditText mItemPrice;
    private Button mItemAdd;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.add_item_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mItemName=findViewById(R.id.item_name);
        mItemPrice=findViewById(R.id.item_price);
        mItemAdd=findViewById(R.id.item_add);

        type = getIntent().getStringExtra(TYPE_KEY);

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
        mItemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue = mItemName.getText().toString();
                float priceValue = Float.parseFloat((mItemPrice.getText().toString()));

                Intent intent = new Intent();
                Item item = new Item(nameValue,priceValue,type);
                intent.putExtra("item",item);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()
                == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
