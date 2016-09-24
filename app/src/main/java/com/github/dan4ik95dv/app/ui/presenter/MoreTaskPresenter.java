package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.model.task.Task;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.view.MoreTaskMvpView;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoreTaskPresenter implements Presenter<MoreTaskMvpView> {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RestInterface restInterface;

    @Inject
    Realm realm;

    private BaseActivity activity;
    private String token;
    private MoreTaskMvpView moreTaskMvpView;
    private Task task;
    private Integer taskId;

    public MoreTaskPresenter(Context context) {
        attachView((MoreTaskMvpView) context);
        App.getInstance().getClientComponent().inject(this);
        this.activity = (BaseActivity) context;
        this.token = sharedPreferences.getString(Constants.Configs.TOKEN, null);
    }

    @Override
    public void attachView(MoreTaskMvpView view) {
        this.moreTaskMvpView = view;
    }

    @Override
    public void detachView() {
        this.moreTaskMvpView = null;
    }

    public void init() {
        if (activity.getIntent() != null)
            taskId = activity.getIntent().getIntExtra(Constants.TASK_ID, -1);
        if (taskId != -1) {
            task = realm.where(Task.class).equalTo("id", taskId).findFirst();
            moreTaskMvpView.fillTask(task);
        }
    }


    public void joinTask() {
        restInterface.joinTask(token, taskId).enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(activity, "Задача успешно добавлена!", Toast.LENGTH_SHORT).show();
                    activity.finish();
                } else {
                    moreTaskMvpView.showError();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                moreTaskMvpView.showError();
            }
        });
    }

}
