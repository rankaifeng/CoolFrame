package cool.frame.com.coolframe.model.imp;

import cool.frame.com.coolframe.api.BaseRetrofit;
import cool.frame.com.coolframe.api.GitJuHeApi;
import cool.frame.com.coolframe.api.RetrofitInit;
import cool.frame.com.coolframe.model.FoodsModel;
import cool.frame.com.coolframe.model.JuHeOut;
import cool.frame.com.coolframe.view.OnJokeListener;
import retrofit2.Call;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public class FoodsModelImp extends BaseRetrofit implements FoodsModel {

    @Override
    public void setData(int rn, int pn, String searStr, final OnJokeListener pOnJokeListener) {
        GitJuHeApi gitJuHeApi = RetrofitInit.getInstance().initRetrofit().create(GitJuHeApi.class);
        Call<JuHeOut> news = gitJuHeApi.getNews(searStr, rn, pn);
        super.sendRequest(news, new CallBackListenner<JuHeOut>() {
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
