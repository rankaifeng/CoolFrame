package cool.frame.com.coolframe.api;

import android.app.Activity;

import java.util.concurrent.TimeUnit;

import cool.frame.com.coolframe.base.BaseActivity;
import cool.frame.com.coolframe.utils.Config;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

    public <T> void toSubscribe(Activity activity, Observable<T> observable, Observer<T> observer) {
        if (null != activity) {
            ((BaseActivity) activity).showDialog("请求中......", true);
        }
        observable.subscribeOn(Schedulers.io())    // 指定subscribe()发生在IO线程
                .observeOn(AndroidSchedulers.mainThread())  // 指定Subscriber的回调发生在io线程
                .timeout(Config.DEFALT_TIME, TimeUnit.SECONDS)    //重连间隔时间
                .subscribe(observer);   //订阅
    }
}
