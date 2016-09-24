package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.model.user.Level;
import com.github.dan4ik95dv.app.model.user.User;
import com.github.dan4ik95dv.app.ui.view.MainMvpView;


public class MainPresenter implements Presenter<MainMvpView> {

    private MainMvpView mainMvpView;

    public MainPresenter(Context context) {
        App.getInstance().getClientComponent().inject(this);
        attachView((MainMvpView) context);
    }


    @Override
    public void attachView(MainMvpView view) {
        this.mainMvpView = view;
    }

    @Override
    public void detachView() {
        this.mainMvpView = null;
    }

    public void init() {
        mainMvpView.fillHeaderView(getUserFixture());
    }


    private User getUserFixture() {
        User fixturaUser = new User();
        Level fixturaLevel = new Level();

        fixturaLevel.setId(1);
        fixturaLevel.setLevel(1);
        fixturaLevel.setStartCount(0);
        fixturaLevel.setEndCount(10);
        fixturaLevel.setName("Новичок");
        fixturaUser.setAvatar("https://cdn.zeplin.io/57e556f26282c2b426c815f7/assets/0DB324A9-C882-47F5-9E2F-2C515553D00F.png");
        fixturaUser.setFullName("Пупкин Василий");
        fixturaUser.setLevel(fixturaLevel);

        return fixturaUser;
    }

}
