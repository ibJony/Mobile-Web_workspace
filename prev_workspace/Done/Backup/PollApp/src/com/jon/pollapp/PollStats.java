package com.jon.pollapp;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import com.jon.pollApp.R;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;


public class PollStats extends Activity{
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //setContentView(R.layout.activity_piechart);
	        
					// Draw the Income vs Expense Chart
					openChart();
					
	 }
	    
	 private void openChart(){    	
	    	
	    	// Pie Chart Section Names
	    	String[] code = new String[] {
	    			"Yes", "No" 
	    	};    	
	    	
	    	// Pie Chart Section Value
	    	double[] distribution = { 3.9, 12.9 } ;
	    	
	    	// Color of each Pie Chart Sections
	    	int[] colors = { Color.BLUE, Color.RED };
	    	
	    	// Instantiating CategorySeries to plot Pie Chart    	
	    	CategorySeries distributionSeries = new CategorySeries("Polling Results");
	    	for(int i=0 ;i < distribution.length;i++){
	    		// Adding a slice with its values and name to the Pie Chart
	    		distributionSeries.add(code[i], distribution[i]);
	    	} 
	    	
	    	
	    	// Instantiating a renderer for the Pie Chart
	    	DefaultRenderer defaultRenderer  = new DefaultRenderer();    	
	    	for(int i = 0 ;i<distribution.length;i++){    		
	    		SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();    	
	    		seriesRenderer.setColor(colors[i]);
	    		seriesRenderer.setDisplayChartValues(true);
	    		// Adding a renderer for a slice
	    		defaultRenderer.addSeriesRenderer(seriesRenderer);
	    	}
	    	
	    	defaultRenderer.setChartTitle("Polls");
	    	defaultRenderer.setChartTitleTextSize(20);
	    	defaultRenderer.setZoomButtonsVisible(true);    	    		
	    		
	    	// Creating an intent to plot bar chart using dataset and multipleRenderer    	
	    	Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries , defaultRenderer, "Poll Chart Results");    	
	    	// Start Activity
	    	startActivity(intent);
	    	
	    }

}
