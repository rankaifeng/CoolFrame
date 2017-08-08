package cool.frame.com.coolframes.utils;

import android.widget.Toast;

import cool.frame.com.coolframes.MyApplication;

/**
 * Created by rankaifeng on 2017/8/2.
 */

public class TostUtils {
    private static Toast toast;

    public static void showTost(String msg) {
        toast = Toast.makeText(MyApplication.context, msg, Toast.LENGTH_LONG);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
