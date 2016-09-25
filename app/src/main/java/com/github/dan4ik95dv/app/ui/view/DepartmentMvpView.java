package com.github.dan4ik95dv.app.ui.view;

import com.github.dan4ik95dv.app.model.Department;

public interface DepartmentMvpView extends MvpView {
    void showError();

    void fillDepartment(Department department);

    void departmentError();

    void showProgress();
}
