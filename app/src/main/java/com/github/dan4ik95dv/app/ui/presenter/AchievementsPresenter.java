package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.model.achievement.Achievement;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.adapter.AchievementAdapter;
import com.github.dan4ik95dv.app.ui.view.AchievementsMvpView;

import java.util.ArrayList;
import java.util.List;


public class AchievementsPresenter implements Presenter<AchievementsMvpView> {
    private AchievementAdapter mAchievementAdapter;
    private AchievementsMvpView achievementsMvpView;
    RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            achievementsMvpView.hideProgress();
        }
    };
    private Context context;
    private BaseActivity activity;
    private Boolean hasNext = true;

    public AchievementsPresenter(Context context) {
        this.context = context;
        this.activity = (BaseActivity) context;
        App.getInstance().getClientComponent().inject(this);
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
        mAchievementAdapter.setOnLoadMoreListener(new AchievementAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int lastItem) {
                if (lastItem > 0 && hasNext) {
                    mAchievementAdapter.getAchievementList().add(null);
                    if (!achievementsMvpView.getAchievementsRecyclerView().isComputingLayout()) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAchievementAdapter.notifyItemInserted(mAchievementAdapter.getAchievementList().size() - 1);
                            }
                        });
                    }
                }
            }
        });
        fillAchievements();
    }


    public void fillAchievements() {
        List<Achievement> achievementList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Achievement fixtureAchievement = new Achievement();
            fixtureAchievement.setDesc(String.valueOf(Math.random()));
            fixtureAchievement.setName(String.valueOf(Math.random()));

            achievementList.add(fixtureAchievement);
        }
        mAchievementAdapter.setAchievementList(achievementList);
    }

}
