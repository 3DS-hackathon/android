package com.github.dan4ik95dv.app.model.realm;

import org.parceler.Parcel;

import io.realm.RealmIntegerRealmProxy;
import io.realm.RealmObject;

@Parcel(implementations = {RealmIntegerRealmProxy.class}, value = Parcel.Serialization.BEAN, analyze = RealmInteger.class)
public class RealmInteger extends RealmObject {
    private Integer value;

    public RealmInteger() {
    }

    public RealmInteger(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


}