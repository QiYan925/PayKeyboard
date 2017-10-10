package cn.com.epsoft.keyboard.widget.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.epsoft.keyboard.R;

/**
 * @author 启研
 * @created at 2017/9/28 17:15
 */

public class KeyboardAdapter extends Adapter {

  private static final int VIEW_TYPE_TEXT = 0;
  private static final int VIEW_TYPE_ICON = 1;
  Context context;

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    context = parent.getContext();
    View view = LayoutInflater.from(context)
        .inflate(
            viewType == VIEW_TYPE_TEXT ? R.layout.keyboard_item_text : R.layout.keyboard_item_icon,
            parent, false);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    int type = getItemViewType(position);
    if (type == VIEW_TYPE_TEXT) {
      final String text = String.valueOf(objs[position]);
      boolean isEmpty = TextUtils.isEmpty(text);
      ;
      holder.itemView.setBackground(
          isEmpty ? new ColorDrawable(Color.parseColor("#e2e9ee"))
              : ContextCompat.getDrawable(context, R.drawable.keyboard_select_white_click));
      ((TextView) holder.itemView).setText(text);
      if (!isEmpty) {
        holder.itemView.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
            if (listener != null) {
              listener.onAddText(text);
            }
          }
        });
      }
    } else if (type == VIEW_TYPE_ICON) {
      ((ImageView) holder.itemView).setImageResource(Integer.valueOf(objs[position].toString()));
      holder.itemView.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          listener.onDeleteOne();
        }
      });
    }
  }

  Object[] objs;

  public void setItems(Object[] objs) {
    this.objs = objs;
  }

  @Override
  public int getItemViewType(int position) {
    if (objs[position] instanceof String) {
      return VIEW_TYPE_TEXT;
    } else if (objs[position] instanceof Integer) {
      return VIEW_TYPE_ICON;
    }
    return -1;
  }

  @Override
  public int getItemCount() {
    if (objs == null) {
      return 0;
    }
    return objs.length;
  }

  OnItemClickListener listener;

  public void setOnItemClickListener(OnItemClickListener l) {
    this.listener = l;
  }

  public interface OnItemClickListener {

    void onAddText(String text);

    void onDeleteOne();
  }

  public class Holder extends ViewHolder {

    public Holder(View itemView) {
      super(itemView);
    }
  }
}
