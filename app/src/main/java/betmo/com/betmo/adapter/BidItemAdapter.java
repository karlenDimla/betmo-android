package betmo.com.betmo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import betmo.com.betmo.R;
import betmo.com.betmo.model.BetmoBidItem;
import betmo.com.betmo.utility.CategoryUtility;
import betmo.com.betmo.view.ComfortaaTextView;
import betmo.com.betmo.view.NordicaTextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/22/14.
 */
public class BidItemAdapter extends ArrayAdapter<BetmoBidItem> {
    private static final String TAG = BidItemAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<BetmoBidItem> mBidItemList;

    public BidItemAdapter(Context context, ArrayList<BetmoBidItem> bidItems) {
        super(context, R.layout.betmo_item_listview_bid, bidItems);
        mContext = context;
        mBidItemList = bidItems;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final BetmoBidItem bitItem = mBidItemList.get(position);
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.betmo_item_listview_bid, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.mCategoryText.setText(bitItem.getBidCategory());
        holder.mTitleText.setText(bitItem.getBidTitle());
        holder.mOrgText.setText(bitItem.getBidOrg());
        holder.mCircleCategory.setText(CategoryUtility.getAbbreviation(bitItem.getBidCategory()));
        holder.mCircleCategory.setBackgroundResource(CategoryUtility.getDrawable(bitItem.getBidCategory()));

        return view;
    }

    class ViewHolder {
        @InjectView(R.id.betmo_item_listview_textview_category)
        TextView mCircleCategory;
        @InjectView(R.id.betmo_item_listview_textview_category_text)
        ComfortaaTextView mCategoryText;
        @InjectView(R.id.betmo_item_listview_textview_organization)
        ComfortaaTextView mOrgText;
        @InjectView(R.id.betmo_item_listview_textview_title)
        NordicaTextView mTitleText;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
