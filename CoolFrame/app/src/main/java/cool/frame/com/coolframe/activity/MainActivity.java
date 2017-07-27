package cool.frame.com.coolframe.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cool.frame.com.coolframe.R;
import cool.frame.com.coolframe.adapter.MyNewsAdapter;
import cool.frame.com.coolframe.base.BaseListRefreshActivity;
import cool.frame.com.coolframe.model.JuHeOut;
import cool.frame.com.coolframe.presenter.IPresenter;
import cool.frame.com.coolframe.presenter.imp.PresenterImp;
import cool.frame.com.coolframe.view.GetFoodsView;
import cool.frame.com.coolframe.view.MClearEditText;
import cool.frame.com.library.adapter.adapter.MyBaseAdapter;
import cool.frame.com.library.adapter.recyclerview.HRecyclerView;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        iPresenter.requestData(rn, pn, searStr, MainActivity.this);
    }

    @Override
    public void getDatas(List<JuHeOut.Data> dataList) {
        resultList = dataList;
        relError.setVisibility(View.GONE);
        imgLists = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            imgLists.add(dataList.get(i).getAlbums().get(0));
        }
        Banner mBanner = (Banner) view.findViewById(R.id.banner);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(imgLists);
        mBanner.start();
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
}
