package cool.frame.com.coolframe.utils;

import android.widget.Toast;

import cool.frame.com.coolframe.MyApplication;

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
