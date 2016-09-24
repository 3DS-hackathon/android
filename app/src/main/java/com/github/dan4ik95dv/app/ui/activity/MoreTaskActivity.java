package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerMoreTaskComponent;
import com.github.dan4ik95dv.app.di.module.activity.MoreTaskModule;
import com.github.dan4ik95dv.app.ui.presenter.MoreTaskPresenter;
import com.github.dan4ik95dv.app.ui.view.MoreTaskMvpView;

import javax.inject.Inject;

public class MoreTaskActivity extends BaseActivity implements MoreTaskMvpView {

    @Inject
    MoreTaskPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMoreTaskComponent.builder().moreTaskModule(new MoreTaskModule(this)).build().inject(this);
        presenter.init();
        setContentView(R.layout.activity_more_task);
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

