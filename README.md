# PayKeyboard
> Android 仿支付宝支付密码输入控件开发
### 静态图图和动态图有点看不清分割线，但是实际运行是好的。
![](/screenshot/p1.png)
![](/screenshot/g1.gif)

### 封装成PayKeyboardView控件，调用代码如下

        
          PayKeyboardView.setTitle("仿支付宝密码输入");
          PayKeyboardView.setOnKeyboardListener(new OnKeyboardListener() {
            @Override
            public void onComplete(String one, String two, String three, String four, String five,
                String six) {
              Toast.makeText(getBaseContext(), "数据为：" + one + two + three + four + five + six,
                  Toast.LENGTH_LONG).show();
            }

            @Override
            public void onBack() {
              Toast.makeText(getBaseContext(), "触发返回按钮",
                  Toast.LENGTH_LONG).show();
            }
          });
