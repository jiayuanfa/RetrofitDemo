package com.example.jiayuanfa.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 用户接口
 */
public interface UserMgrService {

    @GET("login")
    Call<UserInfoModel> login(@Query("username") String username, @Query("password") String password);
}
