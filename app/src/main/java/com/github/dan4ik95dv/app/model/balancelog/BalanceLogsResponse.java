package com.github.dan4ik95dv.app.model.balancelog;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

public class BalanceLogsResponse {
    @SerializedName("count")
    Integer count;

    @SerializedName("results")
    RealmList<BalanceLog> balanceLogs;

    public RealmList<BalanceLog> getBalanceLogs() {
        return balanceLogs;
    }

}
