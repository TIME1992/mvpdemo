package com.practice.time.tempapplication.zhihudaily.content;

import com.practice.time.tempapplication.bean.News;
import com.practice.time.tempapplication.bean.StoryExtra;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.zhihudaily.content
 * @类名: ContentContract
 * @日期: 2017/8/18
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public interface ContentContract {

    interface CommentCallback{
        void getNewsExtra(StoryExtra storyExtra);
    }

    interface NewsContentCallback{
        void result(News news);
    }

    interface View{
        void setTitle(String title);
        void setContent(String content);
        void setTitleImage(String url);
        void setFabVisable();
    }

    interface Model{
        void getNews(int id,NewsContentCallback callback);
        void getCommentNum(int id,CommentCallback callback);
    }

    interface Presenter{
        void getNews(int id);
        void getCommentNum(int id);
    }
}
