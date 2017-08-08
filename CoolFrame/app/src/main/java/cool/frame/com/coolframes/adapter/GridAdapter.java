package cool.frame.com.coolframes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cool.frame.com.coolframes.R;
import cool.frame.com.coolframes.model.Titls;

/**
 * Created by rankaifeng on 2017/8/8.
 */

public class GridAdapter extends BaseAdapter {
    List<Titls> mList;
    Context context;

    public GridAdapter(List<Titls> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View mView = LayoutInflater.from(context).inflate(R.layout.item_gridview, null);
        ImageView gridIcon = (ImageView) mView.findViewById(R.id.grid_icon);
        TextView gridTitle = (TextView) mView.findViewById(R.id.grid_title);

        gridIcon.setImageResource(mList.get(i).getIcon());
        gridTitle.setText(mList.get(i).getTitle());
        return mView;
    }
}
