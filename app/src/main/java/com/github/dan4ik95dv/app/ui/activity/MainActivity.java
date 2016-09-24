package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerMainComponent;
import com.github.dan4ik95dv.app.di.module.activity.MainModule;
import com.github.dan4ik95dv.app.model.user.User;
import com.github.dan4ik95dv.app.ui.fragment.TasksFragment;
import com.github.dan4ik95dv.app.ui.presenter.MainPresenter;
import com.github.dan4ik95dv.app.ui.view.MainMvpView;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainPresenter presenter;

    @BindView(R.id.container)
    FrameLayout container;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    View header;
    CircleImageView userAvatarCircleImageView;
    TextView userFullNameTextView;
    TextView userLevelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
        setContentView(R.layout.activity_main);
        initHeaderView();
        presenter.init();

        showFragment(TasksFragment.class, new Bundle());
    }

    private void initHeaderView() {
        header = mNavigationView.getHeaderView(0);
        header.setOnClickListener(getHeaderClickListener());
        userAvatarCircleImageView = (CircleImageView) header.findViewById(R.id.userAvatarCircleImageView);
        userFullNameTextView = (TextView) header.findViewById(R.id.userFullNameTextView);
        userLevelTextView = (TextView) header.findViewById(R.id.userLevelTextView);
    }

    @NonNull
    private View.OnClickListener getHeaderClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextToProfileActivity();
            }
        };
    }

    @Override
    public void showError() {

    }

    @Override
    public void fillHeaderView(User user) {
        if (user != null) {
            Glide.with(this).load(user.getAvatar()).into(userAvatarCircleImageView);
            if (user.getFullName() != null)
                userFullNameTextView.setText(user.getFullName());
            if (user.getLevel() != null)
                userLevelTextView.setText(getString(R.string.level_format_header_view, user.getLevel().getName(), user.getLevel().getLevel()));
        }
    }


}
