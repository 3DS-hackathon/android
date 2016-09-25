package com.github.dan4ik95dv.app.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.util.AndroidUtils;
import com.github.dan4ik95dv.app.util.Constants;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    Unbinder unbinder;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }


    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public void nextToMainActivity() {
        AndroidUtils.startActivitySafe(this, new Intent(this, MainActivity.class), R.string.error_unknown);
    }


    public void nextToLoginActivity() {
        AndroidUtils.startActivitySafe(this, new Intent(this, LoginActivity.class), R.string.error_unknown);
    }


    public void showMoreTaskActivity(Integer taskId) {
        Intent intent = new Intent(this, MoreTaskActivity.class);
        intent.putExtra(Constants.TASK_ID, taskId);
        AndroidUtils.startActivitySafe(this, intent, R.string.error_unknown);
    }

    public void showCompanyActivity(Integer taskId) {
        Intent intent = new Intent(this, DepartmentActivity.class);
        intent.putExtra(Constants.DEPARTMENT_ID, taskId);
        AndroidUtils.startActivitySafe(this, intent, R.string.error_unknown);
    }
    public void showAddRequestTaskActivity(Integer taskId) {
        Intent intent = new Intent(this, AddRequestActivity.class);
        intent.putExtra(Constants.TASK_ID, taskId);
        AndroidUtils.startActivitySafe(this, intent, R.string.error_unknown);
    }

    public void nextToMainActivity(Bundle bundle) {
        Intent intent = new Intent(this, MainActivity.class);
        if (bundle != null) {
            intent.putExtra(Constants.IntentExtras.BUNDLE, bundle);
        }
        AndroidUtils.startActivitySafe(this, intent, R.string.error_unknown);
    }


//    public void showFragment(Class fragmentClass, Bundle args) {
//        Fragment fragment = null;
//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//            fragment.setArguments(args);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
//    }


    public void showErrorInternetDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTheme);
        builder.setTitle(R.string.title_internet_not_worked);
        builder.setMessage(R.string.msg_internet_not_worked);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create();
        builder.show();
    }


    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }


    public void rateApp() {
        Uri uri = Uri.parse("market://details?id=" + Constants.PACKAGE);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + Constants.PACKAGE)));
        }
    }


    public void setCursorDrawableColor(EditText editText) {
        try {
            final Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            fCursorDrawableRes.set(editText, R.drawable.edit_text_cursor);
        } catch (final Throwable ignored) {
        }
    }

}
