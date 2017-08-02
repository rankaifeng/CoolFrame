package cool.frame.com.coolframe.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
import cool.frame.com.coolframe.R;
import cool.frame.com.coolframe.adapter.MyNewsAdapter;
import cool.frame.com.coolframe.api.GitJuHeApi;
import cool.frame.com.coolframe.api.RetrofitInit;
import cool.frame.com.coolframe.base.BaseListRefreshActivity;
import cool.frame.com.coolframe.model.DaoMaster;
import cool.frame.com.coolframe.model.DaoSession;
import cool.frame.com.coolframe.model.EventMsg;
import cool.frame.com.coolframe.model.JuHeOut;
import cool.frame.com.coolframe.model.UserInfo;
import cool.frame.com.coolframe.model.UserInfoDao;
import cool.frame.com.coolframe.presenter.IPresenter;
import cool.frame.com.coolframe.presenter.imp.PresenterImp;
import cool.frame.com.coolframe.utils.BaseSubscribe;
import cool.frame.com.coolframe.view.GetFoodsView;
import cool.frame.com.coolframe.view.MClearEditText;
import cool.frame.com.library.adapter.adapter.MyBaseAdapter;
import cool.frame.com.library.adapter.recyclerview.HRecyclerView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseListRefreshActivity implements GetFoodsView {
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
    List<String> imgLists;
    HRecyclerView recyclerView;
    boolean isHead = false;
    View view;
    List<String> titles;
    UserInfoDao userInfoDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        //requestHtpp();//请求网络数据的示例
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "User.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userInfoDao = daoSession.getUserInfoDao();
        UserInfo user = new UserInfo();
        user.setUserName("李四");
        user.setAge(233);
        userInfoDao.insert(user);

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
        recyclerView = getListView();

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
        GitJuHeApi gitJuHeApi = RetrofitInit.getInstance().initRetrofit().create(GitJuHeApi.class);
        Observable<JuHeOut> news = gitJuHeApi.getHttpNews("土豆", 10, 1);
        RetrofitInit.getInstance().toSubscribe(this, news, new BaseSubscribe<JuHeOut>(this) {
            @Override
            public void success(JuHeOut juHeOut, Disposable disposable) {
                Log.i("------", juHeOut + "");
            }
        });

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
        Banner mBanner = (Banner) view.findViewById(R.id.banner);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setImages(imgLists);
        mBanner.setBannerTitles(titles);
        mBanner.start();

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(MainActivity.this, titles.get(position), Toast.LENGTH_LONG).show();
            }
        });
        if (!isHead) {
            recyclerView.addHeaderView(view);
            isHead = true;
        }
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
