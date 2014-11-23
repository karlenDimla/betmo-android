package betmo.com.betmo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import betmo.com.betmo.R;
import betmo.com.betmo.adapter.NavigationListViewAdapter;
import betmo.com.betmo.fragment.AwardedFragment;
import betmo.com.betmo.fragment.LeaderBoardsFragment;
import betmo.com.betmo.fragment.NewFragment;
import betmo.com.betmo.fragment.WhatsHotFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/22/14.
 */
public class BetmoBaseActivity extends AbstractBetmoActivity implements DrawerLayout.DrawerListener, AdapterView.OnItemClickListener {

    @InjectView(R.id.betmo_activity_base_drawer_layout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.betmo_activity_base_content)
    FrameLayout mDrawerContentHolder;
    @InjectView(R.id.betmo_activity_base_drawer_listview)
    ListView mDrawerListView;
    @InjectView(R.id.betmo_activity_base_drawer_linearlayout_drawer_items)
    LinearLayout mLinearLayoutDrawerLayoutItemsHolder;

    private String mLastTitle;
    private static final int[] DRAWABLE_ICONS = new int[]{
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher};
    public String[] mActionBarTitles;
    private FragmentManager mFragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.betmo_activity_base_with_drawer);
        initializeActivity();
        initializeStrings();
        initializeNavigationDrawer();
        initializeFragments();
    }

    @Override
    protected String setInitialTitleOfActionBar() {
        return getResources().getString(R.string.app_name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                toggleDrawer();
                break;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        changeActionBarTitle(mLastTitle);
        InputMethodManager inputManager = (InputMethodManager) getSystemService
                (INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        mLastTitle = getActionBarTitle();
        changeActionBarTitle(mActionBarTitles[0]);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerStateChanged(int newState) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        toggleDrawer();
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new WhatsHotFragment();
                break;
            case 1:
                fragment = new NewFragment();
                break;
            case 2:
                 fragment = new AwardedFragment();
                break;
            case 3:
                fragment = new LeaderBoardsFragment();
                break;
        }
        changeFragment(fragment);
    }

    private void toggleDrawer() {
        if (mDrawerLayout.isDrawerOpen(mLinearLayoutDrawerLayoutItemsHolder)) {
            mDrawerLayout.closeDrawer(mLinearLayoutDrawerLayoutItemsHolder);
        } else {
            mDrawerLayout.openDrawer(mLinearLayoutDrawerLayoutItemsHolder);
        }
    }

    private void initializeActivity() {
        ButterKnife.inject(this);
    }

    private void initializeStrings() {
        mActionBarTitles = getResources().getStringArray(R.array.betmo_actionbar_titles);
        mLastTitle = mActionBarTitles[0];
    }

    private void initializeNavigationDrawer() {
        ArrayList<String> drawerListItemArray = new ArrayList<String>(Arrays.asList(
                getResources().getStringArray(R.array.betmo_navigation_items)));
        NavigationListViewAdapter navigationAdapter = new NavigationListViewAdapter(this,
                drawerListItemArray, DRAWABLE_ICONS, 0);
        mDrawerListView.setAdapter(navigationAdapter);
        mDrawerLayout.setDrawerListener(this);
        mDrawerLayout.closeDrawer(mLinearLayoutDrawerLayoutItemsHolder);
        mDrawerListView.setOnItemClickListener(this);
    }

    private void initializeFragments() {
        mFragmentManager = getSupportFragmentManager();
        Fragment fragment = new WhatsHotFragment();
        changeFragment(fragment);
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(mDrawerContentHolder.getId(), fragment);
        fragmentTransaction.commit();
    }
}
