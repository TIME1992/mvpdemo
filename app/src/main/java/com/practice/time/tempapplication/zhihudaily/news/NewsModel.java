package com.practice.time.tempapplication.zhihudaily.news;

import com.apkfuns.logutils.LogUtils;
import com.practice.time.tempapplication.api.DataApi;
import com.practice.time.tempapplication.bean.LatestNews;

import rx.Subscriber;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.zhihudaily.news
 * @类名: NewsModel
 * @日期: 2017/8/17
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public class NewsModel implements NewsContract.model {
    private static final String TAG = "NewsModel";

    @Override
    public void getBeforeNews(String date, final CallbackLatestNews callback) {
        Subscriber subscriber=new Subscriber<LatestNews>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LatestNews latestNews) {
                LogUtils.tag(TAG).d(latestNews.getStories());
                callback.result(latestNews.getStories());
            }

        };
        DataApi.getInstance().getBeforeNews(date,subscriber);
    }

    @Override
    public void getLatestNews(final CallbackLatestNews callback) {
        Subscriber subscriber=new Subscriber<LatestNews>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LatestNews latestNews) {
                LogUtils.tag(TAG).d(latestNews.getStories());
                callback.result(latestNews.getStories());
            }

        };
        DataApi.getInstance().getLatestNews(subscriber);
    }
}
