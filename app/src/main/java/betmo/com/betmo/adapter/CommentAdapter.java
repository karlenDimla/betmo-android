package betmo.com.betmo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Date;

import betmo.com.betmo.R;
import betmo.com.betmo.model.BetmoListItem;
import betmo.com.betmo.model.Comment;
import betmo.com.betmo.utility.CategoryUtility;
import betmo.com.betmo.utility.DateUtility;
import betmo.com.betmo.utility.DisplayUtility;
import betmo.com.betmo.view.ComfortaaTextView;
import betmo.com.betmo.view.NordicaTextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/23/14.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {
    private static final String TAG = BidItemAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<Comment> mCommentList;

    public CommentAdapter(Context context,ArrayList<Comment> comments) {
        super(context, R.layout.betmo_item_comment, comments);
        mContext = context;
        mCommentList = comments;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Comment listItem = mCommentList.get(position);
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.betmo_item_comment, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.mNameTextView.setText(listItem.getFirstname());
        holder.mTimeTextView.setText(DateUtility.getDisplayableDate(listItem.getCreatedAt()));
        holder.mMessageTextView.setText(listItem.getComment());

        return view;
    }

    class ViewHolder {
        @InjectView(R.id.comment_name)
        NordicaTextView mNameTextView;
        @InjectView(R.id.comment_time)
        ComfortaaTextView mTimeTextView;
        @InjectView(R.id.comment_message)
        ComfortaaTextView mMessageTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
