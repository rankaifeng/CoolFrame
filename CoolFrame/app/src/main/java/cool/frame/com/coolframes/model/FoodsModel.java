package cool.frame.com.coolframes.model;

import android.app.Activity;

import cool.frame.com.coolframes.view.OnJokeListener;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public interface FoodsModel {


    void setData(int rn, int pn, String searStr, OnJokeListener pOnJokeListener, Activity activity);

}
