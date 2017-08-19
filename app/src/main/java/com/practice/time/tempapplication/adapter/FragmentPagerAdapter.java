package com.practice.time.tempapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

/**
 * @描述:
 * @包名: com.practice.time.tempapplication.adapter
 * @类名: FragmentPagerAdapter
 * @日期: 2017/8/17
 * @版权: Copyright ® 烽火星空. All right reserved.
 * @作者: Admin
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter{

        ArrayList<Fragment> fragments;

        public FragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
           super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

}
