package cool.frame.com.coolframes.base;

import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import butterknife.BindView;
import cool.frame.com.coolframes.R;
import cool.frame.com.coolframes.utils.Config;
import cool.frame.com.coolframes.utils.LoadMoreFooterView;
import cool.frame.com.library.adapter.adapter.MyBaseAdapter;
import cool.frame.com.library.adapter.recyclerview.HRecyclerView;
import cool.frame.com.library.adapter.recyclerview.OnLoadMoreListener;
import cool.frame.com.library.adapter.recyclerview.OnRefreshListener;
import cool.frame.com.library.adapter.recyclerview.SpaceItemDecoration;

/**
 * 集成下拉刷新上拉加载更多
 *
 * @param <T>
 */
public abstract class BaseListRefreshActivity<T> extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    public static final String DROP_REFRESH = "drop_refresh ";//下拉刷新标识
    public static final String PULL_UP_LOADING = "up_loading";//上拉加载更多标识
    public static String SEART_STR = Config.SEAR_STR;
    public static int RN = Config.RN;//一页显示多少条
    public static int PAGE_NUMBER = Config.PAGE_NUMBER;//页数
    public String type = "";
    /**
     * List View 适配器
     */
    protected MyBaseAdapter<T> mAdapter;

    /**
     * 子类重写获取适配器
     */
    protected abstract MyBaseAdapter<T> getListAdapter();

    @BindView(R.id.recy_view)
    HRecyclerView recyclerView;
    /*加载更多布局*/
    private LoadMoreFooterView mlLoadMoreFooterView;

    @Override
    public void initView() {
        mAdapter = getListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpaceItemDecoration(15));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnLoadMoreListener(this);
        recyclerView.setOnRefreshListener(this);
        mlLoadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();
        loadServerData(RN, PAGE_NUMBER, SEART_STR, type);
    }


    @Override
    public void onRefresh() {
        PAGE_NUMBER = 1;
        type = DROP_REFRESH;
        loadServerData(RN, PAGE_NUMBER, SEART_STR, type);
    }

    @Override
    public void onLoadMore() {
        if (mlLoadMoreFooterView.canLoadMore()) {
            mlLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
            PAGE_NUMBER++;
            type = PULL_UP_LOADING;
            loadServerData(RN, PAGE_NUMBER, SEART_STR, type);
        }
    }

    /**
     * 拿到listview,设置属性
     */
    public HRecyclerView getListView() {
        return recyclerView;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 请求成功调用的方法
     */
    protected void setState(List<T> mDataSource, String message) {
        if (type.equals(DROP_REFRESH)) {
            mAdapter.clearAndAddData(mDataSource);
            recyclerView.setRefreshing(false);
            mAdapter.notifyDataSetChanged();
        } else if (type.equals(PULL_UP_LOADING)) {
            if (mDataSource.size() == 0) {
                //没有更多数据
                mlLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
            } else {
                mlLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                mAdapter.setData(mDataSource);
            }
        } else {
            mAdapter.clearAndAddData(mDataSource);
        }
    }


    /**
     * 调用Service获取数据，
     * 需要在子类中重写
     */
    protected abstract void loadServerData(int rn, int pn, String seartStr, String type);

    /***
     * 恢复默认设置的值
     */
    public void restoreDefault() {
        RN = Config.RN;
        PAGE_NUMBER = Config.PAGE_NUMBER;
        SEART_STR = Config.SEAR_STR;
    }
}
