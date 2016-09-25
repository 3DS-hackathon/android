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
import com.github.dan4ik95dv.app.di.component.fragment.DaggerPeoplesComponent;
import com.github.dan4ik95dv.app.di.module.fragment.PeoplesModule;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.presenter.PeoplesPresenter;
import com.github.dan4ik95dv.app.ui.view.PeoplesMvpView;
import com.github.dan4ik95dv.app.ui.widget.ItemClickSupport;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PeoplesFragment extends BaseFragment implements PeoplesMvpView {
    @Inject
    PeoplesPresenter presenter;

    BaseActivity activity;
    Bundle savedInstanceState;

    @BindView(R.id.peopleRecyclerView)
    RecyclerView mPeopleRecyclerView;
    @BindView(R.id.swipePeopleContainer)
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
        DaggerPeoplesComponent.builder().peoplesModule(new PeoplesModule(getContext())).build().inject(this);
        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView(inflater, container);
        presenter.init();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPeopleRecyclerView.setAdapter(presenter.getAdapter());
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

        mPeopleRecyclerView.setHasFixedSize(true);
        mPeopleRecyclerView.setLayoutManager(mLayoutManager);

        ItemClickSupport.addTo(mPeopleRecyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // TODO: 25.09.2016
                    }

                });

        mSwipeContainer.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary);
    }

    private void hideProgressItem() {
        if (mSwipeContainer != null) {
            mSwipeContainer.setRefreshing(false);
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
    public PeoplesFragment getFragment() {
        return this;
    }

    @Override
    public RecyclerView getPeoplesRecyclerView() {
        return mPeopleRecyclerView;
    }

    @Override
    public void hideProgress() {
        hideProgressItem();
    }

}
