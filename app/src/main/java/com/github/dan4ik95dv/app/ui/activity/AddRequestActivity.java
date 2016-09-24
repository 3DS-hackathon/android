package com.github.dan4ik95dv.app.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerAddRequestComponent;
import com.github.dan4ik95dv.app.di.module.activity.AddRequestModule;
import com.github.dan4ik95dv.app.model.task.Task;
import com.github.dan4ik95dv.app.ui.adapter.PhotosAdapter;
import com.github.dan4ik95dv.app.ui.presenter.AddRequestPresenter;
import com.github.dan4ik95dv.app.ui.view.AddRequestMvpView;
import com.github.dan4ik95dv.app.util.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import gun0912.tedbottompicker.TedBottomPicker;

public class AddRequestActivity extends BaseActivity implements AddRequestMvpView {

    @Inject
    AddRequestPresenter presenter;

    @BindView(R.id.taskPicImageView)
    ImageView mTaskPicImageView;
    @BindView(R.id.priceTaskTextView)
    TextView mPriceTaskTextView;
    @BindView(R.id.xpTaskTextView)
    TextView mXpTaskTextView;
    @BindView(R.id.nameTaskTextView)
    TextView nameTaskTextView;
    @BindView(R.id.taskLevelProgressBar)
    ProgressBar mTaskLevelProgressBar;
    @BindView(R.id.typeTaskTextView)
    TextView typeTaskTextView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.addSellTaskButton)
    AppCompatButton mAddSellTaskButton;
    @BindView(R.id.attachAdd)
    FloatingActionButton mAttachAdd;

    @OnClick(R.id.attachAdd)
    public void attachAddOnClick() {
        callTedBottomPicker();
    }

    @OnClick(R.id.addSellTaskButton)
    public void addSellOnClick() {
        presenter.sentRequest();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAddRequestComponent.builder().addRequestModule(new AddRequestModule(this)).build().inject(this);
        setContentView(R.layout.activity_add_request);
        presenter.init();
        initActionBar();
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setElevation(0);
        }
    }

    void callTedBottomPicker() {
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(AddRequestActivity.this)
                .setMaxCount(5)
                .setTitle(R.string.select_pic)
                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(Uri uri) {
                        presenter.uploadFile(uri);
                    }
                })
                .create();

        tedBottomPicker.show(getSupportFragmentManager());
    }

    @Override
    public void showError() {
        showErrorInternetDialog(this);
    }

    @Override
    public void fillTask(Task task) {
        Glide.with(this).load(task.getPic()).centerCrop().into(mTaskPicImageView);

        mPriceTaskTextView.setText(getString(R.string.balance, Utils.formatNumber(task.getPrice())));
        mXpTaskTextView.setText(getString(R.string.xpa, Utils.formatNumber(task.getExperience())));
        nameTaskTextView.setText(TextUtils.isEmpty(task.getName()) ? "" : task.getName());
        typeTaskTextView.setText(TextUtils.isEmpty(task.getType()) ? "" : task.getType());
        mToolbar.setTitle(TextUtils.isEmpty(task.getName()) ? "" : task.getName());
        mTaskLevelProgressBar.setProgress(task.getProgress());
        mTaskLevelProgressBar.setSecondaryProgress(task.getProgressUser());
        mTaskLevelProgressBar.setMax(task.getTotalCount());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}

