package cool.frame.com.coolframe.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rankaifeng on 2017/7/26.
 */

public class BaseRetrofit<T> {

    public void sendRequest(Call<T> tCall, final CallBackListenner<T> backListenner) {
        tCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                backListenner.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                backListenner.onError();
            }
        });
    }


    public interface CallBackListenner<T> {
        void onSuccess(T result);

        void onError();
    }
}
