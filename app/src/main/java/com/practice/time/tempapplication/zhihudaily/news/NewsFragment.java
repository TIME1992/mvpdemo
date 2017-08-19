package com.practice.time.tempapplication.zhihudaily.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.practice.time.tempapplication.R;
import com.practice.time.tempapplication.adapter.QuickAdapter;
import com.practice.time.tempapplication.bean.LatestNews;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.zhihudaily.news
 * @类名: NewsFragment
 * @日期: 2017/8/17
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public class NewsFragment extends Fragment implements NewsContract.View, DatePickerDialog.OnDateSetListener
{

	@BindView(R.id.latest_news_recyclerview)
	RecyclerView mLatestNewsRecyclerview;
	@BindView(R.id.fab)
	FloatingActionButton mFab;
	private NewsContract.Presenter presenter;
	List<LatestNews.StoriesBean> storiesBeanList;
	QuickAdapter adapter;

	int year=2017,month=7,day=18;

    public NewsFragment(){
        presenter=new NewsPresenter(this);
		storiesBeanList=new ArrayList<>();
    }

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_zhihu_daily, container, false);
		ButterKnife.bind(this, view);
		mFab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Calendar now=Calendar.getInstance();
				DatePickerDialog dialog=DatePickerDialog.newInstance(NewsFragment.this,now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
				dialog.show(getActivity().getFragmentManager(),"DatePicker");
			}
		});
		LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mLatestNewsRecyclerview.setLayoutManager(layoutManager);
		adapter=new QuickAdapter(getContext(),storiesBeanList);
		adapter.openLoadAnimation();
		adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
			@Override
			public void onLoadMoreRequested() {
				day--;
				if(day<0){
					month--;
					day=30;
				}
				if(month<=0){
					year--;
					month=11;
				}
				String date=String.format("%d%02d%02d",year,month+1,day);
				presenter.getBeforeNews(date);
			}
		},mLatestNewsRecyclerview);
		mLatestNewsRecyclerview.setAdapter(adapter);
        presenter.getLatestNews();
		return view;
	}

	@Override
	public void refreshView(List<LatestNews.StoriesBean> stories)
	{
		adapter.addData(stories);
		adapter.loadMoreComplete();
	}

	@Override
	public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
		this.year=year;
		this.month=monthOfYear;
		this.day=dayOfMonth;
		String date=String.format("%d%02d%02d",year,monthOfYear+1,dayOfMonth);
		presenter.getBeforeNews(date);
	}
}
