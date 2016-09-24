package com.github.dan4ik95dv.app.ui.adapter;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.model.app.Image;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private static final String TAG = "PhotosAdapter";

    private ArrayList<Image> imageList = new ArrayList<>();
    private ProgressItem progressItem;
    private boolean editMode = false;
    private Context mContext;

    public PhotosAdapter(Context context, boolean editMode) {
        this.editMode = editMode;
        this.imageList = new ArrayList<>();
        this.mContext = context;
    }

    public ArrayList<Image> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<Image> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    public ArrayList<Image> getImages() {
        ArrayList<Image> imagesUrl = new ArrayList<>();
        for (Image image : imageList) {
            imagesUrl.add(image);
        }
        return imagesUrl;
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public PhotosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image image = imageList.get(position);

        if (!TextUtils.isEmpty(image.getOriginal())) {
            Log.d("Glide", image.getOriginal());
            if (progressItem != null && progressItem.getPosition() == position) {
                if (progressItem.getProgress() > 0 && progressItem.getProgress() < 100) {
                    holder.appProgressImage.setVisibility(View.VISIBLE);
                    holder.appProgressImage.setMax(100);
                    holder.appProgressImage.setProgress(progressItem.getProgress());
                } else if (progressItem.getProgress() == 100) {
                    holder.appProgressImage.setVisibility(View.GONE);
                    progressItem.setProgress(0);
                }
            }
            Glide.with(mContext).load(image.getOriginal()).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.appThumb);
        } else {
            Glide.clear(holder.appThumb);
            holder.appThumb.setImageDrawable(null);
        }
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        Glide.clear(holder.appThumb);
        super.onViewRecycled(holder);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void add(Image image) {
        imageList.add(image);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        imageList.remove(position);
        notifyDataSetChanged();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView appThumb;
        ProgressBar appProgressImage;

        public ViewHolder(View view) {
            super(view);

            appThumb = (ImageView) view.findViewById(R.id.appThumbItem);
            appProgressImage = (ProgressBar) view.findViewById(R.id.appProgressImage);
        }
    }

    public static class ProgressItem {
        int progress;
        int position;

        public ProgressItem(int progress, int position) {
            this.progress = progress;
            this.position = position;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

}
