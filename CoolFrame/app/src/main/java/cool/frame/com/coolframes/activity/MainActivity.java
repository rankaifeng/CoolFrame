package cool.frame.com.coolframes.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cool.frame.com.coolframes.R;
import cool.frame.com.coolframes.adapter.GridAdapter;
import cool.frame.com.coolframes.adapter.MyNewsAdapter;
import cool.frame.com.coolframes.adapter.ViewPageAdapter;
import cool.frame.com.coolframes.api.GitJuHeApi;
import cool.frame.com.coolframes.api.RetrofitInit;
import cool.frame.com.coolframes.base.BaseListRefreshActivity;
import cool.frame.com.coolframes.model.EventMsg;
import cool.frame.com.coolframes.model.JuHeOut;
import cool.frame.com.coolframes.model.Titls;
import cool.frame.com.coolframes.model.UserInfoDao;
import cool.frame.com.coolframes.presenter.IPresenter;
import cool.frame.com.coolframes.presenter.imp.PresenterImp;
import cool.frame.com.coolframes.utils.ViewPagerListener;
import cool.frame.com.coolframes.view.GetFoodsView;
import cool.frame.com.coolframes.view.MClearEditText;
import cool.frame.com.coolframes.widget.Indicator;
import cool.frame.com.library.adapter.adapter.MyBaseAdapter;
import cool.frame.com.library.adapter.recyclerview.HRecyclerView;
import io.reactivex.Observable;
import retrofit2.Retrofit;

public class MainActivity extends BaseListRefreshActivity implements GetFoodsView {
    private static final String TAG = "MainActivity";
    private List<JuHeOut.Data> resultList = new ArrayList<>();
    IPresenter iPresenter;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.editTextSearch)
    MClearEditText editTextSearch;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.rel_error)
    RelativeLayout relError;
    Banner banner;
    ViewPager viewPager;
    List<String> imgLists;
    HRecyclerView recyclerView;
    boolean isHead = false;
    View view;
    List<String> titles;
    UserInfoDao userInfoDao;
    List<Titls> mTitlesOne = new ArrayList<>();
    List<Titls> mTitlesTwo = new ArrayList<>();
    List<View> mListView = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
//        requestHtpp();//请求网络数据的示例
        /*greendao操作数据库相关*/
