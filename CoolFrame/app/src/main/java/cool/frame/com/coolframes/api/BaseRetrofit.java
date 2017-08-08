package cool.frame.com.coolframes.api;

import android.app.Activity;

import cool.frame.com.coolframes.base.BaseActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rankaifeng on 2017/7/26.
 */

public class BaseRetrofit<T> {

    public void sendRequest(Call<T> tCall, final Activity activity, final CallBackListenner<T> backListenner) {
        if (activity != null) {
            ((BaseActivity) activity).showDialog("加载中......",false);
        }
        tCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (activity != null) {
                    ((BaseActivity) activity).hideDialog();
                }
                backListenner.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (activity != null) {
                    ((BaseActivity) activity).hideDialog();
                }
                backListenner.onError();
            }
        });
    }


    public interface CallBackListenner<T> {
        void onSuccess(T result);

        void onError();
    }
}
