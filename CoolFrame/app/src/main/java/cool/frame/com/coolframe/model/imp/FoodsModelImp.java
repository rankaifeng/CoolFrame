package cool.frame.com.coolframe.model.imp;

import cool.frame.com.coolframe.api.GitJuHeApi;
import cool.frame.com.coolframe.model.FoodsModel;
import cool.frame.com.coolframe.model.JuHeOut;
import cool.frame.com.coolframe.utils.Config;
import cool.frame.com.coolframe.utils.GsonUtils;
import cool.frame.com.coolframe.view.OnJokeListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public class FoodsModelImp implements FoodsModel {

    @Override
    public void setData(int rn, int pn, final OnJokeListener pOnJokeListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitJuHeApi gitJuHeApi = retrofit.create(GitJuHeApi.class);
        Call<JuHeOut> news = gitJuHeApi.getNews("土豆", rn, pn);
        news.enqueue(new Callback<JuHeOut>() {
            @Override
            public void onResponse(Call<JuHeOut> call, final Response<JuHeOut> response) {
                JuHeOut out = (JuHeOut) GsonUtils.fromJsonToObject(GsonUtils.toJson(response.body()), JuHeOut.class);
                pOnJokeListener.onSuccess(out.getResult().getData());
            }


            @Override
            public void onFailure(Call<JuHeOut> call, Throwable t) {
                pOnJokeListener.onError();
            }
        });
    }
}
