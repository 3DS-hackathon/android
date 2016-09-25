package com.github.dan4ik95dv.app.ui.presenter;

import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.Toast;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.model.attachment.Attachment;
import com.github.dan4ik95dv.app.model.task.Request;
import com.github.dan4ik95dv.app.model.task.Task;
import com.github.dan4ik95dv.app.model.task.TaskRequest;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.adapter.PhotosAdapter;
import com.github.dan4ik95dv.app.ui.view.AddRequestMvpView;
import com.github.dan4ik95dv.app.util.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRequestPresenter implements Presenter<AddRequestMvpView> {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RestInterface restInterface;

    @Inject
    Realm realm;

    private BaseActivity activity;
    private String token;
    private Task task;
    private Integer taskId;
    private Context context;
    private List<Integer> attachmentIds = new ArrayList<>();
    private AddRequestMvpView addRequestMvpView;
    private PhotosAdapter photosAdapter;

    public AddRequestPresenter(Context context) {
        attachView((AddRequestMvpView) context);
        App.getInstance().getClientComponent().inject(this);
        this.activity = (BaseActivity) context;
        this.context = context;
        this.photosAdapter = new PhotosAdapter(context, true);
        this.token = sharedPreferences.getString(Constants.Configs.TOKEN, null);
    }


    @Override
    public void attachView(AddRequestMvpView view) {
        this.addRequestMvpView = view;
    }

    @Override
    public void detachView() {
        this.addRequestMvpView = null;
    }

    public void uploadFile(Uri fileUri) {

        String filePath = getRealPathFromUri(fileUri);
        if (filePath != null && !filePath.isEmpty()) {
            File file = new File(filePath);
            if (file.exists()) {
                // creates RequestBody instance from file
                final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                // MultipartBody.Part is used to send also the actual filename
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

                Call<Attachment> call = restInterface.upload(token, body);
                call.enqueue(new Callback<Attachment>() {
                    @Override
                    public void onResponse(Call<Attachment> call, Response<Attachment> response) {
                        if (response.isSuccessful()) {
                            photosAdapter.add(response.body());
                            attachmentIds.add(response.body().getId());
                            addRequestMvpView.requestSuccess();
                        } else {
                            addRequestMvpView.showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Attachment> call, Throwable t) {
                        addRequestMvpView.showError();
                    }
                });
            }
        }
    }

    public String getRealPathFromUri(final Uri uri) {
        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private String getDataColumn(Context context, Uri uri, String selection,
                                 String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public void init() {
        if (activity.getIntent() != null)
            taskId = activity.getIntent().getIntExtra(Constants.TASK_ID, -1);
        if (taskId != -1) {
            task = realm.where(Task.class).equalTo("id", taskId).findFirst();
            List<Request> requestsList = realm.where(Request.class).equalTo("task.id", taskId).findAll();
            if (requestsList != null && requestsList.size() > 0) {
                for (Request request : requestsList)
                    photosAdapter.add(request.getAttachments());
            }

            addRequestMvpView.fillTask(task);
        }
    }


    public void sentRequest() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setAttachments(attachmentIds);
        taskRequest.setTaskId(taskId);

        restInterface.requestTask(token, taskRequest).enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                if (response.isSuccessful()) {
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(response.body());
                    realm.commitTransaction();
                    Toast.makeText(activity, R.string.request_has_been_added, Toast.LENGTH_SHORT).show();
                    addRequestMvpView.requestSuccess();
                    activity.finish();
                } else {
                    addRequestMvpView.showError();
                }
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                addRequestMvpView.showError();
            }
        });
    }

    public PhotosAdapter getPhotosAdapter() {
        return photosAdapter;
    }
}
