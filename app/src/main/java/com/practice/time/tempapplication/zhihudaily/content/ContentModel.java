package com.practice.time.tempapplication.zhihudaily.content;

import com.apkfuns.logutils.LogUtils;
import com.practice.time.tempapplication.api.DataApi;
import com.practice.time.tempapplication.bean.News;
import com.practice.time.tempapplication.bean.StoryExtra;

import rx.Subscriber;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.zhihudaily.content
 * @类名: ContentModel
 * @日期: 2017/8/18
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public class ContentModel implements ContentContract.Model {
    private static final String TAG = "ContentModel";

    @Override
    public void getNews(int id, final ContentContract.NewsContentCallback callback) {
        Subscriber<News> subscriber=new Subscriber<News>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(News news) {
                LogUtils.tag(TAG).d(news);
                callback.result(news);
            }
        };
        DataApi.getInstance().getNews(id,subscriber);
    }

    @Override
    public void getCommentNum(int id, final ContentContract.CommentCallback callback) {
        Subscriber<StoryExtra> subscriber=new Subscriber<StoryExtra>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StoryExtra storyExtra) {
                LogUtils.tag(TAG).d(storyExtra);
                callback.getNewsExtra(storyExtra);
            }
        };
        DataApi.getInstance().getStoryExtra(id,subscriber);
    }
}
