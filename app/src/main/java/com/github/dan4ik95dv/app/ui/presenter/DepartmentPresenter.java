package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;

import com.github.dan4ik95dv.app.ui.view.DepartmentMvpView;


public class DepartmentPresenter implements Presenter<DepartmentMvpView> {

    private DepartmentMvpView departmentMvpView;

    public DepartmentPresenter(Context context) {
        attachView((DepartmentMvpView) context);
    }

    @Override
    public void attachView(DepartmentMvpView view) {
        this.departmentMvpView = view;
    }

    @Override
    public void detachView() {
        this.departmentMvpView = null;
    }

    public void init() {

    }

}
