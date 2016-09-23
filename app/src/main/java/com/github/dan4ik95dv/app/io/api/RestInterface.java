package com.github.dan4ik95dv.app.io.api;

import com.github.dan4ik95dv.app.model.achievement.AchievementResponse;
import com.github.dan4ik95dv.app.model.attachment.AttachmentResponse;
import com.github.dan4ik95dv.app.model.task.RequestResponse;
import com.github.dan4ik95dv.app.model.task.TaskRequest;
import com.github.dan4ik95dv.app.model.user.LoginRequest;
import com.github.dan4ik95dv.app.model.user.LoginResponse;
import com.github.dan4ik95dv.app.model.user.ProfileResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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

    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);


    @Multipart
    @POST("/attach")
    Call<AttachmentResponse> upload(@Part MultipartBody.Part file);



    @POST("/request")
    Call<RequestResponse> requestTask(@Body TaskRequest taskRequest);

   /*
    * Profile
    * ===============
    * Профиль
    *
    * */

    @GET("/profile")
    Call<ProfileResponse> getProfile(@Query("id") Integer id);

    @GET("/profile")
    Call<ProfileResponse> getProfile();


   /*
    * Achievements
    * ===============
    * Достижения
    *
    * */

    @GET("/profile/achievements")
    Call<AchievementResponse> getAchievements(@Query("id") Integer id, @Query("offset") Integer offset, @Query("count") Integer count);

    @GET("/profile/achievements")
    Call<AchievementResponse> getAchievements(@Query("offset") Integer offset, @Query("count") Integer count);

    /*
    * Balance Log
    * ===============
    * История баланса
    *
    * */

    @GET("/profile/log")
    Call<> getBalanceLog();

    @GET("/profile/log")
    Call<> getBalanceLog(@Query("id") Integer id);

    @GET("/profile/log")
    Call<> getBalanceLog(@Query("id") Integer id, @Query("offset") Integer offset, @Query("count") Integer count);

    @GET("/profile/log")
    Call<> getBalanceLog(@Query("offset") Integer offset, @Query("count") Integer count);

    /*
    * Department
    * ===============
    * Отдел компании
    *
    * */

    @GET("/department")
    Call<> getDepartment();

    @GET("/department")
    Call<> getDepartment(@Query("id") Integer id);


}
