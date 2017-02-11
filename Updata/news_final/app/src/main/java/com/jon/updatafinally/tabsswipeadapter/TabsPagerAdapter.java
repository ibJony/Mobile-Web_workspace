package com.jon.updatafinally.tabsswipeadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jon.updatafinally.functions.RealtimeNews;
import com.jon.updatafinally.functions.BusinessNews;
import com.jon.updatafinally.functions.Enter_Lfstyle;
import com.jon.updatafinally.functions.SportsNews;
import com.jon.updatafinally.functions.Trending;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new RealtimeNews();
		case 1:
			// Games fragment activity
			return new BusinessNews();
		case 2:
			// Movies fragment activity
			return new Enter_Lfstyle();
		case 3:
			return new SportsNews();
		case 4:
			return new Trending();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 5;
	}

}
