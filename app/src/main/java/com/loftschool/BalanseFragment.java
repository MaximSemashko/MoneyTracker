package com.loftschool;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loftschool.Api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanseFragment extends Fragment {
    private static final String TAG = "BalanseFragment";
    private TextView total;
    private TextView expense;
    private DiagramView mDiagramView;
    private TextView income;

    private Api api;
    private App app;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (App) getActivity().getApplication();
        api = app.getApi();
    }
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
        mDiagramView = view.findViewById(R.id.diagram);
        updateData();
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
//            Log.i(TAG, "setUserVisibleHint: ");
//            updateData();
//        }
//    }

    private void updateData() {

        Call<BalanseResult> call = api.balance();

        call.enqueue(new Callback<BalanseResult>() {
            @Override
            public void onResponse(Call<BalanseResult> call, Response<BalanseResult> response) {
                BalanseResult result = response.body();

                total.setText( result.income - result.expense);
                expense.setText(result.expense);
                income.setText( result.income);
                mDiagramView.update(result.income, result.expense);
            }

            @Override
            public void onFailure(Call<BalanseResult> call, Throwable t) {

            }
        });


    }
}
