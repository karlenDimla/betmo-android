package betmo.com.betmo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import betmo.com.betmo.R;
import betmo.com.betmo.model.BetmoBidItem;
import betmo.com.betmo.model.BetmoListItem;
import betmo.com.betmo.utility.CategoryUtility;
import betmo.com.betmo.view.ComfortaaTextView;
import betmo.com.betmo.view.NordicaTextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/22/14.
 */
public class ListItemAdapter extends ArrayAdapter<BetmoListItem> {
    private static final String TAG = BidItemAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<BetmoListItem> mListItemList;
    private String mCategory;

    public ListItemAdapter(Context context, String category, ArrayList<BetmoListItem> listItems) {
        super(context, R.layout.betmo_item_listview_bid, listItems);
        mContext = context;
        mListItemList = listItems;
        mCategory = category;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final BetmoListItem listItem = mListItemList.get(position);
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.betmo_item_listitem, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.mNameTextView.setText(listItem.getName());
        holder.mUomTextView.setText(listItem.getUom());
        holder.mQtyTextView.setBackgroundResource(CategoryUtility.getDrawable(mCategory));
//        holder.mQtyTextView.setText(listItem.getQty());

        return view;
    }

    class ViewHolder {
        @InjectView(R.id.list_item_name)
        NordicaTextView mNameTextView;
        @InjectView(R.id.list_item_uom)
        ComfortaaTextView mUomTextView;
        @InjectView(R.id.list_item_qty)
        ComfortaaTextView mQtyTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
