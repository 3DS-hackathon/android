package com.github.dan4ik95dv.app.model.realm;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.RealmStringRealmProxy;

@Parcel(implementations = {RealmStringRealmProxy.class}, value = Parcel.Serialization.BEAN, analyze = RealmString.class)
public class RealmString extends RealmObject {
    private String value;

    public RealmString() {
    }

    public RealmString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
