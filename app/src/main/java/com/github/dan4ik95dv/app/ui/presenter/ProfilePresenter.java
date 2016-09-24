package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.model.Department;
import com.github.dan4ik95dv.app.model.user.Level;
import com.github.dan4ik95dv.app.model.user.User;
import com.github.dan4ik95dv.app.ui.view.ProfileMvpView;


public class ProfilePresenter implements Presenter<ProfileMvpView> {

    private ProfileMvpView profileMvpView;

    public ProfilePresenter(Context context) {
        App.getInstance().getClientComponent().inject(this);
        attachView((ProfileMvpView) context);

    }


    @Override
    public void attachView(ProfileMvpView view) {
        this.profileMvpView = view;
    }

    @Override
    public void detachView() {
        this.profileMvpView = null;
    }

    public void init() {
        profileMvpView.fillUserProfile(getUserFixture());
    }

    private User getUserFixture() {
        User fixturaUser = new User();
        Level fixturaLevel = new Level();
        Department fixturaDepartment = new Department();
        fixturaDepartment.setName("Отдел инновационных технологий");
        fixturaDepartment.setId(1);
        fixturaDepartment.setDesc("Очень очень секретный отдел");
        fixturaDepartment.setRating(10);
        fixturaLevel.setId(1);
        fixturaLevel.setLevel(1);
        fixturaLevel.setStartCount(0);
        fixturaLevel.setEndCount(10);
        fixturaUser.setBalance(100);
        fixturaUser.setDepartment(fixturaDepartment);
        fixturaLevel.setName("Новичок");
        fixturaUser.setAvatar("https://cdn.zeplin.io/57e556f26282c2b426c815f7/assets/0DB324A9-C882-47F5-9E2F-2C515553D00F.png");
        fixturaUser.setFullName("Пупкин Василий");
        fixturaUser.setLevel(fixturaLevel);

        return fixturaUser;
    }


}
