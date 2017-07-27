package cool.frame.com.coolframe.api;

import java.util.concurrent.TimeUnit;

import cool.frame.com.coolframe.utils.Config;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rankaifeng on 2017/7/26.
 */

public class RetrofitInit {
    public static RetrofitInit mRetrofitInit;

    public static RetrofitInit getInstance() {
        if (mRetrofitInit == null) {
            mRetrofitInit = new RetrofitInit();
        }
        return mRetrofitInit;
    }

    public Retrofit initRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Config.DEFALT_TIME, TimeUnit.SECONDS)
                .writeTimeout(Config.DEFALT_TIME, TimeUnit.SECONDS)
                .readTimeout(Config.DEFALT_TIME, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
