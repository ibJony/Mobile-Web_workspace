package com.jon.talii;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/**
 * Main activity. It displays tabs.
 * 
 * @author itcuties
 *
 */
@SuppressWarnings("deprecation") 
// @SuppressWarnings annotation is here since we are using TabActivity which is deprecated in Android 4+
// Alternative way of constructing Tab Layout is to use ActionBar API
public class RssTabsActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// First, set the content view
		setContentView(R.layout.activity_rss_tabs);

		// Then get the TabHost
		TabHost tabHost = getTabHost();
		
		/* *****************
		 * Art tab
		 */
		Intent artIntent = new Intent().setClass(this, RssChannelActivity.class);
		// Set Art category RSS URL
		artIntent.putExtra("rss-url", "http://allafrica.com/tools/headlines/rdf/travel/headlines.rdf");
		
		// The name of the art tab taken from the String resources
		String artTabName = getResources().getString(R.string.tab_africa);
		TabSpec artTabSpec = tabHost.newTabSpec(artTabName)
									.setIndicator(artTabName, getResources().getDrawable(R.drawable.rss_tab_art))
									.setContent(artIntent);
		// Add art tab to the TabHost
		tabHost.addTab(artTabSpec);
	
		/* *****************
		 * Tech tab
		 */
		Intent techIntent = new Intent().setClass(this, RssChannelActivity.class);
		// Set Tech category RSS URL
		techIntent.putExtra("rss-url", "http://www.southafrica.info/news/travelfeed.xml");
		
		// Tech tab name taken from the string resources
		String techTabName = getResources().getString(R.string.tab_sa);
		TabSpec techTabSpec = tabHost.newTabSpec(techTabName)
									 .setIndicator(techTabName, getResources().getDrawable(R.drawable.rss_tab_tech))
									 .setContent(techIntent);
		// Add tech tab to the TabHost
		tabHost.addTab(techTabSpec);
		
		
		/* *****************
		 * Sports tab
		 */
		Intent sportsIntent = new Intent().setClass(this, RssChannelActivity.class);
		// Set Sports category RSS URL
		sportsIntent.putExtra("rss-url", "http://www.world-tourism-news.eu/rss/travel-industry.xml");
		
		// Sports tab name - string resources
		String sportsTabName = getResources().getString(R.string.tab_world);
		TabSpec sportsTabSpec = tabHost.newTabSpec(sportsTabName)
									   .setIndicator(sportsTabName, getResources().getDrawable(R.drawable.rss_tab_sports))
				  					   .setContent(sportsIntent);
		// Add sports tab to the TabHost
		tabHost.addTab(sportsTabSpec);
		
		
		// Set current tab to Technology
		tabHost.setCurrentTab(0);
	}

}
