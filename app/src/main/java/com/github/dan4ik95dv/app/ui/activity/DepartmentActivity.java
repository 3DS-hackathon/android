package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerDepartmentComponent;
import com.github.dan4ik95dv.app.di.module.activity.DepartmentModule;
import com.github.dan4ik95dv.app.ui.presenter.DepartmentPresenter;
import com.github.dan4ik95dv.app.ui.view.DepartmentMvpView;

import javax.inject.Inject;

public class DepartmentActivity extends BaseActivity implements DepartmentMvpView {

    @Inject
    DepartmentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDepartmentComponent.builder().departmentModule(new DepartmentModule(this)).build().inject(this);
        presenter.init();
        setContentView(R.layout.activity_department);
    }


    @Override
    public void showError() {
        showErrorInternetDialog(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}

