package betmo.com.betmo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import betmo.com.betmo.R;
import betmo.com.betmo.model.BetmoBidderItem;
import betmo.com.betmo.model.BetmoListItem;
import betmo.com.betmo.utility.CategoryUtility;
import betmo.com.betmo.view.ComfortaaTextView;
import betmo.com.betmo.view.NordicaTextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/23/14.
 */
public class BidderItemAdapter extends ArrayAdapter<BetmoBidderItem> {
    private static final String TAG = BidItemAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<BetmoBidderItem> mBidderList;
    private String mCategory;

    public BidderItemAdapter(Context context, String category, ArrayList<BetmoBidderItem> bidderItems) {
        super(context, R.layout.betmo_item_bidder, bidderItems);
        mContext = context;
        mBidderList = bidderItems;
        mCategory = category;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final BetmoBidderItem listItem = mBidderList.get(position);
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.betmo_item_bidder, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.mBudgetTV.setText(""+listItem.getBudget());
        holder.mNameTV.setText(listItem.getName());
        holder.mBetsTV.setBackgroundResource(CategoryUtility.getDrawable(mCategory));
        if(listItem.isAwarded()){
            view.setBackgroundColor(mContext.getResources().getColor(R.color.betmo_color_bid_listview_item_org));
            holder.mNameTV.setTextColor(mContext.getResources().getColor(R.color.betmo_color_default_textcolor));
            holder.mBudgetTV.setTextColor(mContext.getResources().getColor(R.color.betmo_color_default_textcolor));
        }else if(listItem.isPopular()){
            view.setBackgroundColor(mContext.getResources().getColor(R.color.betmo_color_bid_listview_item_title));
            holder.mNameTV.setTextColor(mContext.getResources().getColor(R.color.betmo_color_default_textcolor));
            holder.mBudgetTV.setTextColor(mContext.getResources().getColor(R.color.betmo_color_default_textcolor));
        }
//        holder.mBetsTV.setText(listItem.getBets());

        return view;
    }

    class ViewHolder {
        @InjectView(R.id.bidder_item_bets)
        ComfortaaTextView mBetsTV;
        @InjectView(R.id.bidder_item_name)
        NordicaTextView mNameTV;
        @InjectView(R.id.bidder_item_budget)
        ComfortaaTextView mBudgetTV;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
