package betmo.com.betmo.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import betmo.com.betmo.R;
import betmo.com.betmo.constant.ActionType;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kdimla on 11/23/14.
 */
public class CommentDialog  extends Dialog implements View.OnClickListener{

    @InjectView(R.id.comment_send_close)
    ImageButton mButtonClose;
    @InjectView(R.id.comment_send_submit)
    Button mButtonSubmit;
    @InjectView(R.id.comment_send_edittext)
    ComfortaaEditText mEditText;

    private OnActionClickedListener mListener;

    public CommentDialog(Context context, OnActionClickedListener listener) {
        super(context, android.R.style.Theme_Black_NoTitleBar);
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.betmo_comment_dialog);
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
            case R.id.comment_send_close:
                break;
            case R.id.comment_send_submit:
                mListener.onActionClicked(ActionType.SUBMIT, mEditText.getText().toString());
                break;
            default:
                break;
        }
        this.dismiss();
    }

    private void initializeButtons(){
        mButtonSubmit.setOnClickListener(this);
        mButtonClose.setOnClickListener(this);
    }

    public interface OnActionClickedListener {
        public void onActionClicked(ActionType actionType, String message);
    }
}