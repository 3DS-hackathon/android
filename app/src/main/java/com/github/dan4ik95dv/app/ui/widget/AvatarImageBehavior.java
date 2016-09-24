package com.github.dan4ik95dv.app.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.github.dan4ik95dv.app.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AvatarImageBehavior extends CoordinatorLayout.Behavior {

    private final static float MIN_AVATAR_PERCENTAGE_SIZE = 0.3f;
    private final static int EXTRA_FINAL_AVATAR_PADDING = 80;

    private final static String TAG = "behavior";
    private final Context mContext;
    private float mAvatarMaxSize;

    private float mFinalLeftAvatarPadding;
    private float mStartPosition;
    private int mStartXPosition;
    private float mStartToolbarPosition;
    private int mStartYPosition;
    private int mFinalYPosition;
    private int mFinalHeight;
    private int mStartHeight;
    private int mFinalXPosition;
    public AvatarImageBehavior(Context context, AttributeSet attrs) {
        mContext = context;
        init();

        mFinalLeftAvatarPadding = context.getResources().getDimension(R.dimen.activity_horizontal_margin);
    }

    private void init() {
        bindDimensions();
    }

    private void bindDimensions() {
        mAvatarMaxSize = mContext.getResources().getDimension(R.dimen.image_width);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        maybeInitProperties((CircleImageView) child, dependency);

        final int maxScrollDistance = (int) (mStartToolbarPosition - getStatusBarHeight());

        Log.d(TAG, "onDependentViewChanged: getStatusBarHeight: " + getStatusBarHeight());

        Log.d(TAG, "onDependentViewChanged: mStartToolbarPosition: " + mStartToolbarPosition);

        Log.d(TAG, "onDependentViewChanged: mStartXPosition: " + mStartXPosition);
        Log.d(TAG, "onDependentViewChanged: mFinalYPosition: " + mFinalYPosition);

        Log.d(TAG, "onDependentViewChanged: mStartYPosition: " + mStartYPosition);
        Log.d(TAG, "onDependentViewChanged: mFinalXPosition: " + mFinalXPosition);

        Log.d(TAG, "onDependentViewChanged: mStartHeight: " + mStartHeight);
        Log.d(TAG, "onDependentViewChanged: mFinalHeight: " + mFinalHeight);

        Log.d(TAG, "onDependentViewChanged: mStartYPosition: " + child.getX());
        Log.d(TAG, "onDependentViewChanged: mFinalXPosition: " + child.getY());

        Log.d(TAG, "onDependentViewChanged: dependency.getX() :" + dependency.getX());
        Log.d(TAG, "onDependentViewChanged: dependency.getY() :" + dependency.getY());

        Log.d(TAG, "onDependentViewChanged: child.getX() :" + child.getX());
        Log.d(TAG, "onDependentViewChanged: child.getY() :" + child.getY());

        Log.d(TAG, "onDependentViewChanged: dependency.getWidth() :" + dependency.getWidth());
        Log.d(TAG, "onDependentViewChanged: dependency.getHeight() :" + dependency.getHeight());

        Log.d(TAG, "onDependentViewChanged: child.getWidth() :" + child.getWidth());
        Log.d(TAG, "onDependentViewChanged: child.getHeight() :" + child.getHeight());

        Log.d(TAG, "onDependentViewChanged: maxScrollDistance: " + maxScrollDistance);

        // float expandedPercentageFactor = dependency.getY() / maxScrollDistance;


//        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//        lp.width = (int) (mStartHeight - heightToSubtract);
//        lp.height = (int) (mStartHeight - heightToSubtract);
//        child.setY(mStartYPosition - distanceYToSubtract);
//        child.setX(mStartXPosition - distanceXToSubtract);
//        child.setLayoutParams(lp);
        return true;
    }

    @SuppressLint("PrivateResource")
    private void maybeInitProperties(CircleImageView child, View dependency) {

        if (mStartYPosition == 0)
            mStartYPosition = (int) (dependency.getY() * 2);

        if (mFinalYPosition == 0)
            mFinalYPosition = (dependency.getHeight() / 2);

        if (mStartHeight == 0)
            mStartHeight = child.getHeight();

        if (mFinalHeight == 0)
            mFinalHeight = mContext.getResources().getDimensionPixelOffset(R.dimen.image_small_width);

        if (mStartXPosition == 0)
            mStartXPosition = (int) (child.getX() + (child.getWidth() / 2));

        if (mFinalXPosition == 0)
            mFinalXPosition = mContext.getResources().getDimensionPixelOffset(R.dimen.abc_action_bar_content_inset_material) + (mFinalHeight / 2);

        if (mStartToolbarPosition == 0)
            mStartToolbarPosition = dependency.getY() + (dependency.getHeight() / 2);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}