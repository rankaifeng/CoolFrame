package cool.frame.com.coolframe.view;

import java.util.List;

import cool.frame.com.coolframe.model.JuHeOut;

public interface OnJokeListener {

    /**
     * 成功的时候回调
     */
    void onSuccess(List<JuHeOut.Data> out);

    /**
     * 失败的时候回调
     */
    void onError(String error);
}
