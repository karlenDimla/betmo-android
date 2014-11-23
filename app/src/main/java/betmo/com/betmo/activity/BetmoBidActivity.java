package betmo.com.betmo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import betmo.com.betmo.R;
import betmo.com.betmo.adapter.ListItemAdapter;
import betmo.com.betmo.constant.BidFilters;
import betmo.com.betmo.constant.Categories;
import betmo.com.betmo.constant.DialogUtility;
import betmo.com.betmo.constant.VolleyRequestType;
import betmo.com.betmo.controller.BetmoApplication;
import betmo.com.betmo.listener.VolleyListener;
import betmo.com.betmo.model.BetmoBid;
import betmo.com.betmo.model.BetmoListItem;
import betmo.com.betmo.utility.CategoryUtility;
import betmo.com.betmo.utility.DateUtility;
import betmo.com.betmo.utility.DisplayUtility;
import betmo.com.betmo.view.ComfortaaTextView;
import betmo.com.betmo.view.NordicaTextView;
import betmo.com.betmo.volley.error.CustomVolleyError;
import betmo.com.betmo.volley.request.RequestGetBid;
import betmo.com.betmo.volley.request.RequestGetBidInfo;
import betmo.com.betmo.volley.response.GetBidResponse;
import betmo.com.betmo.volley.response.GetBidSpecificResponse;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/22/14.
 */
public class BetmoBidActivity extends AbstractBetmoActivity implements AdapterView.OnItemClickListener, VolleyListener {
    @InjectView(R.id.bid_view_category)
    NordicaTextView mCategoryTextView;
    @InjectView(R.id.bid_view_title)
    NordicaTextView mTitleTextView;
    @InjectView(R.id.bid_view_org)
    NordicaTextView mOrgTextView;
    @InjectView(R.id.bid_view_desc)
    ComfortaaTextView mDescTextView;
    @InjectView(R.id.bid_view_budget)
    ComfortaaTextView mBudgetTextView;
    @InjectView(R.id.bid_view_publish)
    ComfortaaTextView mPubDateTextView;
    @InjectView(R.id.bid_view_close)
    ComfortaaTextView mCloseDateTextView;
    @InjectView(R.id.bid_view_listview_item)
    ListView mItemsListView;

    private int mBidId;
    public static final String INTENT_KEY_BID_ID="bid id";
    private BetmoBid mBid;
    private ListItemAdapter mListItemAdapter;
    private String mActionBarTitle;
    private ProgressDialog mLoading;

    @Override
    protected String setInitialTitleOfActionBar() {
        return mActionBarTitle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.betmo_activity_bid_view);
        initializeActivity();
        mBidId = getIntent().getIntExtra(INTENT_KEY_BID_ID, 0);
        getBidWithBidId();
        mLoading = DialogUtility.createBasicProgressDialog(this,"Please wait...","Downloading Bid information from server...");
        DialogUtility.showDialog(mLoading);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, BetmoLineItemsActivity.class);
        intent.putExtra(BetmoLineItemsActivity.INTENT_KEY_BID_NAME,mBid.getTitle());
        intent.putExtra(BetmoLineItemsActivity.INTENT_KEY_ITEM_ID,mBid.getItems().get(position).getId());
        intent.putExtra(BetmoLineItemsActivity.INTENT_KEY_BID_ID,mBid.getId());
        intent.putExtra(BetmoLineItemsActivity.INTENT_KEY_CATEGORY,mBid.getCategory());
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return (super.onOptionsItemSelected(menuItem));
    }
    private void initializeActivity(){
        ButterKnife.inject(this);
    }

    private void initializeViews(){
        mCategoryTextView.setBackgroundResource(CategoryUtility.getDrawable(mBid.getCategory()));
        mCategoryTextView.setText(mBid.getCategory());
        mTitleTextView.setText(mBid.getTitle());
        mOrgTextView.setText(mBid.getOrg());
        mDescTextView.setText(mBid.getDescription());
        mBudgetTextView.setText("BUDGET: P "+ mBid.getBudget());
        String pub = TextUtils.isEmpty(mBid.getPublishDate()) ? "N/A" :mBid.getPublishDate();
        mPubDateTextView.setText("PUBLISHED: "+ pub);
        String close = TextUtils.isEmpty(mBid.getClosingDate()) ? "N/A" :mBid.getClosingDate();
        mCloseDateTextView.setText("CLOSED: "+close);
        mListItemAdapter = new ListItemAdapter(this,mBid.getCategory(),mBid.getItems());
        mItemsListView.setAdapter(mListItemAdapter);
        mItemsListView.setOnItemClickListener(this);
    }

    private void getBidWithBidId(){
        RequestGetBidInfo requestGetBid = new RequestGetBidInfo(mBidId, 0, 20,this);
        BetmoApplication.getEngine().getVolleyHelper().addToRequestQueue(requestGetBid);
    }
    @Override
    public void onResponse(VolleyRequestType request, Object response){
        DialogUtility.dismissDialog(mLoading);
        if(request == VolleyRequestType.GET_BID_INFO){
            if(response instanceof BetmoBid){
                BetmoBid bid = (BetmoBid) response;
                mBid = bid;
                mActionBarTitle = mBid.getTitle();
                changeActionBarTitle(mActionBarTitle);
                initializeViews();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyRequestType request, CustomVolleyError error){
        DialogUtility.dismissDialog(mLoading);
        Toast.makeText(this, "Something went wrong! Try again.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
