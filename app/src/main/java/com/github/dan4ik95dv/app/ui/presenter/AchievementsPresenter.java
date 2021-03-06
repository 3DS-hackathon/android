package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.model.Department;
import com.github.dan4ik95dv.app.model.achievement.AchievementsResponse;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.adapter.AchievementAdapter;
import com.github.dan4ik95dv.app.ui.view.AchievementsMvpView;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AchievementsPresenter implements Presenter<AchievementsMvpView> {
    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RestInterface restInterface;

    @Inject
    Realm realm;

    private AchievementAdapter mAchievementAdapter;
    private AchievementsMvpView achievementsMvpView;
    RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            achievementsMvpView.hideProgress();
        }
    };
    private String token;
    private Context context;
    private BaseActivity activity;
    private Department department;
    private Boolean hasNext = true;

    public AchievementsPresenter(Context context) {
        this.context = context;
        this.activity = (BaseActivity) context;
        App.getInstance().getClientComponent().inject(this);
        this.token = sharedPreferences.getString(Constants.Configs.TOKEN, null);
    }

    @Override
    public void attachView(AchievementsMvpView view) {
        this.achievementsMvpView = view;
    }

    @Override
    public void detachView() {
        this.achievementsMvpView = null;
    }

    public AchievementAdapter getAdapter() {
        return mAchievementAdapter;
    }


    public void init() {
        mAchievementAdapter = new AchievementAdapter(context, achievementsMvpView.getAchievementsRecyclerView());
        mAchievementAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        getAchievements();
    }


    public SwipeRefreshLayout.OnRefreshListener getSwipeRefreshLayoutListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAchievements();
            }
        };
    }


    private void getAchievements() {
        if (token != null) {
            restInterface.getAchievements(token, 0, 999).enqueue(new Callback<AchievementsResponse>() {
                @Override
                public void onResponse(Call<AchievementsResponse> call, Response<AchievementsResponse> response) {
                    if (response.isSuccessful()) {
                        achievementsMvpView.hideProgress();
                        mAchievementAdapter.setAchievementList(response.body().getAchievements());
                    } else {
                        achievementsMvpView.showError();
                    }
                }

                @Override
                public void onFailure(Call<AchievementsResponse> call, Throwable t) {
                    achievementsMvpView.showError();
                }
            });
        }
    }

}
