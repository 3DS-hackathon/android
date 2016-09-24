package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.model.task.Task;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.adapter.CurrentTaskAdapter;
import com.github.dan4ik95dv.app.ui.view.CurrentTasksMvpView;
import com.github.dan4ik95dv.app.util.Utils;

import java.util.ArrayList;
import java.util.List;


public class CurrentTasksPresenter implements Presenter<CurrentTasksMvpView> {
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

    public CurrentTaskAdapter getAdapter() {
        return mTaskAdapter;
    }


    public void init() {
        mTaskAdapter = new CurrentTaskAdapter(context, currentTasksMvpView.getTasksRecyclerView());
        mTaskAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        mTaskAdapter.setOnLoadMoreListener(new CurrentTaskAdapter.OnLoadMoreListener() {
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
            String[] statuses = new String[]{"pending", "progress", "complete"};
            Task fixtureTask = new Task();
            fixtureTask.setDesc(String.valueOf(Math.random()));
            fixtureTask.setName(String.valueOf(Math.random()));
            fixtureTask.setPic("https://unsplash.it/512/256/?random&r=" + String.valueOf(Math.random()));
            fixtureTask.setExperience(Utils.randInt(0, 100000));
            fixtureTask.setPrice(Utils.randInt(0, 100000));
            fixtureTask.setStatus(statuses[Utils.randInt(0, 2)]);
            fixtureTask.setProgressUser(Utils.randInt(0, 100));
            fixtureTask.setProgress(Utils.randInt(0, 100));
            fixtureTask.setTotalCount(100);
            fixtureTask.setType("fixed");

            taskList.add(fixtureTask);
        }
        mTaskAdapter.setTaskList(taskList);
    }

}
