package com.example.jiayuanfa.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 登录
     */
    public void login(View view){

        // 创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 获取接口对象
        UserMgrService userMgrService = retrofit.create(UserMgrService.class);

        // 调用我们登录的方法
        final Call<UserInfoModel> call = userMgrService.login("jiayuanfa","123456");

        // 真正发送请求(同步)
//        try {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Response<UserInfoModel> response = null;
//                    try {
//                        response = call.execute();
//                        Log.d("result", ":" + response.body());
//                    }catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        // 发送异步请求 他们再内部开启线程池请求数据 主动在请求完毕的时候 通过主线程回调给我们数据
        call.enqueue(new Callback<UserInfoModel>() {
            @Override
            public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {
                Log.d("result", " " + response.body());
            }

            @Override
            public void onFailure(Call<UserInfoModel> call, Throwable t) {

            }
        });
    }
}
