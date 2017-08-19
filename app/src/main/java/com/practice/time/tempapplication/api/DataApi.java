package com.practice.time.tempapplication.api;

import com.practice.time.tempapplication.Config;
import com.practice.time.tempapplication.bean.Comment;
import com.practice.time.tempapplication.bean.LatestNews;
import com.practice.time.tempapplication.bean.News;
import com.practice.time.tempapplication.bean.StoryExtra;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.api
 * @类名: DataApi
 * @日期: 2017/8/17
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public class DataApi {

    private static final int DEFAULT_TIMEOUT = 5;

    private DataService service;
    private static DataApi dataApiInstance;

    private DataApi(){
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .client(client)
                .baseUrl(Config.ZHIHU_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service=retrofit.create(DataService.class);
    }

    public static DataApi getInstance(){
        if(dataApiInstance==null){
            synchronized (DataApi.class){
                if(dataApiInstance==null){
                    dataApiInstance=new DataApi();
                }
            }
        }
        return dataApiInstance;
    }

    public void getLatestNews(Subscriber<LatestNews> subscriber){
        service.getLatestNews()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getBeforeNews(String date,Subscriber<LatestNews> subscriber){
        service.getBeforeNews(date)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getNews(int id,Subscriber<News> subscriber){
        service.getNews(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void getStoryExtra(int id,Subscriber<StoryExtra> subscriber){
        service.getStroyExtra(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getComment(int id,Subscriber<Comment> subscriber) {
        service.getComments(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