//        MyOpenHelper devOpenHelper = new MyOpenHelper(getApplicationContext(), "User.db", null);
//        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
//        DaoSession daoSession = daoMaster.newSession();
//        userInfoDao = daoSession.getUserInfoDao();
//        UserInfo user = new UserInfo();
//        user.setUserName("李四");
//        user.setSex("男");
//        user.setAge(233);
//        user.setGoods("韩浩");
//        user.setWidth("13");
//        userInfoDao.insert(user);
        //selectDB();//请求数据库的示例


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent mIntent = new Intent(MainActivity.this, EvenBusActivity.class);
//                startActivity(mIntent);
                String searStr = editTextSearch.getText().toString().trim();
                type = "";
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(MainActivity.this.getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (searStr.equals("")) {
                    restoreDefault();
                    iPresenter.requestData(RN, PAGE_NUMBER, SEART_STR, MainActivity.this);
                } else {
                    SEART_STR = searStr;
                    RN = 10;
                    PAGE_NUMBER = 1;
                    iPresenter.requestData(RN, PAGE_NUMBER, searStr, MainActivity.this);
                }

            }
        });
        view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recl_head, null);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        banner = (Banner) view.findViewById(R.id.banner);
        recyclerView = getListView();

        initTitls();

    }

    private void initTitls() {
        String[] stringArray = getResources().getStringArray(R.array.name_titls);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.name_icon);
        for (int i = 0; i < stringArray.length; i++) {
            if (i < 8) {
                mTitlesOne.add(new Titls(typedArray.getResourceId(i, 0), stringArray[i]));
            } else {
                mTitlesTwo.add(new Titls(typedArray.getResourceId(i, 0), stringArray[i]));
            }
        }

        View viewOne = getLayoutInflater().inflate(R.layout.titl_item, null);
        GridView gridView = (GridView) viewOne.findViewById(R.id.grid_view);
        gridView.setAdapter(new GridAdapter(mTitlesOne, MainActivity.this));
        Indicator headIndicator = (Indicator) view.findViewById(R.id.home_head_indicator);

        View viewTwo = getLayoutInflater().inflate(R.layout.titl_item, null);
        GridView gridView1 = (GridView) viewTwo.findViewById(R.id.grid_view);
        gridView1.setAdapter(new GridAdapter(mTitlesTwo, MainActivity.this));

        mListView.add(viewOne);
        mListView.add(viewTwo);
        viewPager.setAdapter(new ViewPageAdapter(mListView));
        viewPager.addOnPageChangeListener(new ViewPagerListener(headIndicator));
        recyclerView.addHeaderView(view);
    }

    /**
     * 查询数据库
     */
    private void selectDB() {
        /*查询单个对象*/
//        Observable.create(new BaseObservable<UserInfo>() {
//            @Override
//            public void subscribes(ObservableEmitter<UserInfo> e) {
//                UserInfo userInfo = userInfoDao.queryBuilder()
//                        .where(UserInfoDao.Properties.UserName.eq("张三"))
//                        .build().unique();
//                e.onNext(userInfo);
//            }
//        }).subscribe(new BaseSubscribe<UserInfo>() {
//            @Override
//            public void success(UserInfo userInfo, Disposable disposable) {
//                UserInfo userInfo1 = userInfo;
//            }
//        });
        /*查询数据库的所有数据返回的是一个list*/
//        Observable.create(new BaseObservable<List<UserInfo>>() {
//            @Override
//            public void subscribes(ObservableEmitter<List<UserInfo>> e) {
//                List<UserInfo> list = userInfoDao.loadAll();
//                e.onNext(list);
//            }
//        }).subscribe(new BaseSubscribe<List<UserInfo>>() {
//            @Override
//            public void success(List<UserInfo> userInfos, Disposable disposable) {
//                List<UserInfo> list1 = new ArrayList<UserInfo>();
//                list1.addAll(userInfos);
//            }
//        });

    }

    /**
     * 请求网络数据
     */
    private void requestHtpp() {
        Retrofit retrofit = RetrofitInit.getInstance().initRetrofit();
        GitJuHeApi gitJuHeApi = retrofit.create(GitJuHeApi.class);
        Observable<JuHeOut> news = gitJuHeApi.getHttpNews("土豆", 10, 1);
//        RetrofitInit.getInstance().toSubscribe(this, news, new BaseSubscribe<JuHeOut>(this) {
//            @Override
//            public void success(JuHeOut user, Disposable disposable) {
//                Log.i(TAG, user + "");
//            }
//        });
//        news.subscribeOn(Schedulers.io())
//                .flatMap(new Function<JuHeOut, ObservableSource<JuHeOut.Data>>() {
//                    @Override
//                    public ObservableSource<JuHeOut.Data> apply(@NonNull JuHeOut juHeOut) throws Exception {
//                        List<JuHeOut.Data> list = juHeOut.getResult().getData();
//                        return Observable.fromIterable(list);
//                    }
//                }).filter(new Predicate<JuHeOut.Data>() {
//            @Override
//            public boolean test(@NonNull JuHeOut.Data data) throws Exception {
//                String id = data.getId();
//                if (Integer.parseInt(id) == 33) {
//                    return true;
//                }
//                return false;
//            }
//        }).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<JuHeOut.Data>() {
//                    @Override
//                    public void accept(@NonNull JuHeOut.Data data) throws Exception {
//                        resultList.add(data);
//                        Log.i("ddd", data.toString());
//                    }
//                });
//        news.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseSubscribe<JuHeOut>() {
//                    @Override
//                    public void success(JuHeOut juHeOut, Disposable disposable) {
//                        Log.i("------", juHeOut + "");
//                    }
//                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected MyBaseAdapter<JuHeOut.Data> getListAdapter() {
        return new MyNewsAdapter(this, R.layout.recy_item, resultList);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadServerData(int rn, int pn, String searStr, String type) {
        requestData(rn, pn, searStr, type);
    }


    public void requestData(int rn, int pn, String searStr, String type) {
        if (iPresenter == null) {
            iPresenter = new PresenterImp(this);
        }
        if (type.equals(DROP_REFRESH) || type.equals(PULL_UP_LOADING)) {
            iPresenter.requestData(rn, pn, searStr, null);
            return;
        }
        iPresenter.requestData(rn, pn, searStr, this);
    }

    @Override
    public void getDatas(List<JuHeOut.Data> dataList) {
        resultList = dataList;
        relError.setVisibility(View.GONE);
        imgLists = new ArrayList<>();
        titles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            imgLists.add(dataList.get(i).getAlbums().get(0));
            titles.add(dataList.get(i).getTitle());
        }
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImages(imgLists);
        banner.setBannerTitles(titles);
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(MainActivity.this, titles.get(position), Toast.LENGTH_LONG).show();
            }
        });
//        recyclerView.addHeaderView(view);
        setState(resultList, "");
    }

    @Override
    public void showError(String str) {
        mAdapter.clearData();
        relError.setVisibility(View.VISIBLE);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMsg event) {
        int content = event.getContent();
        Toast.makeText(MainActivity.this, content + "", Toast.LENGTH_LONG).show();
//        tvShow.setText(content + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
