package com.practice.time.tempapplication.zhihudaily.content;

import com.practice.time.tempapplication.bean.News;
import com.practice.time.tempapplication.bean.StoryExtra;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.zhihudaily.content
 * @类名: ContentPresenter
 * @日期: 2017/8/18
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public class ContentPresenter implements ContentContract.Presenter {

    private ContentContract.Model model;
    private ContentContract.View view;

    public ContentPresenter(ContentContract.View view) {
        this.view = view;
        model=new ContentModel();
    }

    @Override
    public void getNews(int id) {
        model.getNews(id, new ContentContract.NewsContentCallback() {
            @Override
            public void result(News news) {
                view.setTitle(news.getTitle());
                view.setTitleImage(news.getImage());
                view.setContent(news.getBody());
            }
        });
    }

    @Override
    public void getCommentNum(int id) {
        model.getCommentNum(id, new ContentContract.CommentCallback() {
            @Override
            public void getNewsExtra(StoryExtra storyExtra) {
                if(storyExtra.getLong_comments()!=0){
                    view.setFabVisable();
                }
            }
        });
    }
}
