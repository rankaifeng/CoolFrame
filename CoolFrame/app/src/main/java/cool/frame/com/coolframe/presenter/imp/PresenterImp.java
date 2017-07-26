package cool.frame.com.coolframe.presenter.imp;

import java.util.List;

import cool.frame.com.coolframe.view.GetFoodsView;
import cool.frame.com.coolframe.model.FoodsModel;
import cool.frame.com.coolframe.model.imp.FoodsModelImp;
import cool.frame.com.coolframe.view.OnJokeListener;
import cool.frame.com.coolframe.model.JuHeOut;
import cool.frame.com.coolframe.presenter.IPresenter;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public class PresenterImp implements IPresenter, OnJokeListener {
    private GetFoodsView view;
    private FoodsModel model;

    public PresenterImp(GetFoodsView view) {
        this.view = view;
        model = new FoodsModelImp();
    }

    @Override
    public void onSuccess(List<JuHeOut.Data> out) {
        view.getDatas(out);
    }

    @Override
    public void onError(String str) {
        view.showError(str);
    }

    @Override
    public void requestData(int rn, int pn, String searStr) {
        model.setData(rn, pn, searStr, this);
    }
}
