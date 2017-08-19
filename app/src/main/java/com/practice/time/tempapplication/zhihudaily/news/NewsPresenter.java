package com.practice.time.tempapplication.zhihudaily.news;

import com.practice.time.tempapplication.bean.LatestNews;

import java.util.List;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.zhihudaily.news
 * @类名: NewsPresenter
 * @日期: 2017/8/17
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View view;
    private NewsContract.model model;

    public NewsPresenter(NewsContract.View view) {
        this.view = view;
        model=new NewsModel();
    }

    @Override
    public void getBeforeNews(String date) {
        model.getBeforeNews(date, new CallbackLatestNews() {
            @Override
            public void result(List<LatestNews.StoriesBean> storiesBeanList) {
                view.refreshView(storiesBeanList);
            }
        });

    }

    @Override
    public void getLatestNews() {
        model.getLatestNews(new CallbackLatestNews() {
            @Override
            public void result(List<LatestNews.StoriesBean> storiesBeanList) {
                view.refreshView(storiesBeanList);
            }
        });

    }
}
