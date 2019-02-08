package com.loftschool;

import com.google.gson.annotations.SerializedName;

public class BalanseResult {
    public String status;
    @SerializedName("total_expenses")
    public int expense;
    @SerializedName("total_income")
    public int income;
}
