package cool.frame.com.coolframes;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        context = getApplicationContext();
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }
}
