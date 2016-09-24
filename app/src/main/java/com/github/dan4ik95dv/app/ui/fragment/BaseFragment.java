package com.github.dan4ik95dv.app.ui.fragment;

import android.support.v4.app.Fragment;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class BaseFragment extends Fragment {
    private CompositeSubscription subscription = new CompositeSubscription();

    public void watch(Subscription sub) {
        subscription.add(sub);
    }


}
