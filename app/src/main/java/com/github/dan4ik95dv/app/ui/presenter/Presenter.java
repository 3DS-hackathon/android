package com.github.dan4ik95dv.app.ui.presenter;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}