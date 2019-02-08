package com.loftschool;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BalanseFragment extends Fragment {

    private TextView total;
    private TextView expense;
    private TextView income;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_balanse, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        total = view.findViewById(R.id.total);
        expense=view.findViewById(R.id.expense);
        income=view.findViewById(R.id.income);

        updateData();
    }

    private void updateData() {
        BalanseResult result = new BalanseResult();
        result.expense = 4500;
        result.income = 6000;

        total.setText(String.valueOf(result.income-result.expense));
        expense.setText(String.valueOf(result.expense));
        income.setText(String.valueOf(result.income));
    }
}
