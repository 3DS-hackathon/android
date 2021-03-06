package com.github.dan4ik95dv.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.fragment.DaggerCurrentTasksComponent;
import com.github.dan4ik95dv.app.di.module.fragment.CurrentTasksModule;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.presenter.CurrentTasksPresenter;
import com.github.dan4ik95dv.app.ui.view.CurrentTasksMvpView;
import com.github.dan4ik95dv.app.ui.widget.ItemClickSupport;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CurrentTasksFragment extends BaseFragment implements CurrentTasksMvpView {
    @Inject
    CurrentTasksPresenter presenter;

    BaseActivity activity;
    Bundle savedInstanceState;

    @BindView(R.id.tasksRecyclerView)
    RecyclerView mTasksRecyclerView;
    @BindView(R.id.swipeTasksContainer)
    SwipeRefreshLayout mSwipeContainer;


    Toast errorToast;
    long lastShowErrorToast = 0;
    Unbinder unbinder;
    LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        this.activity = (BaseActivity) getActivity();
        this.savedInstanceState = savedInstanceState;
        DaggerCurrentTasksComponent.builder().currentTasksModule(new CurrentTasksModule(getContext())).build().inject(this);
        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView(inflater, container);
        presenter.init();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTasksRecyclerView.setAdapter(presenter.getAdapter());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    private void initRecyclerView(LayoutInflater inflater, @Nullable ViewGroup container) {
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mTasksRecyclerView.setHasFixedSize(true);
        mTasksRecyclerView.setLayoutManager(mLayoutManager);

        ItemClickSupport.addTo(mTasksRecyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        presenter.addRequest(position);
                    }

                });
        mSwipeContainer.setOnRefreshListener(presenter.getSwipeRefreshLayoutListener());
        mSwipeContainer.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary);
    }

    private void hideProgressItem() {
        hideProgress();

        if (presenter.getAdapter().getTaskList().size() > 0 &&
                presenter.getAdapter().getTaskList().get(presenter.getAdapter().getTaskList().size() - 1) == null) {
            presenter.getAdapter().getTaskList().remove(presenter.getAdapter().getTaskList().size() - 1);
            presenter.getAdapter().notifyItemRemoved(presenter.getAdapter().getTaskList().size() - 1);
        }
    }

    @Override
    public void showError() {

        hideProgressItem();

        if (errorToast == null)
            errorToast = Toast.makeText(getActivity(), R.string.offline_mode, Toast.LENGTH_LONG);

        if (lastShowErrorToast < System.currentTimeMillis() - Constants.SHOW_TOAST_ERROR_INTERVAL) {
            errorToast.show();
            lastShowErrorToast = System.currentTimeMillis();
        }
    }


    @Override
    public CurrentTasksFragment getFragment() {
        return this;
    }

    @Override
    public RecyclerView getTasksRecyclerView() {
        return mTasksRecyclerView;
    }

    @Override
    public void hideProgress() {
        if (mSwipeContainer != null) {
            mSwipeContainer.setRefreshing(false);
        }
    }

}
