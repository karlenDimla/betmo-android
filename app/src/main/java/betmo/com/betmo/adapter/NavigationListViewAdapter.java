package betmo.com.betmo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import betmo.com.betmo.R;
import betmo.com.betmo.view.ComfortaaTextView;
import betmo.com.betmo.view.NordicaTextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/22/14.
 */
public class NavigationListViewAdapter  extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mDrawerItems;
    private int[] mDrawerIcons;
    private int mSelectedItem;

    public NavigationListViewAdapter(Context context, ArrayList<String> drawerItems,
                                   int[] drawerIcons, int selectedItem) {
        this.mContext = context;
        this.mDrawerItems = drawerItems;
        this.mDrawerIcons = drawerIcons;
        this.mSelectedItem = selectedItem;
    }

    public int getSelectedItem() {
        return this.mSelectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.mSelectedItem = selectedItem;
    }

    @Override
    public int getCount() {
        return mDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mDrawerItems.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.betmo_item_navigation, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.drawerItemName.setText(mDrawerItems.get(position));
        holder.drawerItemName.setCompoundDrawablesWithIntrinsicBounds(mDrawerIcons[position], 0, 0, 0);
        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.betmo_navigation_drawer_text)
        ComfortaaTextView drawerItemName;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}

