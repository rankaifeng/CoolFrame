package cool.frame.com.coolframe.activity;

import java.util.ArrayList;
import java.util.List;

import cool.frame.com.coolframe.R;
import cool.frame.com.coolframe.adapter.MyNewsAdapter;
import cool.frame.com.coolframe.api.GitJuHeApi;
import cool.frame.com.coolframe.base.BaseListRefreshActivity;
import cool.frame.com.coolframe.model.JuHeOut;
import cool.frame.com.coolframe.utils.Config;
import cool.frame.com.coolframe.utils.GsonUtils;
import cool.frame.com.library.adapter.adapter.MyBaseAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseListRefreshActivity {
    private List<JuHeOut.Data> resultList = new ArrayList<>();

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitJuHeApi gitJuHeApi = retrofit.create(GitJuHeApi.class);
        Call<JuHeOut> news = gitJuHeApi.getNews("西红柿", rn, pn);
        news.enqueue(new Callback<JuHeOut>() {
            @Override
            public void onResponse(Call<JuHeOut> call, final Response<JuHeOut> response) {
                JuHeOut out = (JuHeOut) GsonUtils.fromJsonToObject(GsonUtils.toJson(response.body()), JuHeOut.class);
                resultList = out.getResult().getData();
                setState(resultList, "");
            }


            @Override
            public void onFailure(Call<JuHeOut> call, Throwable t) {
                System.out.print(call);
            }
        });
    }
}
