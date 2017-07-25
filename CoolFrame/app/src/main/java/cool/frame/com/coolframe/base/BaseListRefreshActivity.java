package cool.frame.com.coolframe.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import cool.frame.com.coolframe.LoadMoreFooterView;
import cool.frame.com.coolframe.R;
import cool.frame.com.coolframe.adapter.MyBaseAdapter;
import cool.frame.com.coolframe.model.JuHeOut;
import cool.frame.com.library.adapter.recyclerview.HRecyclerView;
import cool.frame.com.library.adapter.recyclerview.OnLoadMoreListener;
import cool.frame.com.library.adapter.recyclerview.OnRefreshListener;

public abstract class BaseListRefreshActivity<T> extends Activity implements OnRefreshListener, OnLoadMoreListener {
    public static final String REFRESH = "refreshNoLoading";
    public static final String LOADMORE = "load_more";
    private static final int MAX_SIZE = 4;
    private static final int RN = 10;
    private static int PAGE_NUMBER = 1;
    public String type;
    /**
     * List View 适配器
     */
    protected MyBaseAdapter<T> mAdapter;

    /**
     * 子类重写获取适配器
     */
    protected abstract MyBaseAdapter<JuHeOut> getListAdapter();

    HRecyclerView recyclerView;
    /*加载更多布局*/
    private LoadMoreFooterView mlLoadMoreFooterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter= (MyBaseAdapter<T>) getListAdapter();
        recyclerView = (HRecyclerView) findViewById(R.id.recy_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnLoadMoreListener(this);
        recyclerView.setOnRefreshListener(this);
        mlLoadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();
        loadServerData(RN, PAGE_NUMBER);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        if (mlLoadMoreFooterView.canLoadMore()) {
            mlLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
            PAGE_NUMBER++;
            type = LOADMORE;
            loadServerData(RN, PAGE_NUMBER);
        }
    }

    /**
     * 拿到listview,设置属性
     */
    public HRecyclerView getListView() {
        return recyclerView;
    }

    protected void loadServerData(int rn, int pn) {
        loadData(rn, pn);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 请求成功调用的方法
     */
    protected void setState(List<JuHeOut.Data> mDataSource, String message) {
        mAdapter.setData((List<T>) mDataSource);
        mAdapter.notifyDataSetChanged();
    }


    /**
     * 调用Service获取数据，
     * 需要在子类中重写
     */
    protected abstract void loadData(int rn, int pn);

}
