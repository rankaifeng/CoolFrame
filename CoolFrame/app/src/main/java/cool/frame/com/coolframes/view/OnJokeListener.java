package cool.frame.com.coolframes.view;

import java.util.List;

import cool.frame.com.coolframes.model.JuHeOut;

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
