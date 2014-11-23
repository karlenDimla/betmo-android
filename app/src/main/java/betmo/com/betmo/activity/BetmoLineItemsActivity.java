package betmo.com.betmo.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import betmo.com.betmo.R;
import betmo.com.betmo.adapter.BidderItemAdapter;
import betmo.com.betmo.constant.ActionType;
import betmo.com.betmo.constant.DialogUtility;
import betmo.com.betmo.constant.Status;
import betmo.com.betmo.constant.VolleyRequestType;
import betmo.com.betmo.controller.BetmoApplication;
import betmo.com.betmo.listener.VolleyListener;
import betmo.com.betmo.model.BetmoBid;
import betmo.com.betmo.model.BetmoBidderItem;
import betmo.com.betmo.model.BetmoListItem;
import betmo.com.betmo.model.BetmoListItemComplete;
import betmo.com.betmo.utility.CategoryUtility;
import betmo.com.betmo.view.ActionDialog;
import betmo.com.betmo.view.ComfortaaButton;
import betmo.com.betmo.view.ComfortaaTextView;
import betmo.com.betmo.view.CommentDialog;
import betmo.com.betmo.view.NordicaTextView;
import betmo.com.betmo.volley.error.CustomVolleyError;
import betmo.com.betmo.volley.request.RequestGetBidInfo;
import betmo.com.betmo.volley.request.RequestGetItemInfo;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/22/14.
 */
public class BetmoLineItemsActivity extends AbstractBetmoActivity implements AdapterView.OnItemClickListener, View.OnClickListener, ActionDialog.OnActionClickedListener, CommentDialog.OnActionClickedListener, VolleyListener {
    @InjectView(R.id.activity_listitem_qty)
    ComfortaaTextView mQtyTextView;
    @InjectView(R.id.activity_listitem_oum)
    ComfortaaTextView mOumTextView;
    @InjectView(R.id.activity_listitem_title)
    NordicaTextView mTitleTextView;
    @InjectView(R.id.activity_listitem_desc)
    NordicaTextView mDescTextView;
    @InjectView(R.id.activity_listitem_budget)
    NordicaTextView mBudgetTextView;
    @InjectView(R.id.holder_isawarded)
    LinearLayout mAwardedHolder;
    @InjectView(R.id.awarded_item)
    View mAwardedView;
    @InjectView(R.id.peopleschoice_item)
    View mPeoplesChoiceView;
    @InjectView(R.id.activity_listitem_isawarded_total_bids)
    NordicaTextView mTotalBidsTextView;
//    @InjectView(R.id.activity_listitem_betbutton)
//    ComfortaaButton mBetButton;
    @InjectView(R.id.activity_listitem_bidders)
    ListView mBiddersListView;

    private BidderItemAdapter mBidderApapter;

    private int mBidId;
    private int mItemId;
    private String mBidName;
    public static final String INTENT_KEY_BID_ID = "bid id";
    public static final String INTENT_KEY_BID_NAME = "bid name";
    public static final String INTENT_KEY_ITEM_ID = "item id";
    public static final String INTENT_KEY_CATEGORY = "category";


    private BetmoListItemComplete mListItem;
    private String mActionBarTitle;
    private String mCategory;
    private ProgressDialog mLoading;

    @Override
    protected String setInitialTitleOfActionBar() {
        return mActionBarTitle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.betmo_activity_listitem);
        initializeActivity();

        mBidId = getIntent().getIntExtra(INTENT_KEY_BID_ID, 0);
        mItemId = getIntent().getIntExtra(INTENT_KEY_ITEM_ID, 0);
        mBidName = getIntent().getStringExtra(INTENT_KEY_BID_NAME);
        mCategory = getIntent().getStringExtra(INTENT_KEY_CATEGORY);

        if(mItemId == 0){

        }

