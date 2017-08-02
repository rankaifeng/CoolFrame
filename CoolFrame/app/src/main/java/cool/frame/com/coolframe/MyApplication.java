package cool.frame.com.coolframe;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }
}
