package cool.frame.com.library.adapter.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cool.frame.com.library.adapter.holder.BaseViewHolder;

/**
 * Created by rankaifeng on 2017/7/24.
 */

public abstract class MyBaseAdapter<T> extends CommonAdapter {
    List<T> datas;

    public MyBaseAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
        this.datas = datas;
    }

    public void setData(List<T> data) {
        datas.addAll(data);
        notifyItemInserted(datas.size() + 1);
    }

    public void clearAndAddData(List<T> data) {
        if (datas.size() > 0) {
            datas.clear();
        }
        datas.addAll(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        datas.clear();
        notifyDataSetChanged();
    }

    public void convert(BaseViewHolder holder, int position) {
        showHnadDatas(holder, position);
    }

    public List<T> getData() {
        return datas == null ? (datas = new ArrayList<T>()) : datas;
    }

    public void addData(List<T> data) {
        if (datas != null && data != null && !data.isEmpty()) {
            datas.addAll(data);
        }
        notifyDataSetChanged();
    }

    public abstract void showHnadDatas(BaseViewHolder holder, int position);
}
