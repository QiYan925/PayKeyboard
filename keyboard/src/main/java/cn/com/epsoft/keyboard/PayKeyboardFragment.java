package cn.com.epsoft.keyboard;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.WindowManager;
import cn.com.epsoft.keyboard.widget.PayKeyboardView;
import cn.com.epsoft.keyboard.widget.PayKeyboardView.OnKeyboardListener;

/**
 * @author 启研
 * @created at 2017/10/10 14:11
 */

public class PayKeyboardFragment extends DialogFragment {

  AppCompatDialog mDialog;
  PayKeyboardView panel;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    if (mDialog == null) {
      mDialog = new AppCompatDialog(getActivity(), R.style.KeyboardDialog);
      panel = new PayKeyboardView(getActivity());
      panel.setTitle(title);
      panel.setOnKeyboardListener(listener);
      mDialog.setContentView(panel);
      WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
      lp.width = WindowManager.LayoutParams.MATCH_PARENT;
      lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
      lp.alpha = 7f; // 透明度
      lp.gravity = Gravity.BOTTOM;
      mDialog.getWindow().setAttributes(lp);
    }
    panel.clear();
    return mDialog;
  }

  OnKeyboardListener listener;
  String title;

  public void setTitle(String title) {
    this.title = title;
    if (panel != null) {
      panel.setTitle(title);
    }
  }

  public void setOnKeyboardListener(OnKeyboardListener l) {
    this.listener = l;
    if (panel != null) {
      panel.setOnKeyboardListener(l);
    }
  }

}
