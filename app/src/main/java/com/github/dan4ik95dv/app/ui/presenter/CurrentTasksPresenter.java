package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.model.task.TasksResponse;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.adapter.CurrentTaskAdapter;
import com.github.dan4ik95dv.app.ui.view.CurrentTasksMvpView;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CurrentTasksPresenter implements Presenter<CurrentTasksMvpView> {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RestInterface restInterface;

    @Inject
    Realm realm;


    private CurrentTaskAdapter mTaskAdapter;
    private CurrentTasksMvpView currentTasksMvpView;
    RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            currentTasksMvpView.hideProgress();
        }
    };


    private Context context;
    private BaseActivity activity;
    private Boolean hasNext = true;
    private String token;

    public CurrentTasksPresenter(Context context) {
        this.context = context;
        this.activity = (BaseActivity) context;
        App.getInstance().getClientComponent().inject(this);
        this.token = sharedPreferences.getString(Constants.Configs.TOKEN, null);
    }

    @Override
    public void attachView(CurrentTasksMvpView view) {
        this.currentTasksMvpView = view;
    }

    @Override
    public void detachView() {
        this.currentTasksMvpView = null;
    }

    public CurrentTaskAdapter getAdapter() {
        return mTaskAdapter;
    }


    public void init() {
        mTaskAdapter = new CurrentTaskAdapter(context, currentTasksMvpView.getTasksRecyclerView());
        mTaskAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        
        getCurrentTasks();
    }

    public SwipeRefreshLayout.OnRefreshListener getSwipeRefreshLayoutListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCurrentTasks();
            }
        };
    }


    private void getCurrentTasks() {
        if (token != null) {
            restInterface.getUserTasks(token, 0, 999).enqueue(new Callback<TasksResponse>() {
                @Override
                public void onResponse(Call<TasksResponse> call, Response<TasksResponse> response) {
                    if (response.isSuccessful()) {
                        currentTasksMvpView.hideProgress();
                        mTaskAdapter.setTaskList(response.body().getTasks());
                    } else {
                        currentTasksMvpView.showError();
                    }
                }

                @Override
                public void onFailure(Call<TasksResponse> call, Throwable t) {
                    currentTasksMvpView.showError();
                }
            });
        }
    }
}