        mLoading = DialogUtility.createBasicProgressDialog(this, "Please wait...","Downloading List item data from server...");
        DialogUtility.showDialog(mLoading);
        getItemWithBidId(mItemId);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DialogUtility.showDialog(new ActionDialog(this, this));
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

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
//            case R.id.activity_listitem_betbutton:
//                //launch activity
//                break;
        }
    }

    private void initializeActivity() {
        ButterKnife.inject(this);
    }

    private void initializeViews() {
        mQtyTextView.setBackgroundResource(CategoryUtility.getDrawable(mCategory));
        //mQtyTextView.setText(mListItem.getQty());
        mTitleTextView.setText(mListItem.getName());
        mOumTextView.setText(mListItem.getUom());
        mDescTextView.setText(mListItem.getDescription());
        mBudgetTextView.setText("BUDGET: P " + mListItem.getBudget());
        mTotalBidsTextView.setText("TOTAL BIDS: " + mListItem.getBidderTotal());
        mBidderApapter = new BidderItemAdapter(this,mCategory,mListItem.getBidders());
        mBiddersListView.setAdapter(mBidderApapter);
        mBiddersListView.setOnItemClickListener(this);

        if (isAwarded(mListItem)) {
            initializeIsAwardedViews();
        } else {
            initializeNotAwardedViews();
        }
    }

    private Boolean isAwarded(BetmoListItemComplete item) {
        if (item.getStatus().equals(Status.AWARDED)) {
            return true;
        }
        return false;
    }

    private void initializeIsAwardedViews() {
        mAwardedHolder.setVisibility(View.VISIBLE);
        mTotalBidsTextView.setVisibility(View.VISIBLE);
        ComfortaaTextView mACountTextView = (ComfortaaTextView) mAwardedView.findViewById(R.id.awarded_vote_count);
        ComfortaaTextView mAtitleTextView = (ComfortaaTextView) mAwardedView.findViewById(R.id.awarded_vote_title);
        ComfortaaTextView mPCountTextView = (ComfortaaTextView) mPeoplesChoiceView.findViewById(R.id.peopleschoice_vote_count);
        ComfortaaTextView mPtitleTextView = (ComfortaaTextView) mPeoplesChoiceView.findViewById(R.id.peopleschoice_title);
        for (int cnt = 0; cnt < mListItem.getBidders().size(); cnt++) {
            if (mListItem.getBidders().get(cnt).isAwarded()) {
                mACountTextView.setText(mListItem.getBidders().get(cnt).getBets() + " BETS");
                mAtitleTextView.setText(mListItem.getBidders().get(cnt).getName());
            } else if (mListItem.getBidders().get(cnt).isPopular()) {
                mPCountTextView.setText(mListItem.getBidders().get(cnt).getBets() + " BETS");
                mPtitleTextView.setText(mListItem.getBidders().get(cnt).getName());
            }
        }
    }

    private void initializeNotAwardedViews() {
        mAwardedHolder.setVisibility(View.GONE);
        mTotalBidsTextView.setVisibility(View.GONE);
    }

    private void getItemWithBidId(int mItemId) {

        RequestGetItemInfo requestGetItemInfo = new RequestGetItemInfo(mItemId,mBidId, 0, 20,this);
        BetmoApplication.getEngine().getVolleyHelper().addToRequestQueue(requestGetItemInfo);
    }

    public void onActionClicked(ActionType actionType){
        if(actionType == ActionType.BET){
            //show BET!
        }else if(actionType == ActionType.COMMENT){
            CommentDialog dialog = new CommentDialog(this, this);
            DialogUtility.showDialog(dialog);
        }else if(actionType == ActionType.INFO){
            Intent intent = new Intent(this, BetmoBidderActivity.class);
            startActivity(intent);
        }
    }

    public void onActionClicked(ActionType actionType, String message){
        if(actionType == ActionType.BET){
        }else if(actionType == ActionType.COMMENT){
            CommentDialog dialog = new CommentDialog(this, this);
            DialogUtility.showDialog(dialog);
        }else if(actionType == ActionType.INFO){
            Intent intent = new Intent(this, BetmoBidderActivity.class);
            startActivity(intent);
        }else
        if(actionType == ActionType.SUBMIT){
        }
    }

    @Override
    public void onResponse(VolleyRequestType request, Object response){
        DialogUtility.dismissDialog(mLoading);
        if(request == VolleyRequestType.GET_ITEM_INFO){
            if(response instanceof BetmoBid){
                BetmoListItemComplete item = (BetmoListItemComplete) response;
                mActionBarTitle = item.getName();
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
