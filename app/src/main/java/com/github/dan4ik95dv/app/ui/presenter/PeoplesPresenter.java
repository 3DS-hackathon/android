package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.model.Department;
import com.github.dan4ik95dv.app.model.user.User;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.adapter.PeoplesAdapter;
import com.github.dan4ik95dv.app.ui.view.PeoplesMvpView;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PeoplesPresenter implements Presenter<PeoplesMvpView> {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RestInterface restInterface;

    @Inject
    Realm realm;

    private PeoplesAdapter mPeoplesAdapter;
    private PeoplesMvpView peoplesMvpView;
    RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            peoplesMvpView.hideProgress();
        }
    };
    private String token;
    private Context context;
    private BaseActivity activity;

    public PeoplesPresenter(Context context) {
        this.context = context;
        this.activity = (BaseActivity) context;
        App.getInstance().getClientComponent().inject(this);
        this.token = sharedPreferences.getString(Constants.Configs.TOKEN, null);
    }

    @Override
    public void attachView(PeoplesMvpView view) {
        this.peoplesMvpView = view;
    }

    @Override
    public void detachView() {
        this.peoplesMvpView = null;
    }

    public PeoplesAdapter getAdapter() {
        return mPeoplesAdapter;
    }


    public void init() {
        mPeoplesAdapter = new PeoplesAdapter(context);
        mPeoplesAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        getPeoples();
    }

    public void showMorePeople(int position) {
        User user = mPeoplesAdapter.getItem(position);
        if (user != null) {
            // TODO: 25.09.2016
//            activity.showMoreTaskActivity(mPeoplesAdapter.getItem(position).getId());
        }
    }


    private void getPeoples() {
        if (token != null) {
            restInterface.getDepartment(token).enqueue(new Callback<Department>() {
                @Override
                public void onResponse(Call<Department> call, Response<Department> response) {
                    if (response.isSuccessful()) {
                        peoplesMvpView.hideProgress();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(response.body().getUsers());
                        realm.commitTransaction();
                        mPeoplesAdapter.setUserList(response.body().getUsers());

                    } else {
                        peoplesMvpView.showError();
                    }
                }

                @Override
                public void onFailure(Call<Department> call, Throwable t) {
                    peoplesMvpView.showError();
                }
            });
        }
    }


}
