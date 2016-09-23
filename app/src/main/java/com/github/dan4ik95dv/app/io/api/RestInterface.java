package com.github.dan4ik95dv.app.io.api;

import com.github.dan4ik95dv.app.model.Department;
import com.github.dan4ik95dv.app.model.achievement.Achievement;
import com.github.dan4ik95dv.app.model.attachment.Attachment;
import com.github.dan4ik95dv.app.model.balancelog.BalanceLog;
import com.github.dan4ik95dv.app.model.task.Request;
import com.github.dan4ik95dv.app.model.task.Task;
import com.github.dan4ik95dv.app.model.task.TaskRequest;
import com.github.dan4ik95dv.app.model.user.LoginRequest;
import com.github.dan4ik95dv.app.model.user.Token;
import com.github.dan4ik95dv.app.model.user.User;

import java.util.List;

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
    Call<Token> login(@Body LoginRequest loginRequest);

    /*
     * Upload files
     * ===============
     * Загрузка файлов
     *
     * */

    @Multipart
    @POST("/attach")
    Call<Attachment> upload(@Part MultipartBody.Part file);

    /*
     * Request task
     * ===============
     * Оставить реквест на выполненный таск.
     *
     * */

    @POST("/request")
    Call<Request> requestTask(@Body TaskRequest taskRequest);


   /*
    * Profile
    * ===============
    * Профиль
    *
    * */

    @GET("/user")
    Call<User> getUser(@Query("id") Integer id);

    @GET("/user")
    Call<User> getUser();


   /*
    * Achievements
    * ===============
    * Достижения
    *
    * Deprecated !!!
    *
    * */

    @GET("/profile/achievements")
    Call<List<Achievement>> getAchievements(@Query("id") Integer id, @Query("offset") Integer offset, @Query("count") Integer count);

    @GET("/profile/achievements")
    Call<List<Achievement>> getAchievements(@Query("offset") Integer offset, @Query("count") Integer count);

    /*
    * Balance Log
    * ===============
    * История баланса
    *
    * */

    @GET("/user/log")
    Call<List<BalanceLog>> getBalanceLog();

    @GET("/user/log")
    Call<List<BalanceLog>> getBalanceLog(@Query("id") Integer id);

    @GET("/user/log")
    Call<List<BalanceLog>> getBalanceLog(@Query("id") Integer id, @Query("offset") Integer offset, @Query("count") Integer count);

    @GET("/user/log")
    Call<List<BalanceLog>> getBalanceLog(@Query("offset") Integer offset, @Query("count") Integer count);

    /*
    * Department
    * ===============
    * Отдел компании
    *
    * */

    @GET("/department")
    Call<Department> getDepartment();

    @GET("/department")
    Call<Department> getDepartment(@Query("id") Integer id);

    /*
    * Таски
    * ===============
    * Список таски
    *
    * */

    @GET("/tasks")
    Call<List<Task>> getTasks();

    @GET("/tasks")
    Call<List<Task>> getTasks(@Query("offset") Integer offset, @Query("count") Integer count);


}
