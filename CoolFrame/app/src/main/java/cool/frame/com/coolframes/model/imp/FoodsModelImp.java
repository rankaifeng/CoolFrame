package cool.frame.com.coolframes.model.imp;

import android.app.Activity;

import cool.frame.com.coolframes.api.BaseRetrofit;
import cool.frame.com.coolframes.api.GitJuHeApi;
import cool.frame.com.coolframes.api.RetrofitInit;
import cool.frame.com.coolframes.model.FoodsModel;
import cool.frame.com.coolframes.model.JuHeOut;
import cool.frame.com.coolframes.view.OnJokeListener;
import retrofit2.Call;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public class FoodsModelImp extends BaseRetrofit implements FoodsModel {

    @Override
    public void setData(int rn, int pn, String searStr, final OnJokeListener pOnJokeListener, Activity activity) {
        GitJuHeApi gitJuHeApi = RetrofitInit.getInstance().initRetrofit().create(GitJuHeApi.class);
        Call<JuHeOut> news = gitJuHeApi.getNews(searStr, rn, pn);
        super.sendRequest(news, activity, new CallBackListenner<JuHeOut>() {
            @Override
            public void onSuccess(JuHeOut result) {
                if (result.getResult() != null) {
                    pOnJokeListener.onSuccess(result.getResult().getData());
                } else {
                    pOnJokeListener.onError("暂无数据");
                }
            }

            @Override
            public void onError() {

            }
        });
    }
}
