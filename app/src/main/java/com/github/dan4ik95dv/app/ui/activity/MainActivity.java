package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.module.activity.MainModule;
import com.github.dan4ik95dv.app.ui.presenter.MainPresenter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
        presenter.init();
        setContentView(R.layout.activity_main);
    }
}
