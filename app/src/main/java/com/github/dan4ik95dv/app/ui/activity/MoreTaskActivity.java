package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.devspark.robototextview.widget.RobotoTextView;
import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerMoreTaskComponent;
import com.github.dan4ik95dv.app.di.module.activity.MoreTaskModule;
import com.github.dan4ik95dv.app.model.task.Task;
import com.github.dan4ik95dv.app.ui.presenter.MoreTaskPresenter;
import com.github.dan4ik95dv.app.ui.view.MoreTaskMvpView;
import com.github.dan4ik95dv.app.util.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MoreTaskActivity extends BaseActivity implements MoreTaskMvpView {

    @Inject
    MoreTaskPresenter presenter;

    @BindView(R.id.taskPicImageView)
    ImageView mTaskPicImageView;

    @BindView(R.id.typeTaskTextView)
    RobotoTextView mTypeTaskTextView;

    @BindView(R.id.nameTaskTextView)
    RobotoTextView mNameTaskTextView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.descTaskTextView)
    RobotoTextView mDescTaskTextView;

    @BindView(R.id.priceTaskTextView)
    RobotoTextView mPriceTaskTextView;

    @BindView(R.id.dateTaskTextView)
    RobotoTextView mDateTaskTextView;

    @BindView(R.id.joinTaskButton)
    AppCompatButton mJoinTaskButton;

    @BindView(R.id.taskAdd)
    FloatingActionButton mTaskAdd;

    @OnClick({R.id.taskAdd, R.id.joinTaskButton})
    public void onClickJoin() {
        presenter.joinTask();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMoreTaskComponent.builder().moreTaskModule(new MoreTaskModule(this)).build().inject(this);
        setContentView(R.layout.activity_more_task);
        presenter.init();
    }


    @Override
    public void showError() {
        showErrorInternetDialog(this);
    }

    @Override
    public void fillTask(Task task) {
        Glide.with(this).load(task.getPic()).centerCrop().into(mTaskPicImageView);
        mTypeTaskTextView.setText(TextUtils.isEmpty(task.getType()) ? "" : task.getType());
        mNameTaskTextView.setText(TextUtils.isEmpty(task.getName()) ? "" : task.getName());
        mToolbar.setTitle(TextUtils.isEmpty(task.getName()) ? "" : task.getName());
        mDescTaskTextView.setText(TextUtils.isEmpty(task.getDesc()) ? "" : task.getDesc());
        mPriceTaskTextView.setText(task.getPrice() != null && task.getExperience() != null ? getString(R.string.balance_xpa, Utils.formatNumber(task.getPrice()), Utils.formatNumber(task.getExperience())) : "");
        mDateTaskTextView.setText(" Date !!!11");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}

