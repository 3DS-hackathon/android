package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.model.task.Task;
import com.github.dan4ik95dv.app.model.task.TasksResponse;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.adapter.TaskAdapter;
import com.github.dan4ik95dv.app.ui.view.TasksMvpView;
import com.github.dan4ik95dv.app.util.Constants;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.github.dan4ik95dv.app.util.Constants.COUNT;


public class TasksPresenter implements Presenter<TasksMvpView> {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RestInterface restInterface;

    @Inject
    Realm realm;

    private TaskAdapter mTaskAdapter;
    private TasksMvpView tasksMvpView;
    private String token;

    RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            tasksMvpView.hideProgress();
        }
    };
    private Context context;
    private BaseActivity activity;
    private Boolean hasNext = true;

    public TasksPresenter(Context context) {
        this.context = context;
        this.activity = (BaseActivity) context;
        App.getInstance().getClientComponent().inject(this);
        this.token = sharedPreferences.getString(Constants.Configs.TOKEN, null);
    }

    @Override
    public void attachView(TasksMvpView view) {
        this.tasksMvpView = view;
    }

    @Override
    public void detachView() {
        this.tasksMvpView = null;
    }

    public TaskAdapter getAdapter() {
        return mTaskAdapter;
    }


    public void init() {
        mTaskAdapter = new TaskAdapter(context, tasksMvpView.getTasksRecyclerView());
        mTaskAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        mTaskAdapter.setOnLoadMoreListener(new TaskAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int lastItem) {
                if (lastItem > 0 && hasNext) {
                    mTaskAdapter.getTaskList().add(null);
                    if (!tasksMvpView.getTasksRecyclerView().isComputingLayout()) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTaskAdapter.notifyItemInserted(mTaskAdapter.getTaskList().size() - 1);
                            }
                        });
                        updateTasks(lastItem);
                    }
                }
            }
        });
        getTasks();
    }

    public SwipeRefreshLayout.OnRefreshListener getSwipeRefreshLayoutListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateTasks(0);
            }
        };
    }


    private void getTasks() {
        final List<Task> taskList = realm.where(Task.class).findAll();
        if (taskList.size() > 0) {
            mTaskAdapter.setTaskList(taskList);
        } else {
            if (token != null) {
                restInterface.getTasks(token).enqueue(new Callback<TasksResponse>() {
                    @Override
                    public void onResponse(Call<TasksResponse> call, Response<TasksResponse> response) {
                        if (response.isSuccessful()) {
                            tasksMvpView.hideProgress();
                            realm.insertOrUpdate(response.body().getTasks());
                            mTaskAdapter.setTaskList(response.body().getTasks());
                        } else {
                            tasksMvpView.showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<TasksResponse> call, Throwable t) {
                        tasksMvpView.showError();
                    }
                });
            }
        }
    }


    private void updateTasks(int offset) {
        if (token != null) {
            restInterface.getTasks(token, offset, COUNT).enqueue(new Callback<TasksResponse>() {
                @Override
                public void onResponse(Call<TasksResponse> call, Response<TasksResponse> response) {
                    if (response.isSuccessful()) {
                        tasksMvpView.hideProgress();
                        realm.insertOrUpdate(response.body().getTasks());
                        List<Task> tasks = realm.where(Task.class).findAll();
                        mTaskAdapter.setTaskList(tasks);
                    } else {
                        tasksMvpView.showError();
                    }
                }

                @Override
                public void onFailure(Call<TasksResponse> call, Throwable t) {
                    tasksMvpView.showError();
                }
            });
        }
    }

}
