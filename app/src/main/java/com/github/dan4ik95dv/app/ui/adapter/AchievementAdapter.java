package com.github.dan4ik95dv.app.ui.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.model.achievement.Achievement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.BaseViewHolder> {

    public static final int VIEW_TYPE_LOADING = 1;
    private static final int VIEW_TYPE_ITEM = 0;
    long lastErrorTime = 0;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private Context mContext;
    private List<Achievement> mAchievementList = new ArrayList<>();
    private int lastId;

    public AchievementAdapter(Context mContext, RecyclerView recyclerView) {
        this.mContext = mContext;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading
                        && lastErrorTime < System.currentTimeMillis() - 5000 || lastErrorTime == 0
                        && totalItemCount <= (lastVisibleItem + visibleThreshold)
                        && mAchievementList != null
                        && mAchievementList.size() >= visibleThreshold
                        && mOnLoadMoreListener != null) {
                    lastErrorTime = System.currentTimeMillis();
                    mOnLoadMoreListener.onLoadMore(lastVisibleItem);
                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public List<Achievement> getAchievementList() {
        return mAchievementList;
    }

    public void setAchievementList(List<Achievement> achievementList) {

        this.mAchievementList.clear();
        for (Achievement achievement : achievementList) {
            mAchievementList.add(achievement);
        }
        notifyDataSetChanged();
    }

    public Integer getLastItemId() {
        return lastId;
    }

    public Achievement getItem(int position) {
        return mAchievementList.size() > position ? mAchievementList.get(position) : null;
    }

    @Override
    public int getItemViewType(int position) {
        return mAchievementList.get(position) != null ? 0 : 1;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_ITEM) {
            return new ViewHolder(inflater.inflate(R.layout.view_achievement, parent, false), mContext);
        } else if (viewType == VIEW_TYPE_LOADING) {
            return new LoadingViewHolder(inflater.inflate(R.layout.view_loading_item, parent, false), mContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Achievement achievement = mAchievementList.get(position);
        if (achievement != null) {
            holder.bind(achievement);
        }
    }


    @Override
    public void onViewRecycled(BaseViewHolder holder) {
        super.onViewRecycled(holder);
        holder.clear();
    }

    @Override
    public int getItemCount() {
        return mAchievementList.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int lastItem);
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        BaseViewHolder(View view) {
            super(view);
        }

        abstract void clear();

        abstract void bind(Achievement achievement);
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
        void bind(Achievement achievement) {
            progressBar.setIndeterminate(true);
        }
    }


    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.achievementNameTextView)
        TextView achievementNameTextView;
        @BindView(R.id.achievementDescTextView)
        TextView achievementDescTextView;


        Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(Achievement achievement) {
            if (achievement != null) {
                achievementNameTextView.setText(achievement.getName() != null ? achievement.getName() : "");
                achievementDescTextView.setText(achievement.getDesc() != null ? achievement.getDesc() : "");
            }
        }

        @Override
        void clear() {

            if (achievementNameTextView != null)
                achievementNameTextView.setText("");
            if (achievementDescTextView != null)
                achievementDescTextView.setText("");
        }

    }

}