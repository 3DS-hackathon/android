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
import com.github.dan4ik95dv.app.model.task.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.BaseViewHolder> {

    public static final int VIEW_TYPE_LOADING = 1;
    private static final int VIEW_TYPE_ITEM = 0;
    long lastErrorTime = 0;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private Context mContext;
    private List<Task> mTaskList = new ArrayList<>();
    private int lastId;

    public TaskAdapter(Context mContext, RecyclerView recyclerView) {
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
        return mTaskList.get(position) != null ? 0 : 1;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_ITEM) {
            return new ViewHolder(inflater.inflate(R.layout.view_task, parent, false), mContext);
        } else if (viewType == VIEW_TYPE_LOADING) {
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


    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.taskNameTextView)
        TextView taskNameTextView;
        @BindView(R.id.typeTaskTextView)
        TextView typeTaskTextView;
        @BindView(R.id.priceTaskTextView)
        TextView priceTaskTextView;
        @BindView(R.id.xpTaskTextView)
        TextView xpTaskTextView;

        Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(Task task) {
            if (task != null) {
                xpTaskTextView.setText(task.getExperience() != null ? context.getString(R.string.xpa, task.getExperience()) : "");
                typeTaskTextView.setText(task.getType() != null ? task.getType() : "");
                priceTaskTextView.setText(task.getPrice() != null ? context.getString(R.string.balance, task.getPrice()) : "");
                taskNameTextView.setText(task.getName() != null ? task.getName() : "");
            }
        }

        @Override
        void clear() {
            if (xpTaskTextView != null)
                xpTaskTextView.setText("");

            if (typeTaskTextView != null)
                typeTaskTextView.setText("");

            if (priceTaskTextView != null)
                priceTaskTextView.setText("");

            if (taskNameTextView != null)
                taskNameTextView.setText("");

        }

    }

}