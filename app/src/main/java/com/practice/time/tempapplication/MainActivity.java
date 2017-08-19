package com.practice.time.tempapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.practice.time.tempapplication.adapter.FragmentPagerAdapter;
import com.practice.time.tempapplication.zhihudaily.news.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity implements MaterialTabListener
{

	private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1;
	private static final String TAG = "MainActivity";

	MaterialTabHost tabHost;
	ViewPager pager;
	FragmentPagerAdapter pagerAdapter;

	ArrayList<Fragment> fragmentList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		checkPermission();
		tabHost = (MaterialTabHost) this.findViewById(R.id.materialTabHost);
		pager = (ViewPager) this.findViewById(R.id.viewpager);

		fragmentList = new ArrayList<>();
		fragmentList.add(new NewsFragment());
		fragmentList.add(new NewsFragment());
		fragmentList.add(new NewsFragment());
		// init view pager
		pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
		pager.setAdapter(pagerAdapter);
		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// when user do a swipe the selected tab change
				tabHost.setSelectedNavigationItem(position);
			}
		});

		// insert all tabs from pagerAdapter data
		List<String> tabsNames = new ArrayList<>();
		tabsNames.add("zhihu");
		tabsNames.add("schedule");
		tabsNames.add("timeTable");

		// insert all tabs from pagerAdapter data
		for (int i = 0; i < pagerAdapter.getCount(); i++) {
			tabHost.addTab(
					tabHost.newTab()
							.setText(tabsNames.get(i))
							.setTabListener(this)
			);
		}

	}

	private void checkPermission()
	{
		// Here, thisActivity is the current activity
		if (ContextCompat.checkSelfPermission(MainActivity.this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
		{

			// Should we show an explanation?
			if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
					Manifest.permission.WRITE_EXTERNAL_STORAGE))
			{

				// Show an expanation to the user *asynchronously* -- don't
				// block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.

			}
			else
			{

				// No explanation needed, we can request the permission.

				ActivityCompat.requestPermissions(MainActivity.this, new String[]
				{
					Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.INTERNET
				}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

				// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
				// app-defined int constant. The callback method gets the
				// result of the request.
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
	{
		switch (requestCode)
		{
			case MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE:
			{
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
				{

					// permission was granted, yay! Do the
					// contacts-related task you need to do.

				}
				else
				{

					// permission denied, boo! Disable the
					// functionality that depends on this permission.
				}
				return;
			}

			// other 'case' lines to check for other
			// permissions this app might request
		}
	}

	@Override
	public void onTabSelected(MaterialTab tab) {

	}

	@Override
	public void onTabReselected(MaterialTab tab) {

	}

	@Override
	public void onTabUnselected(MaterialTab tab) {

	}
}
