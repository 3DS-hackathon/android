package com.github.dan4ik95dv.app.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.devspark.robototextview.widget.RobotoTextView;
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
        this.mContext = mContext;}

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
        @BindView(R.id.cardAchievement)
        CardView cardAchievement;

        @BindView(R.id.achievementNameTextView)
        RobotoTextView achievementNameTextView;

        @BindView(R.id.achievementDescTextView)
        RobotoTextView achievementDescTextView;

        @BindView(R.id.picAchievement)
        ImageView picAchievement;

        Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(Achievement achievement) {
            if (achievement != null) {
                Glide.with(context).load(achievement.getPic()).into(picAchievement);
                if (achievement.getTextColor() != null) {
                    achievementNameTextView.setTextColor(Color.parseColor(achievement.getTextColor()));
                    achievementDescTextView.setTextColor(Color.parseColor(achievement.getTextColor()));
                }
                if (achievement.getBackgroundColor() != null) {
                    cardAchievement.setCardBackgroundColor(Color.parseColor(achievement.getBackgroundColor()));
                }
                achievementNameTextView.setText(achievement.getName() != null ? achievement.getName() : "");
                achievementDescTextView.setText(achievement.getDesc() != null ? achievement.getDesc() : "");
            }
        }

        @Override
        void clear() {
            if (cardAchievement != null) {
                cardAchievement.setCardBackgroundColor(Color.WHITE);
            }
            if (achievementNameTextView != null) {
                achievementNameTextView.setText("");
                achievementNameTextView.setTextColor(Color.BLACK);
            }
            if (achievementDescTextView != null) {
                achievementDescTextView.setText("");
                achievementDescTextView.setTextColor(Color.BLACK);
            }


        }
    }
}