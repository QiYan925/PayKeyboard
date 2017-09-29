package cn.com.epsoft.test;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;
import cn.com.epsoft.keyboard.KeyboardPanel;
import cn.com.epsoft.keyboard.KeyboardPanel.OnKeyboardListener;

public class MainActivity extends AppCompatActivity implements KeyboardPanel.OnKeyboardListener {

  Dialog mDialog;
  KeyboardPanel panel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    panel = (KeyboardPanel) findViewById(R.id.keyboardPanel);
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
      @Override
      public void onClick(View view) {
        if (mDialog == null) {
          mDialog = new Dialog(MainActivity.this, R.style.KeyboardDialog);
          KeyboardPanel panel = new KeyboardPanel(getBaseContext());
          panel.setOnKeyboardListener(MainActivity.this);
          panel.setTitle("仿支付宝密码输入");
          mDialog.setContentView(panel);
          WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
          lp.width = WindowManager.LayoutParams.MATCH_PARENT;
          lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
          lp.alpha = 7f; // 透明度
          lp.gravity = Gravity.BOTTOM;
          mDialog.getWindow().setAttributes(lp);
        }
        mDialog.show();
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
  }
}
