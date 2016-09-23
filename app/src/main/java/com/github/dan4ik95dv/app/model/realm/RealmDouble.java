package com.github.dan4ik95dv.app.model.realm;

import org.parceler.Parcel;

import io.realm.RealmDoubleRealmProxy;
import io.realm.RealmObject;

@Parcel(implementations = {RealmDoubleRealmProxy.class}, value = Parcel.Serialization.BEAN, analyze = RealmDouble.class)
public class RealmDouble extends RealmObject {
    private Double value;

    public RealmDouble() {
    }

    public RealmDouble(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


}