package com.github.dan4ik95dv.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.fragment.DaggerAchievementsComponent;
import com.github.dan4ik95dv.app.di.module.fragment.AchievementsModule;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.presenter.AchievementsPresenter;
import com.github.dan4ik95dv.app.ui.view.AchievementsMvpView;
import com.github.dan4ik95dv.app.ui.widget.DividerItemDecoration;
import com.github.dan4ik95dv.app.ui.widget.ItemClickSupport;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AchievementsFragment extends BaseFragment implements AchievementsMvpView {
    @Inject
    AchievementsPresenter presenter;

    BaseActivity activity;
    Bundle savedInstanceState;

    @BindView(R.id.achievementsRecyclerView)
    RecyclerView mAchievementsRecyclerView;


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
        DaggerAchievementsComponent.builder().achievementsModule(new AchievementsModule(getContext())).build().inject(this);
        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievements, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView(inflater, container);
        presenter.init();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mAchievementsRecyclerView.setAdapter(presenter.getAdapter());
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

        mAchievementsRecyclerView.setHasFixedSize(true);
        mAchievementsRecyclerView.setLayoutManager(mLayoutManager);
        mAchievementsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));

        ItemClickSupport.addTo(mAchievementsRecyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                    }
                });
    }

    private void hideProgressItem() {
        if (presenter.getAdapter().getAchievementList().size() > 0 &&
                presenter.getAdapter().getAchievementList().get(presenter.getAdapter().getAchievementList().size() - 1) == null) {
            presenter.getAdapter().getAchievementList().remove(presenter.getAdapter().getAchievementList().size() - 1);
            presenter.getAdapter().notifyItemRemoved(presenter.getAdapter().getAchievementList().size() - 1);
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
    public AchievementsFragment getFragment() {
        return this;
    }

    @Override
    public RecyclerView getAchievementsRecyclerView() {
        return mAchievementsRecyclerView;
    }


    @Override
    public void hideProgress() {

    }

}
