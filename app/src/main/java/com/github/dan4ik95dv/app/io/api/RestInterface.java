package com.github.dan4ik95dv.app.io.api;

import com.github.dan4ik95dv.app.model.Department;
import com.github.dan4ik95dv.app.model.achievement.Achievement;
import com.github.dan4ik95dv.app.model.achievement.AchievementsResponse;
import com.github.dan4ik95dv.app.model.attachment.Attachment;
import com.github.dan4ik95dv.app.model.balancelog.BalanceLog;
import com.github.dan4ik95dv.app.model.balancelog.BalanceLogsResponse;
import com.github.dan4ik95dv.app.model.task.Request;
import com.github.dan4ik95dv.app.model.task.Task;
import com.github.dan4ik95dv.app.model.task.TaskRequest;
import com.github.dan4ik95dv.app.model.task.TasksResponse;
import com.github.dan4ik95dv.app.model.user.LoginRequest;
import com.github.dan4ik95dv.app.model.user.Token;
import com.github.dan4ik95dv.app.model.user.User;

import java.util.List;

import io.realm.RealmList;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RestInterface {

    /*
     * Login
     * ===============
     * Авторизация
     *
     * */

    @POST("login")
    Call<Token> login(@Body LoginRequest loginRequest);

    /*
     * Upload files
     * ===============
     * Загрузка файлов
     *
     * */

    @Multipart
    @POST("attach")
    Call<Attachment> upload(@Header("Authorization") String token, @Part MultipartBody.Part file);

    /*
     * Request task
     * ===============
     * Оставить реквест на выполненный таск.
     *
     * */

    @POST("request")
    Call<Request> requestTask(@Header("Authorization") String token, @Body TaskRequest taskRequest);


   /*
    * Profile
    * ===============
    * Профиль
    *
    * */

    @GET("user")
    Call<User> getUser(@Header("Authorization") String token, @Query("id") Integer id);

    @GET("user")
    Call<User> getUser();


   /*
    * Achievements
    * ===============
    * Достижения
    *
    *
    *
    * */

    @GET("user/achievements")
    Call<AchievementsResponse> getAchievements(@Header("Authorization") String token, @Query("id") Integer id, @Query("offset") Integer offset, @Query("count") Integer count);

    @GET("user/achievements")
    Call<AchievementsResponse> getAchievements(@Header("Authorization") String token, @Query("id") Integer id);

    @GET("user/achievements")
    Call<AchievementsResponse> getAchievements(@Header("Authorization") String token, @Query("offset") Integer offset, @Query("count") Integer count);

    /*
    * Balance Log
    * ===============
    * История баланса
    *
    * */

    @GET("user/log")
    Call<BalanceLogsResponse> getBalanceLog(@Header("Authorization") String token, @Query("id") Integer id);

    @GET("user/log")
    Call<BalanceLogsResponse> getBalanceLog(@Header("Authorization") String token, @Query("id") Integer id, @Query("offset") Integer offset, @Query("count") Integer count);

    @GET("user/log")
    Call<BalanceLogsResponse> getBalanceLog(@Header("Authorization") String token, @Query("offset") Integer offset, @Query("count") Integer count);

    /*
    * Department
    * ===============
    * Отдел компании
    *
    * */

    @GET("department")
    Call<Department> getDepartment(@Header("Authorization") String token);

    @GET("department")
    Call<Department> getDepartment(@Header("Authorization") String token, @Query("id") Integer id);

    /*
    * Таски
    * ===============
    * Список таски
    *
    * */

    @GET("tasks")
    Call<TasksResponse> getTasks(@Header("Authorization") String token);

    @GET("tasks")
    Call<TasksResponse> getTasks(@Header("Authorization") String token, @Query("offset") Integer offset, @Query("count") Integer count);

    @GET("user/tasks")
    Call<TasksResponse> getUserTasks(@Header("Authorization") String token, @Query("offset") Integer offset, @Query("count") Integer count);


}
