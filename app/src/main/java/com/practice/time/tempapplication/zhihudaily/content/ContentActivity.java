package com.practice.time.tempapplication.zhihudaily.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.practice.time.tempapplication.R;
import com.practice.time.tempapplication.utils.HtmlFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.zhihudaily.content
 * @类名: ContentActivity
 * @日期: 2017/8/18
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public class ContentActivity extends AppCompatActivity implements ContentContract.View
{

	@BindView(R.id.news_image)
	ImageView newsImage;
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.toolbar_layout)
	CollapsingToolbarLayout toolbarLayout;
	@BindView(R.id.app_bar)
	AppBarLayout appBar;
	@BindView(R.id.news_content)
	WebView newsContent;
	@BindView(R.id.fab)
	FloatingActionButton fab;

    private ContentContract.Presenter presenter;
    private int id;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		presenter=new ContentPresenter(this);
		Intent intent=getIntent();
		id=intent.getIntExtra("news_id",0);
		presenter.getNews(id);
		presenter.getCommentNum(id);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
		fab.setVisibility(View.INVISIBLE);
	}

	@Override
	public void setTitle(String title)
	{
        toolbarLayout.setTitle(title);
	}

	@Override
	public void setContent(String content)
	{
        newsContent.loadDataWithBaseURL(null, HtmlFormat.getNewContent(content),"text/html","utf-8",null);
        newsContent.getSettings().setJavaScriptEnabled(true);
        newsContent.setWebChromeClient(new WebChromeClient());
	}

	@Override
	public void setTitleImage(String url)
	{
        Glide.with(this).load(url).into(newsImage);
    }

    @Override
    public void setFabVisable() {
        fab.setVisibility(View.VISIBLE);
    }
}
