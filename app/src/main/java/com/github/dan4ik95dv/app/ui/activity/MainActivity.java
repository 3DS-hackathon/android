package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.devspark.robototextview.widget.RobotoTextView;
import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerMainComponent;
import com.github.dan4ik95dv.app.di.module.activity.MainModule;
import com.github.dan4ik95dv.app.model.user.User;
import com.github.dan4ik95dv.app.ui.adapter.ProfileViewPagerAdapter;
import com.github.dan4ik95dv.app.ui.fragment.AchievementsFragment;
import com.github.dan4ik95dv.app.ui.fragment.CurrentTasksFragment;
import com.github.dan4ik95dv.app.ui.fragment.TasksFragment;
import com.github.dan4ik95dv.app.ui.presenter.MainPresenter;
import com.github.dan4ik95dv.app.ui.presenter.ProfilePresenter;
import com.github.dan4ik95dv.app.ui.view.MainMvpView;
import com.github.dan4ik95dv.app.ui.view.ProfileMvpView;
import com.github.dan4ik95dv.app.util.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends BaseActivity implements MainMvpView, ProfileMvpView, AppBarLayout.OnOffsetChangedListener, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainPresenter mainPresenter;
    @Inject
    ProfilePresenter profilePresenter;

    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.navigationView)
    NavigationView mNavigationView;

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
    RobotoTextView mUserFullNameTextView;

    @BindView(R.id.userLevelNameTextView)
    RobotoTextView mUserLevelNameTextView;

    @BindView(R.id.userLevelProgressBar)
    ProgressBar mUserLevelProgressBar;

    @BindView(R.id.userLevelCountTextView)
    RobotoTextView mUserLevelCountTextView;

    @BindView(R.id.departmentNameTextView)
    RobotoTextView mDepartmentNameTextView;

    @BindView(R.id.balanceCountTextView)
    RobotoTextView mBalanceCountTextView;

    @BindView(R.id.userLevelTextView)
    RobotoTextView mUserLevelTextView;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeContainer;

    ProfileViewPagerAdapter mProfileViewPagerAdapter;


    View header;
    CircleImageView userAvatarCircleImageView;
    RobotoTextView userFullNameTextView;
    RobotoTextView userLevelTextView;
    RobotoTextView userLevelNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
        setContentView(R.layout.activity_main);

        initHeaderView();
        initActionBar();
        setupViewPager();
        mainPresenter.init();
        profilePresenter.init();
    }

    private void initHeaderView() {
        header = mNavigationView.getHeaderView(0);
        header.setOnClickListener(getHeaderClickListener());
        userAvatarCircleImageView = (CircleImageView) header.findViewById(R.id.userAvatarCircleHeaderImageView);
        userFullNameTextView = (RobotoTextView) header.findViewById(R.id.userFullNameHeaderTextView);
        userLevelTextView = (RobotoTextView) header.findViewById(R.id.userLevelHeaderTextView);
        userLevelNameTextView = (RobotoTextView) header.findViewById(R.id.userLevelNameHeaderTextView);
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);

        mAppBarLayout.addOnOffsetChangedListener(this);
        mSwipeContainer.setEnabled(false);
        mSwipeContainer.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.open_title, R.string.close_title);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    private void setupViewPager() {
        mProfileViewPagerAdapter = new ProfileViewPagerAdapter(getSupportFragmentManager());
        mProfileViewPagerAdapter.addFragment(new TasksFragment(), getString(R.string.refill_title));
        mProfileViewPagerAdapter.addFragment(new CurrentTasksFragment(), getString(R.string.my_quests_title));
        mProfileViewPagerAdapter.addFragment(new AchievementsFragment(), getString(R.string.my_achievements_title));
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mProfileViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mAppBarLayout.removeOnOffsetChangedListener(this);
    }

    @NonNull
    private View.OnClickListener getHeaderClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }

    @Override
    public void showError() {
        swipeOff();
        showErrorInternetDialog(this);
    }

    @Override
    public void userError() {
        swipeOff();
        Toast.makeText(this, R.string.incorrect_login_or_password, Toast.LENGTH_SHORT).show();
    }

    private void swipeOff() {
        swipeOn(false);
    }

    @Override
    public void showProgress() {
        swipeOn(true);
    }

    private void swipeOn(boolean refreshing) {
        if (mSwipeContainer != null) {
            mSwipeContainer.setRefreshing(refreshing);
            mSwipeContainer.setEnabled(refreshing);
        }
    }


    @Override
    public void fillUserProfile(User user) {
        swipeOff();

        Glide.with(this).load(user.getAvatar()).error(R.drawable.user).placeholder(R.drawable.user).into(mUserAvatarCircleImageView);

        if (user.getFullName() != null) {
            String fullName = TextUtils.isEmpty(user.getFullName()) ? getString(R.string.anonim) : user.getFullName();
            mUserFullNameTextView.setText(fullName);
            mCollapsingToolbarLayout.setTitle(fullName);
            getSupportActionBar().setTitle(fullName);
            mToolbar.setTitle(fullName);
        }

        if (user.getLevel() != null) {
            mUserLevelProgressBar.setProgress(user.getRating());
            mUserLevelProgressBar.setMax(user.getLevel().getEndCount());
            mUserLevelNameTextView.setText(user.getLevel().getName());
            mUserLevelTextView.setText(user.getLevel().getLevel() != null ? String.valueOf(user.getLevel().getLevel()) : "0");
            mUserLevelCountTextView.setText(getString(R.string.user_level_count_text_view, Utils.formatNumber(user.getRating()), Utils.formatNumber(user.getLevel().getEndCount())));
        }

        mDepartmentNameTextView.setText(user.getDepartment() != null ? user.getDepartment().getName() : getString(R.string.indefined));

        if (user.getBalance() != null)
            mBalanceCountTextView.setText(getString(R.string.balance, Utils.formatNumber(user.getBalance())));
    }

    @Override
    public void fillHeaderView(User user) {

        Glide.with(this).load(user.getAvatar()).error(R.drawable.user).placeholder(R.drawable.user).into(userAvatarCircleImageView);
        String fullName = TextUtils.isEmpty(user.getFullName()) ? getString(R.string.anonim) : user.getFullName();

        userFullNameTextView.setText(fullName);

        if (user.getLevel() != null) {
            userLevelNameTextView.setText(user.getLevel().getName());
            userLevelTextView.setText(String.valueOf(user.getLevel().getLevel()));
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
        profilePresenter.detachView();
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update_action:
                profilePresenter.updateUser();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_logout:
                profilePresenter.logout();
                break;
            case R.id.action_department:
                profilePresenter.showDepartment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
