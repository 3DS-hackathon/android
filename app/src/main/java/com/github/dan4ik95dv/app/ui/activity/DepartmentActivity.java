package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.devspark.robototextview.widget.RobotoTextView;
import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerDepartmentComponent;
import com.github.dan4ik95dv.app.di.module.activity.DepartmentModule;
import com.github.dan4ik95dv.app.model.Department;
import com.github.dan4ik95dv.app.ui.adapter.DepartmentViewPagerAdapter;
import com.github.dan4ik95dv.app.ui.fragment.AchievementsDepartmentsFragment;
import com.github.dan4ik95dv.app.ui.fragment.PeoplesFragment;
import com.github.dan4ik95dv.app.ui.presenter.DepartmentPresenter;
import com.github.dan4ik95dv.app.ui.view.DepartmentMvpView;
import com.github.dan4ik95dv.app.util.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class DepartmentActivity extends BaseActivity implements DepartmentMvpView {

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

    @BindView(R.id.departmentAvatarCircleImageView)
    CircleImageView mDepartmentAvatarCircleImageView;

    @BindView(R.id.departmentNameTextView)
    RobotoTextView mDepartmentNameTextView;

    @BindView(R.id.departmentDescTextView)
    RobotoTextView mDepartmentDescTextView;

    @BindView(R.id.departmentRatingTextView)
    RobotoTextView mDepartmentRatingTextView;


    @Inject
    DepartmentPresenter presenter;

    DepartmentViewPagerAdapter mDepartmentViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDepartmentComponent.builder().departmentModule(new DepartmentModule(this)).build().inject(this);

        setContentView(R.layout.activity_department);

        initActionBar();
        setupViewPager();
        presenter.init();

    }


    private void setupViewPager() {
        mDepartmentViewPagerAdapter = new DepartmentViewPagerAdapter(getSupportFragmentManager());
        mDepartmentViewPagerAdapter.addFragment(new AchievementsDepartmentsFragment(), "Награды");
        mDepartmentViewPagerAdapter.addFragment(new PeoplesFragment(), "Люди");
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mDepartmentViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setElevation(0);
        }

    }


    @Override
    public void showError() {
        showErrorInternetDialog(this);
    }

    @Override
    public void departmentError() {
        Toast.makeText(this, R.string.incorrect_login_or_password, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showProgress() {

    }


    @Override
    public void fillDepartment(Department department) {

        Glide.with(this).load(department.getAvatar()).error(R.drawable.user).placeholder(R.drawable.user).into(mDepartmentAvatarCircleImageView);

        if (department.getName() != null) {

            mCollapsingToolbarLayout.setTitle(department.getName());
            getSupportActionBar().setTitle(department.getName());
            mToolbar.setTitle(department.getName());

            mDepartmentNameTextView.setText(!TextUtils.isEmpty(department.getName()) ? department.getName() : "");
            mDepartmentDescTextView.setText(!TextUtils.isEmpty(department.getDesc()) ? department.getDesc() : "");
            mDepartmentRatingTextView.setText(Utils.formatNumber(department.getRating()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

}

