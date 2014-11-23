package betmo.com.betmo.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import betmo.com.betmo.R;
import betmo.com.betmo.constant.ActionType;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/23/14.
 */
public class ActionDialog extends Dialog implements View.OnClickListener{

    @InjectView(R.id.buttons_comment)
    ImageButton mButtonComment;
    @InjectView(R.id.buttons_close)
    ImageButton mButtonClose;
    @InjectView(R.id.buttons_bet)
    ImageButton mButtonBet;
    @InjectView(R.id.buttons_info)
    ImageButton mButtonInfo;

    private OnActionClickedListener mListener;

    public ActionDialog(Context context, OnActionClickedListener listener) {
        super(context, android.R.style.Theme_Black_NoTitleBar);
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.betmo_dialog_buttons);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ButterKnife.inject(this);
        initializeButtons();
    }
    @Override
    public void onBackPressed() {
        this.dismiss();
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.buttons_bet:
                mListener.onActionClicked(ActionType.BET,"");
                break;
            case R.id.buttons_close:
                break;
            case R.id.buttons_comment:
                mListener.onActionClicked(ActionType.COMMENT,"");
                break;
            case R.id.buttons_info:
                mListener.onActionClicked(ActionType.INFO,"");
                break;
            default:
                break;
        }
        this.dismiss();
    }

    private void initializeButtons(){
        mButtonComment.setOnClickListener(this);
        mButtonClose.setOnClickListener(this);
        mButtonBet.setOnClickListener(this);
        mButtonInfo.setOnClickListener(this);
    }

    public interface OnActionClickedListener {
        public void onActionClicked(ActionType actionType, String message);
    }
}

