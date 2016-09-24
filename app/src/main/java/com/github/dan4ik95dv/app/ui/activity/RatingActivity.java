package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerRatingComponent;
import com.github.dan4ik95dv.app.di.module.activity.RatingModule;
import com.github.dan4ik95dv.app.ui.presenter.RatingPresenter;
import com.github.dan4ik95dv.app.ui.view.RatingMvpView;

import javax.inject.Inject;

public class RatingActivity extends BaseActivity implements RatingMvpView {

    @Inject
    RatingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerRatingComponent.builder().ratingModule(new RatingModule(this)).build().inject(this);
        presenter.init();
        setContentView(R.layout.activity_rating);
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

