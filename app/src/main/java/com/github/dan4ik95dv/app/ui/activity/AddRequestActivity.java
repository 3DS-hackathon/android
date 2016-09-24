package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerAddRequestComponent;
import com.github.dan4ik95dv.app.di.module.activity.AddRequestModule;
import com.github.dan4ik95dv.app.ui.adapter.PhotosAdapter;
import com.github.dan4ik95dv.app.ui.presenter.AddRequestPresenter;
import com.github.dan4ik95dv.app.ui.view.AddRequestMvpView;

import javax.inject.Inject;

public class AddRequestActivity extends BaseActivity implements AddRequestMvpView {

    @Inject
    AddRequestPresenter presenter;

    PhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAddRequestComponent.builder().addRequestModule(new AddRequestModule(this)).build().inject(this);
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

