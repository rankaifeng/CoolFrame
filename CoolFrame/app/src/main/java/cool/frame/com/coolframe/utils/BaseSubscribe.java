package cool.frame.com.coolframe.utils;


import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by rankaifeng on 2017/8/2.
 */

public abstract class BaseSubscribe<T> implements Observer<T> {
    private Disposable disposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        success(t, disposable);
    }


    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public abstract void success(T t, Disposable disposable);
}
