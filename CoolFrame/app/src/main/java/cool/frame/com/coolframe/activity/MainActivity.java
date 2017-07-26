package cool.frame.com.coolframe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cool.frame.com.coolframe.view.GetFoodsView;
import cool.frame.com.coolframe.presenter.IPresenter;
import cool.frame.com.coolframe.presenter.imp.PresenterImp;
import cool.frame.com.coolframe.R;
import cool.frame.com.coolframe.adapter.MyNewsAdapter;
import cool.frame.com.coolframe.base.BaseListRefreshActivity;
import cool.frame.com.coolframe.model.JuHeOut;
import cool.frame.com.library.adapter.adapter.MyBaseAdapter;

public class MainActivity extends BaseListRefreshActivity implements GetFoodsView {
    private List<JuHeOut.Data> resultList = new ArrayList<>();
    IPresenter iPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPresenter = new PresenterImp(this);
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
    protected void loadServerData(int rn, int pn) {
        requestData(rn, pn);
    }


    public void requestData(int rn, int pn) {
        iPresenter.requestData(rn, pn);
    }

    @Override
    public void getDatas(List<JuHeOut.Data> dataList) {
        resultList = dataList;
        setState(resultList, "");
    }
}
