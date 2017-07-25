package cool.frame.com.coolframe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import cool.frame.com.coolframe.adapter.MyNewsAdapter;
import cool.frame.com.coolframe.api.GitJuHeApi;
import cool.frame.com.coolframe.model.JuHeOut;
import cool.frame.com.coolframe.utils.Config;
import cool.frame.com.coolframe.utils.GsonUtils;
import cool.frame.com.library.adapter.recyclerview.HRecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    private List<JuHeOut.Data> resultList = new ArrayList<>();
    HRecyclerView recyclerView;

    MyNewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (HRecyclerView) findViewById(R.id.recy_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyNewsAdapter(this, R.layout.recy_item, resultList);
        recyclerView.setAdapter(mAdapter);
        loadDatas(10, 1);
    }


    public void loadDatas(int rn, int pn) {
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
                resultList.addAll(out.getResult().getData());
                mAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<JuHeOut> call, Throwable t) {
                System.out.print(call);
            }
        });
    }
}
