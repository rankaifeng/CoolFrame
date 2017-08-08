package cool.frame.com.coolframes.presenter;

import android.app.Activity;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public interface IPresenter {
    void requestData(int rn, int pn, String searStr, Activity activity);
}
