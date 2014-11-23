package betmo.com.betmo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import betmo.com.betmo.R;
import betmo.com.betmo.activity.BetmoBaseActivity;
import betmo.com.betmo.activity.BetmoBidActivity;
import betmo.com.betmo.activity.BetmoLineItemsActivity;
import betmo.com.betmo.adapter.BidItemAdapter;
import betmo.com.betmo.constant.BidFilters;
import betmo.com.betmo.constant.Categories;
import betmo.com.betmo.constant.VolleyRequestType;
import betmo.com.betmo.controller.BetmoApplication;
import betmo.com.betmo.listener.VolleyListener;
import betmo.com.betmo.model.BetmoBidItem;
import betmo.com.betmo.volley.error.CustomVolleyError;
import betmo.com.betmo.volley.request.RequestGetBid;
import betmo.com.betmo.volley.response.GetBidResponse;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/22/14.
 */
public class AwardedFragment extends Fragment implements AdapterView.OnItemClickListener, VolleyListener {
    @InjectView(R.id.betmo_fragment_whats_hot_listview)
    public ListView mListView;
    public BidItemAdapter mAdapter;
    public ArrayList<BetmoBidItem> mBidItemsList;
    @InjectView(R.id.betmo_fragment_whats_hot_loading)
    public ProgressBar mProgressBar;

    public static AwardedFragment newInstance() {
        AwardedFragment fragment = new AwardedFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.betmo_fragment_whats_hot, null);
        ButterKnife.inject(this, view);
        getBidList();
        return view;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), BetmoBidActivity.class);
        intent.putExtra(BetmoLineItemsActivity.INTENT_KEY_BID_ID,mBidItemsList.get(position).getId());
        startActivity(intent);
    }

    private void initializeList(){
        mAdapter = new BidItemAdapter(getActivity(),mBidItemsList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    private void getBidList(){
        mProgressBar.setVisibility(View.VISIBLE);
        RequestGetBid requestGetBid = new RequestGetBid(BidFilters.HOT_BIDS, 0, 20,this);
        BetmoApplication.getEngine().getVolleyHelper().addToRequestQueue(requestGetBid);
//        mBidItemsList = new ArrayList<BetmoBidItem>();
//        BetmoBidItem item1 = new BetmoBidItem(1, "Bid Title 001", Categories.CAT_MED_SUP, "Organization 001");
//        BetmoBidItem item2 = new BetmoBidItem(2, "Bid Title 002", Categories.CAT_OFF_EQ_CONS, "Organization 002");
//        BetmoBidItem item3 = new BetmoBidItem(3, "Bid Title 003", Categories.CAT_SERVICES, "Organization 003");
//        mBidItemsList.add(item1);
//        mBidItemsList.add(item2);
//        mBidItemsList.add(item3);

    }

    @Override
    public void onResponse(VolleyRequestType request, Object response){
        mProgressBar.setVisibility(View.GONE);
        if(request == VolleyRequestType.GET_BIDS){
            if(response instanceof GetBidResponse){
                GetBidResponse getBidResponse = (GetBidResponse) response;
                mBidItemsList = getBidResponse.getBids();
                initializeList();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyRequestType request, CustomVolleyError error){
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Something went wrong! Try again.", Toast.LENGTH_SHORT).show();
    }
}
