package info.android.IRCTC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.android.IRCTC.R;
import info.android.IRCTC.helper.NavDrawerListAdapter;
import info.android.IRCTC.picasa.model.Category;
import info.android.IRCTC.NavDrawerItem;
import info.android.IRCTC.app.AppController;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
/**/

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


@SuppressLint("DefaultLocale")
public class New_Booking extends Activity {
	ImageView myImageView;
	private static final String TAG = New_Booking.class.getSimpleName();
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// Navigation drawer title
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private List<Category> albumsList;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	
	private ArrayList<String> AL;
	
	
	/*elements */
	TextView lblFrom;
	TextView txtStationName;
	TextView txtStationCode;
	Spinner spinner;
	/**/
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_new__booking);
	      overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);

	      	mTitle = mDrawerTitle = getTitle();
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

			navDrawerItems = new ArrayList<NavDrawerItem>();

			// Getting the albums from shared preferences
			albumsList = AppController.getInstance().getPrefManger().getCategories();
			
			String str[]=new String[]{"Account Name","Account Email","My Account","New Booking","My Booking","Cancel Ticket","My Account","More..","Log Out"};
			AL=new ArrayList<String>(Arrays.asList(str));

// 			Insert "Recently Added" in navigation drawer first position
//			Category recentAlbum = new Category(null,getString(R.string.nav_drawer_recently_added));
//			albumsList.add(0, recentAlbum);
			// Loop through albums in add them to navigation drawer adapter
			
			for(int i=0;i<AL.size();i++){
				navDrawerItems.add(new NavDrawerItem(AL.get(i),AL.get(i)));
			}
			
			/*for(String c:AL){
				navDrawerItems.add(new NavDrawerItem(c.toString(), c.toUpperCase()));
			}*/
			/*for (Category a : albumsList) {
				navDrawerItems.add(new NavDrawerItem(a.getId(), a.getTitle()));
			}*/
			mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
			// Setting the nav drawer list adapter
			adapter = new NavDrawerListAdapter(getApplicationContext(),	navDrawerItems);
			mDrawerList.setAdapter(adapter);
			// Enabling action bar app icon and behaving it as toggle button
			getActionBar().setDisplayHomeAsUpEnabled(true);
			getActionBar().setHomeButtonEnabled(true);
			getActionBar().setIcon(	new ColorDrawable(getResources().getColor(android.R.color.transparent)));
			mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
				public void onDrawerClosed(View view) {
					getActionBar().setTitle(mTitle);
					// calling onPrepareOptionsMenu() to show action bar icons
					invalidateOptionsMenu();
				}
				public void onDrawerOpened(View drawerView) {
					getActionBar().setTitle(mDrawerTitle);
					// calling onPrepareOptionsMenu() to hide action bar icons
					invalidateOptionsMenu();
				}
			};
			mDrawerLayout.setDrawerListener(mDrawerToggle);
			if (savedInstanceState == null) {
				// on first time display view for first nav item
				displayView(0);
			}
			/*Button Clicked Listener*/
			txtStationCode.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getApplicationContext(),Search_Station.class);
					startActivity(i);
					finish();
				}
			});
			
	  }
		/**
		 * Navigation drawer menu item click listener
		 * */
		private class SlideMenuClickListener implements	ListView.OnItemClickListener {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				// display view for selected nav drawer item
				displayView(position);
			}
		}

	/*	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}*/

		/**
		 * On menu item selected
		 * */
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// toggle nav drawer on selecting action bar app icon/title
			if (mDrawerToggle.onOptionsItemSelected(item)) {
				return true;
			}
			// Handle action bar actions click
			switch (item.getItemId()) {
			case R.id.action_settings:
				// Selected settings menu item
				// launch Settings activity
				Intent intent = new Intent(New_Booking.this,SettingsActivity.class);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
		}
		private void displayView(int position) {
			
			lblFrom=(TextView)findViewById(R.id.lblFrom);
			txtStationName=(TextView)findViewById(R.id.txtStationName);
			txtStationCode=(TextView)findViewById(R.id.txtStationCode);

			lblFrom.setText("From");
			txtStationCode.setText("Ahmedabad Jn");
			txtStationName.setText("Adi");
}

		@Override
		public void setTitle(CharSequence title) {
			mTitle = title;
			getActionBar().setTitle(mTitle);
		}

		/**
		 * When using the ActionBarDrawerToggle, you must call it during
		 * onPostCreate() and onConfigurationChanged()...
		 */

		@Override
		protected void onPostCreate(Bundle savedInstanceState) {
			super.onPostCreate(savedInstanceState);
			// Sync the toggle state after onRestoreInstanceState has occurred.
			mDrawerToggle.syncState();
		}

		@Override
		public void onConfigurationChanged(Configuration newConfig) {
			super.onConfigurationChanged(newConfig);
			// Pass any configuration change to the drawer toggls
			mDrawerToggle.onConfigurationChanged(newConfig);
		}
	  @Override
	    protected void onPause() {
			// Whenever this activity is paused (i.e. looses focus because another activity is started etc)
			// Override how this activity is animated out of view
			// The new activity is kept still and this activity is pushed out to the left
	        overridePendingTransition(R.anim.hold, R.anim.push_out_to_left);
	        super.onPause();
	    }
	  public void clockwise(View view){
		  myImageView = (ImageView)findViewById(R.id.imageview);
	      Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
	      myImageView.startAnimation(animation);
	   }
}
