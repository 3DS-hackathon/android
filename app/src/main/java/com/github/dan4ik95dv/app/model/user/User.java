package com.github.dan4ik95dv.app.model.user;

import com.github.dan4ik95dv.app.model.Department;
import com.github.dan4ik95dv.app.model.achievement.Achievement;
import com.github.dan4ik95dv.app.model.task.Task;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class User extends RealmObject {
    @Index
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("balance")
    private Integer balance;

    @SerializedName("email")
    private String email;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("phone")
    private String phone;

    @SerializedName("role")
    private String role;

    @SerializedName("department")
    private Department department;

    @SerializedName("rating")
    private Integer rating;

    @SerializedName("level")
    private Level level;

    @SerializedName("achievements")
    private RealmList<Achievement> achievements;

    @SerializedName("tasks")
    private RealmList<Task> tasks;

    public RealmList<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(RealmList<Achievement> achievements) {
        this.achievements = achievements;
    }

    public RealmList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(RealmList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
