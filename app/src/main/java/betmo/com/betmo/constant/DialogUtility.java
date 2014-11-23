package betmo.com.betmo.constant;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by kdimla on 11/23/14.
 */
public class DialogUtility {
    public static ProgressDialog createBasicProgressDialog(Context context, int theme, String title, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context, theme);
        customizeBasicProgressDialog(progressDialog, title, message, false);
        return progressDialog;
    }

    public static ProgressDialog createBasicProgressDialog(Context context, String title, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        customizeBasicProgressDialog(progressDialog, title, message, false);
        return progressDialog;
    }

    private static ProgressDialog customizeBasicProgressDialog(ProgressDialog progressDialog,
                                                               String title, String message, boolean cancelable) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        return progressDialog;
    }

    public static void showDialog(Dialog dialog) {
        if (dialog != null) {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }
    }

    public static void dismissDialog(Dialog dialog) {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
