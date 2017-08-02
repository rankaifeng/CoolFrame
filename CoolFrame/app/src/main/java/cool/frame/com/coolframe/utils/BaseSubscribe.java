package cool.frame.com.coolframe.utils;


import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import cool.frame.com.coolframe.base.BaseActivity;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by rankaifeng on 2017/8/2.
 */

public abstract class BaseSubscribe<T> implements Observer<T> {
    private Disposable disposable;
    private Activity activity;

    public BaseSubscribe(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    /**
     * 请求成功处理
     *
     * @param t
     */
    @Override
    public void onNext(@NonNull T t) {
        if (activity != null) {
            ((BaseActivity) activity).hideDialog();
        }
        if (null != t) {
            TostUtils.showTost("请求成功");
            success(t, disposable);
        } else {
            TostUtils.showTost("暂无数据显示！");
        }
    }

    /**
     * 请求失败处理
     *
     * @param t
     */
    @Override
    public void onError(@NonNull Throwable t) {
        ((BaseActivity) activity).hideDialog();
        StringBuffer sb = new StringBuffer();
        sb.append("请求失败：");
        if (t instanceof NetworkErrorException || t instanceof UnknownHostException || t instanceof ConnectException) {
            sb.append("网络异常");
        } else if (t instanceof SocketTimeoutException || t instanceof InterruptedIOException || t instanceof TimeoutException) {
            sb.append("请求超时");
        } else if (t instanceof JsonSyntaxException) {
            sb.append("请求不合法");
        } else if (t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof ParseException) {   //  解析错误
            sb.append("解析错误");
        } else {
            TostUtils.showTost(t.getMessage());
            return;
        }
        TostUtils.showTost(sb.toString());
    }

    @Override
    public void onComplete() {

    }

    public abstract void success(T t, Disposable disposable);
}
