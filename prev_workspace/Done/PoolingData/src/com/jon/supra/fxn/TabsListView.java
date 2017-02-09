package com.jon.supra.fxn;

import java.util.ArrayList;

import com.example.supra.R;
import com.jon.supra.fxn.UpdataActivity;
import java.io.FileNotFoundException;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class TabsListView extends TabActivity{
	private static final String NEW_SPEC = "News trending";
    private static final String UPD_SPEC = "Uradi";
    private ActionBar actionBar;
   
    
    // Refresh menu item
    private MenuItem refreshMenuItem;
    
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        
       actionBar = getActionBar();
       actionBar.setSubtitle("Be updated");
        
        TabHost tabHost = getTabHost();
         
        // News Tab
        TabSpec Newspec = tabHost.newTabSpec(NEW_SPEC);
        // Tab Icon
        Newspec.setIndicator("News & Updates");
        Intent newsIntent = new Intent(this, UpdataActivity.class);
        // Tab Content
        Newspec.setContent(newsIntent);
         
        // Lostitems Tab
        TabSpec lostspec = tabHost.newTabSpec(UPD_SPEC);
        lostspec.setIndicator("Opportunities");
        Intent lostIntent = new Intent(this, FinderActivity.class);
        lostspec.setContent(lostIntent);
         
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(Newspec);
        tabHost.addTab(lostspec);
         //Adding news
         // Adding lostitems 
        
    }
		
		/* *
		* Called when invalidateOptionsMenu() is triggered
		*/
		@Override
		public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
			//boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
			//menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
			return super.onPrepareOptionsMenu(menu);
		}
		    
}
		
