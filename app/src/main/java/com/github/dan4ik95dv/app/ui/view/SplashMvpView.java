package com.github.dan4ik95dv.app.ui.view;

import android.os.Bundle;

public interface SplashMvpView extends MvpView {
    void showError();

    void startOffline(Bundle bundle);

    void startApp(Bundle bundle);
}
