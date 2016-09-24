package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.model.task.Task;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.adapter.TaskAdapter;
import com.github.dan4ik95dv.app.ui.view.CurrentTasksMvpView;

import java.util.ArrayList;
import java.util.List;


public class CurrentTasksPresenter implements Presenter<CurrentTasksMvpView> {
    private TaskAdapter mTaskAdapter;
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

    public CurrentTasksPresenter(Context context) {
        this.context = context;
        this.activity = (BaseActivity) context;
        App.getInstance().getClientComponent().inject(this);
    }

    @Override
    public void attachView(CurrentTasksMvpView view) {
        this.currentTasksMvpView = view;
    }

    @Override
    public void detachView() {
        this.currentTasksMvpView = null;
    }

    public TaskAdapter getAdapter() {
        return mTaskAdapter;
    }


    public void init() {
        mTaskAdapter = new TaskAdapter(context, currentTasksMvpView.getTasksRecyclerView());
        mTaskAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        mTaskAdapter.setOnLoadMoreListener(new TaskAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int lastItem) {
                if (lastItem > 0 && hasNext) {
                    mTaskAdapter.getTaskList().add(null);
                    if (!currentTasksMvpView.getTasksRecyclerView().isComputingLayout()) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTaskAdapter.notifyItemInserted(mTaskAdapter.getTaskList().size() - 1);
                            }
                        });
                    }
                }
            }
        });
        fillTasks();
    }

    public SwipeRefreshLayout.OnRefreshListener getSwipeRefreshLayoutListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        };
    }


    public void fillTasks() {
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Task fixtureTask = new Task();
            fixtureTask.setDesc(String.valueOf(Math.random()));
            fixtureTask.setName(String.valueOf(Math.random()));
            fixtureTask.setExperience(Integer.valueOf(String.valueOf(Math.round(Math.random()))));
            fixtureTask.setPrice(Integer.valueOf(String.valueOf(Math.round(Math.random()))));
            fixtureTask.setType("fixed");

            taskList.add(fixtureTask);
        }
        mTaskAdapter.setTaskList(taskList);
    }

}
