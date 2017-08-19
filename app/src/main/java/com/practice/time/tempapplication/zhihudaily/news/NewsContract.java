package com.practice.time.tempapplication.zhihudaily.news;

import com.practice.time.tempapplication.bean.LatestNews;

import java.util.List;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.zhihudaily.news
 * @类名: NewsContract
 * @日期: 2017/8/17
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public interface NewsContract {

    interface View{
        void refreshView(List<LatestNews.StoriesBean> storiesBeanList);
    }

    interface Presenter{
        void getBeforeNews(String date);
        void getLatestNews();
    }

    interface model{
        void getBeforeNews(String date,CallbackLatestNews callback);
        void getLatestNews(CallbackLatestNews callback);
    }
}
