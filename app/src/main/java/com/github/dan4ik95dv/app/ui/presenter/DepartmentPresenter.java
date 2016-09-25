package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.io.api.ConnectionDetector;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.model.Department;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.view.DepartmentMvpView;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DepartmentPresenter implements Presenter<DepartmentMvpView> {


    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RestInterface restInterface;

    @Inject
    ConnectionDetector connectionDetector;
    @Inject
    Realm realm;

    private Integer department;
    private DepartmentMvpView departmentMvpView;
    private BaseActivity activity;
    private String token;

    @Override
    public void attachView(DepartmentMvpView view) {
        this.departmentMvpView = view;
    }

    @Override
    public void detachView() {
        this.departmentMvpView = null;
    }


    public DepartmentPresenter(Context context) {
        App.getInstance().getClientComponent().inject(this);
        activity = (BaseActivity) context;
        token = sharedPreferences.getString(Constants.Configs.TOKEN, null);
        attachView((DepartmentMvpView) context);

    }


    public void init() {
        if (activity.getIntent() != null)
            department = activity.getIntent().getIntExtra(Constants.DEPARTMENT_ID, -1);
        if (department != -1) {
            getDepartment(department);
        }
    }


    public void getDepartment(Integer id) {
        departmentMvpView.showProgress();
        if (connectionDetector.isConnectingToInternet()) {
            restInterface.getDepartment(token, id).enqueue(new Callback<Department>() {
                @Override
                public void onResponse(Call<Department> call, Response<Department> response) {
                    if (response.isSuccessful()) {
                        departmentMvpView.fillDepartment(response.body());
                    } else {
                        departmentMvpView.showError();
                    }
                }

                @Override
                public void onFailure(Call<Department> call, Throwable t) {
                    departmentMvpView.showError();
                }
            });
        }
    }

}
