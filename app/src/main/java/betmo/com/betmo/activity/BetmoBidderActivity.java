package betmo.com.betmo.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import betmo.com.betmo.R;
import betmo.com.betmo.adapter.BidderItemAdapter;
import betmo.com.betmo.adapter.CommentAdapter;
import betmo.com.betmo.constant.ActionType;
import betmo.com.betmo.constant.DialogUtility;
import betmo.com.betmo.constant.Status;
import betmo.com.betmo.model.BetmoBidder;
import betmo.com.betmo.model.BetmoBidderItem;
import betmo.com.betmo.model.BetmoListItemComplete;
import betmo.com.betmo.model.Comment;
import betmo.com.betmo.utility.CategoryUtility;
import betmo.com.betmo.view.ActionDialog;
import betmo.com.betmo.view.ComfortaaTextView;
import betmo.com.betmo.view.NordicaTextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/23/14.
 */
public class BetmoBidderActivity extends AbstractBetmoActivity {
    @InjectView(R.id.bidder_bets)
    NordicaTextView mBetsTextView;
    @InjectView(R.id.bidder_title)
    NordicaTextView mTitleTextView;
    @InjectView(R.id.bidder_org)
    NordicaTextView mOrgTextView;
    @InjectView(R.id.bid_view_budget)
    ComfortaaTextView mBudgetTextView;
    @InjectView(R.id.bidder_comments_list)
    ListView mCommentListView;

    private CommentAdapter mCommentApapter;

    private String mBidderId;
    public static final String INTENT_KEY_BIDDER_ID = "bid id";
    public static final String INTENT_KEY_BID_NAME = "bid name";
    public static final String INTENT_KEY_ITEM_ID = "item id";
    public static final String INTENT_KEY_CATEGORY = "category";


    private BetmoBidder mBidder;
    private String mActionBarTitle;

    @Override
    protected String setInitialTitleOfActionBar() {
        return mActionBarTitle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.betmo_activity_bidder_info);
        initializeActivity();

        mBidderId = getIntent().getStringExtra(INTENT_KEY_BIDDER_ID);

        getBidderWithBidId(mBidderId);
        mActionBarTitle = mBidder.getName();
        changeActionBarTitle(mActionBarTitle);

        initializeViews();
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

    private void initializeActivity() {
        ButterKnife.inject(this);
    }

    private void initializeViews() {
//        mBetsTextView.setText(mBidder.getBets());
        mTitleTextView.setText(mBidder.getName());
        mBudgetTextView.setText("BUDGET: P "+mBidder.getBudget());
        mOrgTextView.setText(mBidder.getOrg());
        mCommentApapter = new CommentAdapter(this, mBidder.getComments());
        mCommentListView.setAdapter(mCommentApapter);
    }

    private void getBidderWithBidId(String mItemId) {
        mBidder = new BetmoBidder();
        mBidder.setBudget(100.75);
        mBidder.setId("Sacks");
        mBidder.setName("Bidder number 001");
        mBidder.setBets(123);
        mBidder.setOrg("Bidder number 001 organization");
        Comment com1 = new Comment();
        com1.setFirstname("Arisa");
        com1.setLastName("Ochavez");
        com1.setComment("This is a comment.");
        com1.setCreatedAt("2014-09-20T16:00:00.000Z");
        Comment com2 = new Comment();
        com2.setFirstname("KL");
        com2.setLastName("Dims");
        com2.setComment("This is a another comment.");
        com2.setCreatedAt("2014-09-20T16:00:00.000Z");
        ArrayList<Comment> bidders = new ArrayList<Comment>();
        bidders.add(com1);
        bidders.add(com2);
        bidders.add(com1);
        mBidder.setComments(bidders);
    }

}