package cool.frame.com.coolframes.presenter.imp;

import android.app.Activity;

import java.util.List;

import cool.frame.com.coolframes.model.FoodsModel;
import cool.frame.com.coolframes.model.JuHeOut;
import cool.frame.com.coolframes.model.imp.FoodsModelImp;
import cool.frame.com.coolframes.presenter.IPresenter;
import cool.frame.com.coolframes.view.GetFoodsView;
import cool.frame.com.coolframes.view.OnJokeListener;

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
    public void requestData(int rn, int pn, String searStr, Activity activity) {
        model.setData(rn, pn, searStr, this, activity);
    }
}
