package cool.frame.com.coolframes.utils;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by rankaifeng on 2017/8/2.
 */

public abstract class BaseObservable<T> implements ObservableOnSubscribe<T> {
    @Override
    public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
        subscribes(e);
    }

    public abstract void subscribes(ObservableEmitter<T> e);
}
