package com.github.dan4ik95dv.app.ui.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.model.task.Task;
import com.github.dan4ik95dv.app.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentTaskAdapter extends RecyclerView.Adapter<CurrentTaskAdapter.BaseViewHolder> {
    public static final int VIEW_TYPE_LOADING = 2;
    private static final String TAG = "CurrentTaskAdapter";
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_ITEM_NO_IMAGE = 1;

    long lastErrorTime = 0;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private Context mContext;
    private List<Task> mTaskList = new ArrayList<>();
    private int lastId;

    public CurrentTaskAdapter(Context mContext, RecyclerView recyclerView) {
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
                        && mTaskList != null
                        && mTaskList.size() >= visibleThreshold
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

    public List<Task> getTaskList() {
        return mTaskList;
    }

    public void setTaskList(List<Task> taskList) {

        this.mTaskList.clear();
        for (Task task : taskList) {
            mTaskList.add(task);
        }
        notifyDataSetChanged();
    }

    public Integer getLastItemId() {
        return lastId;
    }

    public Task getItem(int position) {
        return mTaskList.size() > position ? mTaskList.get(position) : null;
    }

    @Override
    public int getItemViewType(int position) {
        return mTaskList.get(position) != null ? mTaskList.get(position).getPic() != null ? VIEW_TYPE_ITEM : VIEW_TYPE_ITEM_NO_IMAGE : VIEW_TYPE_LOADING;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                return new ViewHolder(inflater.inflate(R.layout.view_current_task_with_image, parent, false), mContext);
            case VIEW_TYPE_ITEM_NO_IMAGE:
                return new ViewHolderNoImage(inflater.inflate(R.layout.view_current_task, parent, false), mContext);
            case VIEW_TYPE_LOADING:
                return new LoadingViewHolder(inflater.inflate(R.layout.view_loading_item, parent, false), mContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Task task = mTaskList.get(position);
        if (task != null) {
            holder.bind(task);
        }
    }


    @Override
    public void onViewRecycled(BaseViewHolder holder) {
        super.onViewRecycled(holder);
        holder.clear();
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
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

        abstract void bind(Task task);
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
        void bind(Task operation) {
            progressBar.setIndeterminate(true);
        }
    }

    class ViewHolder extends ViewHolderNoImage {
        @Nullable
        @BindView(R.id.logoTaskImageView)
        ImageView logoTaskImageView;
        Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView, context);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(Task task) {
            super.bind(task);
            if (task.getPic() != null) {
                Glide.with(context).load(task.getPic()).fitCenter().into(logoTaskImageView);
            }
        }

        @Override
        public void clear() {
            super.clear();
            if (logoTaskImageView != null) {
                Glide.clear(logoTaskImageView);
            }
        }
    }

    class ViewHolderNoImage extends BaseViewHolder {
        @BindView(R.id.taskNameTextView)
        TextView taskNameTextView;
        @BindView(R.id.typeTaskTextView)
        TextView typeTaskTextView;
        @BindView(R.id.priceTaskTextView)
        TextView priceTaskTextView;
        @BindView(R.id.xpTaskTextView)
        TextView xpTaskTextView;

        @BindView(R.id.userLevelProgressBar)
        ProgressBar userLevelProgressBar;
        @BindView(R.id.userLevelCountTextView)
        TextView userLevelCountTextView;
        @BindView(R.id.sendButton)
        AppCompatButton sendButton;

        @BindView(R.id.completeLayoutCard)
        RelativeLayout completeLayoutCard;
        @BindView(R.id.moreCardInfo)
        LinearLayout moreCardInfo;

        Context context;

        public ViewHolderNoImage(View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }


        @Override
        public void bind(Task task) {
            if (task != null) {
                xpTaskTextView.setText(task.getExperience() != null ? context.getString(R.string.xpa, Utils.formatNumber(task.getExperience())) : "");

                typeTaskTextView.setText(task.getType() != null ? task.getType() : "");
                priceTaskTextView.setText(task.getPrice() != null ? context.getString(R.string.balance, Utils.formatNumber(task.getPrice())) : "");
                taskNameTextView.setText(task.getName() != null ? task.getName() : "");

                if (task.getStatus() != null) {
                    switch (task.getStatus()) {
                        case "pending":
                        case "progress":
                            completeLayoutCard.setVisibility(View.GONE);
                            moreCardInfo.setVisibility(View.VISIBLE);
                            if (task.getTotalCount() != null) {
                                userLevelCountTextView.setText(
                                        context.getString(R.string.user_level_count_text_view,
                                                Utils.formatNumber(task.getProgressUser()),
                                                Utils.formatNumber(task.getTotalCount())));

                                userLevelProgressBar.setProgress(0);

//                                Drawable progressDrawable = ContextCompat.getDrawable(context,R.drawable.progress);
//                                progressDrawable.setBounds(userLevelProgressBar.getProgressDrawable().getBounds());

//                                userLevelProgressBar.setProgressDrawable(progressDrawable);
//                                userLevelProgressBar.requestLayout();
                                if (task.getProgressUser() != null)
                                    userLevelProgressBar.setProgress(task.getProgressUser());


                                if (task.getProgress() != null) {
                                    userLevelProgressBar.setSecondaryProgress(task.getProgress());
                                }
                                userLevelProgressBar.setMax(task.getTotalCount());
                            }
                            break;
                        case "complete":
                            moreCardInfo.setVisibility(View.GONE);
                            completeLayoutCard.setVisibility(View.VISIBLE);
                            break;
                    }
                }

            }
        }

        @Override
        void clear() {
            if (xpTaskTextView != null)
                xpTaskTextView.setText("");

            if (typeTaskTextView != null)
                typeTaskTextView.setText("");

            if (userLevelCountTextView != null)
                userLevelCountTextView.setText("");

            if (priceTaskTextView != null)
                priceTaskTextView.setText("");

            if (taskNameTextView != null)
                taskNameTextView.setText("");

            if (userLevelProgressBar != null) {
                userLevelProgressBar.setProgress(0);
                userLevelProgressBar.setSecondaryProgress(0);
            }
            if (moreCardInfo != null) {
                moreCardInfo.setVisibility(View.GONE);
            }
            if (completeLayoutCard != null) {
                completeLayoutCard.setVisibility(View.GONE);
            }

        }

    }

}