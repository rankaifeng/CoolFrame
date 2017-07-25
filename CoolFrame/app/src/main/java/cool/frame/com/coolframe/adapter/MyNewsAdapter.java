package cool.frame.com.coolframe.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import cool.frame.com.coolframe.R;
import cool.frame.com.coolframe.model.JuHeOut;
import cool.frame.com.library.adapter.holder.BaseViewHolder;

/**
 * Created by rankaifeng on 2017/7/24.
 */

public class MyNewsAdapter extends MyBaseAdapter<JuHeOut> {
    private List<JuHeOut.Data> resultList;
    private Context context;

    public MyNewsAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
        this.resultList = datas;
        this.context = context;
    }

    @Override
    public void showHnadDatas(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_title, resultList.get(position).getTitle());
        ImageView imgView = holder.getView(R.id.img_top);
        Glide.with(context)
                .load(resultList.get(position).getAlbums().get(0))
                .into(imgView);
    }
}