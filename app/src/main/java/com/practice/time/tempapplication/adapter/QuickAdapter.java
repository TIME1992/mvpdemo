package com.practice.time.tempapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.practice.time.tempapplication.R;
import com.practice.time.tempapplication.bean.LatestNews;
import com.practice.time.tempapplication.zhihudaily.content.ContentActivity;

import java.util.List;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.adapter
 * @类名: QuickAdapter
 * @日期: 2017/8/17
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public class QuickAdapter extends BaseQuickAdapter<LatestNews.StoriesBean,BaseViewHolder> {

    private Context mContext;
    public QuickAdapter(Context context,@Nullable List<LatestNews.StoriesBean> data) {
        super(R.layout.latest_news_item,data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder holder, final LatestNews.StoriesBean item) {
        holder.setText(R.id.latest_news_title,item.getTitle());
        holder.getView(R.id.latest_news_cardview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=item.getId();
                Intent intent=new Intent(mContext, ContentActivity.class);
                intent.putExtra("news_id",id);
                mContext.startActivity(intent);
            }
        });
        Glide.with(mContext).load(item.getImages().get(0)).into((ImageView) holder.getView(R.id.latest_news_image));
    }
}
