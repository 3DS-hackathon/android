package com.github.dan4ik95dv.app.ui.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.devspark.robototextview.widget.RobotoTextView;
import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.model.user.User;
import com.github.dan4ik95dv.app.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeoplesAdapter extends RecyclerView.Adapter<PeoplesAdapter.BaseViewHolder> {

    public static final int VIEW_TYPE_LOADING = 1;
    private static final int VIEW_TYPE_ITEM = 0;

    private Context mContext;
    private List<User> userList = new ArrayList<>();

    public PeoplesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList.clear();
        for (User user : userList) {
            this.userList.add(user);
        }
        notifyDataSetChanged();
    }


    public User getItem(int position) {
        return userList.size() > position ? userList.get(position) : null;
    }

    @Override
    public int getItemViewType(int position) {
        return userList.get(position) != null ? 0 : 1;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_ITEM) {
            return new ViewHolder(inflater.inflate(R.layout.view_user_item, parent, false), mContext);
        } else if (viewType == VIEW_TYPE_LOADING) {
            return new LoadingViewHolder(inflater.inflate(R.layout.view_loading_item, parent, false), mContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        User user = userList.get(position);
        if (user != null) {
            holder.bind(user);
        }
    }


    @Override
    public void onViewRecycled(BaseViewHolder holder) {
        super.onViewRecycled(holder);
        holder.clear();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        BaseViewHolder(View view) {
            super(view);
        }

        abstract void clear();

        abstract void bind(User user);
    }

    class LoadingViewHolder extends BaseViewHolder {
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public LoadingViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        }

        @Override
        void clear() {
            if (progressBar != null) {
                progressBar.setIndeterminate(false);
            }
        }

        @Override
        void bind(User user) {
            progressBar.setIndeterminate(true);
        }
    }


    class ViewHolder extends BaseViewHolder {


        @BindView(R.id.userAvatarCircleHeaderImageView)
        ImageView userAvatarCircleHeaderImageView;
        @BindView(R.id.userFullNameHeaderTextView)
        RobotoTextView userFullNameHeaderTextView;
        @BindView(R.id.userLevelHeaderTextView)
        RobotoTextView userLevelHeaderTextView;
        @BindView(R.id.userLevelNameHeaderTextView)
        RobotoTextView userLevelNameHeaderTextView;
        @BindView(R.id.userLevelProgressBar)
        ProgressBar userLevelProgressBar;
        @BindView(R.id.userLevelCountTextView)
        RobotoTextView userLevelCountTextView;

        Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(User user) {
            if (user != null) {
                Glide.with(context).load(user.getAvatar()).placeholder(R.drawable.user).error(R.drawable.user).into(userAvatarCircleHeaderImageView);
                userFullNameHeaderTextView.setText(user.getFullName());

                if (user.getLevel() != null) {
                    userLevelProgressBar.setProgress(user.getRating());
                    userLevelProgressBar.setMax(user.getLevel().getEndCount());
                    userLevelNameHeaderTextView.setText(user.getLevel().getName());
                    userLevelHeaderTextView.setText(String.valueOf(user.getLevel().getLevel()));
                    userLevelCountTextView.setText(context.getString(R.string.user_level_count_text_view, Utils.formatNumber(user.getRating()), Utils.formatNumber(user.getLevel().getEndCount())));
                }

            }
        }

        @Override
        void clear() {

            if (userFullNameHeaderTextView != null)
                userFullNameHeaderTextView.setText("");

            if (userLevelHeaderTextView != null)
                userLevelHeaderTextView.setText("");


            if (userLevelCountTextView != null)
                userLevelCountTextView.setText("");

        }
    }
}