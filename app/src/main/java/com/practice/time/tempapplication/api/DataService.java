package com.practice.time.tempapplication.api;

import com.practice.time.tempapplication.bean.Comment;
import com.practice.time.tempapplication.bean.LatestNews;
import com.practice.time.tempapplication.bean.News;
import com.practice.time.tempapplication.bean.StoryExtra;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.api
 * @类名: DataService
 * @日期: 2017/8/17
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public interface DataService {
    @GET("api/4/news/latest")
    Observable<LatestNews> getLatestNews();

    @GET("api/4/news/before/{date}")
    Observable<LatestNews> getBeforeNews(@Path("date") String date);

    @GET("api/4/news/{id}")
    Observable<News> getNews(@Path("id") int id);

    @GET("api/4/story/{id}/long-comments")
    Observable<Comment> getComments(@Path("id") int id);

    @GET("api/4/story-extra/{id}")
    Observable<StoryExtra> getStroyExtra(@Path("id") int id);
}
