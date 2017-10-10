package cn.com.epsoft.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;
import cn.com.epsoft.keyboard.PayKeyboardFragment;
import cn.com.epsoft.keyboard.widget.PayKeyboardView;
import cn.com.epsoft.keyboard.widget.PayKeyboardView.OnKeyboardListener;

public class MainActivity extends AppCompatActivity implements PayKeyboardView.OnKeyboardListener {

  PayKeyboardFragment dialogFrag;
  AppCompatDialog mDialog;
  PayKeyboardView panel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    panel = (PayKeyboardView) findViewById(R.id.keyboardPanel);
    panel.setOnKeyboardListener(new OnKeyboardListener() {
      @Override
      public void onComplete(String one, String two, String three, String four, String five,
          String six) {
        Toast.makeText(getBaseContext(), "数据为：" + one + two + three + four + five + six,
            Toast.LENGTH_LONG).show();
      }

      @Override
      public void onBack() {
      }
    });
    findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
      PayKeyboardView panel;

      @Override
      public void onClick(View view) {
        if (mDialog == null) {
          mDialog = new AppCompatDialog(MainActivity.this, R.style.KeyboardDialog);
          panel = new PayKeyboardView(getBaseContext());
          panel.setOnKeyboardListener(MainActivity.this);
          panel.setTitle("Dialog弹出");
          mDialog.setContentView(panel);
          WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
          lp.width = WindowManager.LayoutParams.MATCH_PARENT;
          lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
          lp.alpha = 7f; // 透明度
          lp.gravity = Gravity.BOTTOM;
          mDialog.getWindow().setAttributes(lp);
        }
        panel.clear();
        mDialog.show();
      }
    });
    findViewById(R.id.btn1).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (dialogFrag == null) {
          dialogFrag = new PayKeyboardFragment();
          dialogFrag.setTitle("Fragment弹出");
          dialogFrag.setOnKeyboardListener(MainActivity.this);
        }
        dialogFrag.show(getFragmentManager(), "payKeyboardDialog");
      }
    });
  }

  @Override
  public void onComplete(String one, String two, String three, String four, String five,
      String six) {
    Toast.makeText(getBaseContext(), "数据为：" + one + two + three + four + five + six,
        Toast.LENGTH_LONG).show();
  }

  @Override
  public void onBack() {
    if (mDialog != null) {
      mDialog.dismiss();
    }
    if (dialogFrag != null) {
      dialogFrag.dismiss();
    }
  }
}
