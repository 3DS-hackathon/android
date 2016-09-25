package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.model.Department;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.adapter.AchievementAdapter;
import com.github.dan4ik95dv.app.ui.view.AchievementsDepartmentsMvpView;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AchievementsDepartmentsPresenter implements Presenter<AchievementsDepartmentsMvpView> {
    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RestInterface restInterface;

    @Inject
    Realm realm;

    private AchievementAdapter mAchievementAdapter;
    private AchievementsDepartmentsMvpView achievementsDepartmentsMvpView;

    RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            achievementsDepartmentsMvpView.hideProgress();
        }
    };

    private String token;
    private Context context;
    private BaseActivity activity;
    private Department department;
    private Boolean hasNext = true;

    public AchievementsDepartmentsPresenter(Context context) {
        this.context = context;
        this.activity = (BaseActivity) context;
        App.getInstance().getClientComponent().inject(this);
        this.token = sharedPreferences.getString(Constants.Configs.TOKEN, null);
    }

    @Override
    public void attachView(AchievementsDepartmentsMvpView view) {
        this.achievementsDepartmentsMvpView = view;
    }


    @Override
    public void detachView() {
        this.achievementsDepartmentsMvpView = null;
    }

    public AchievementAdapter getAdapter() {
        return mAchievementAdapter;
    }


    public void init() {
        mAchievementAdapter = new AchievementAdapter(context, achievementsDepartmentsMvpView.getAchievementsRecyclerView());
        mAchievementAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        getAchievementsDepartment();
    }


    public SwipeRefreshLayout.OnRefreshListener getSwipeRefreshLayoutListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAchievementsDepartment();
            }
        };
    }


    private void getAchievementsDepartment() {
        if (token != null) {
            restInterface.getDepartment(token).enqueue(new Callback<Department>() {
                @Override
                public void onResponse(Call<Department> call, Response<Department> response) {
                    if (response.isSuccessful()) {
                        department = response.body();

                    } else {
                        achievementsDepartmentsMvpView.showError();
                    }
                }

                @Override
                public void onFailure(Call<Department> call, Throwable t) {
                    achievementsDepartmentsMvpView.showError();
                }
            });
        }
    }

}
