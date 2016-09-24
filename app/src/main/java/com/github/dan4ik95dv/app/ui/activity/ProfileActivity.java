package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerProfileComponent;
import com.github.dan4ik95dv.app.di.module.activity.ProfileModule;
import com.github.dan4ik95dv.app.model.user.User;
import com.github.dan4ik95dv.app.ui.adapter.ProfileViewPagerAdapter;
import com.github.dan4ik95dv.app.ui.fragment.AchievementsFragment;
import com.github.dan4ik95dv.app.ui.fragment.TasksFragment;
import com.github.dan4ik95dv.app.ui.presenter.ProfilePresenter;
import com.github.dan4ik95dv.app.ui.view.ProfileMvpView;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseActivity implements ProfileMvpView {

    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.userAvatarCircleImageView)
    CircleImageView mUserAvatarCircleImageView;

    @BindView(R.id.userFullNameTextView)
    TextView mUserFullNameTextView;

    @BindView(R.id.userLevelTextView)
    TextView mUserLevelTextView;

    @BindView(R.id.userLevelProgressBar)
    ProgressBar mUserLevelProgressBar;

    @BindView(R.id.userLevelCountTextView)
    TextView mUserLevelCountTextView;

    @BindView(R.id.departmentNameTextView)
    TextView mDepartmentNameTextView;

    @BindView(R.id.balanceCountTextView)
    TextView mBalanceCountTextView;

    @Inject
    ProfilePresenter presenter;

    ProfileViewPagerAdapter mProfileViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerProfileComponent.builder().profileModule(new ProfileModule(this)).build().inject(this);

        setContentView(R.layout.activity_profile);
        setSupportActionBar(mToolbar);
        presenter.init();
        initActionBar();
        setupViewPager();
    }

    private void initActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setElevation(0);
        }
    }

    private void setupViewPager() {
        mProfileViewPagerAdapter = new ProfileViewPagerAdapter(getSupportFragmentManager());
        mProfileViewPagerAdapter.addFragment(new TasksFragment(), getString(R.string.refill_title));
        mProfileViewPagerAdapter.addFragment(new TasksFragment(), getString(R.string.my_quests_title));
        mProfileViewPagerAdapter.addFragment(new AchievementsFragment(), getString(R.string.my_achievements_title));
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mProfileViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void showError() {
        showErrorInternetDialog(this);
    }

    @Override
    public void fillUserProfile(User user) {


        if (user.getAvatar() != null)
            Glide.with(this).load(user.getAvatar()).into(mUserAvatarCircleImageView);

        if (user.getFullName() != null) {
            mUserFullNameTextView.setText(user.getFullName());
            mCollapsingToolbarLayout.setTitle(user.getFullName());
            mToolbar.setTitle(user.getFullName());
        }

        if (user.getLevel() != null) {
            mUserLevelProgressBar.setProgress(user.getLevel().getLevel());
            mUserLevelProgressBar.setMax(user.getLevel().getEndCount());
            mUserLevelTextView.setText(getString(R.string.level_format_header_view, user.getLevel().getName(), user.getLevel().getLevel()));
            mUserLevelCountTextView.setText(getString(R.string.user_level_count_text_view, user.getLevel().getLevel(), user.getLevel().getEndCount()));
        }

        if (user.getDepartment() != null)
            mDepartmentNameTextView.setText(user.getDepartment().getName());

        if (user.getBalance() != null)
            mBalanceCountTextView.setText(getString(R.string.balance, user.getBalance()));

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}

