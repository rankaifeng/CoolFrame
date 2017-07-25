package cool.frame.com.coolframe;

import android.app.Application;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }
}
