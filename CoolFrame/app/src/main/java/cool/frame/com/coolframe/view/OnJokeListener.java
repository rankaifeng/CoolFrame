package cool.frame.com.coolframe.view;

import java.util.List;

import cool.frame.com.coolframe.model.JuHeOut;

/**
 * @author wangjing
 * @version 1.0
 *          <p><strong>Features draft description.主要功能介绍</strong></p>
 * @since 2016/5/20 17:03
 */
public interface OnJokeListener {

    /**
     * 成功的时候回调
     */
    void onSuccess(List<JuHeOut.Data> out);

    /**
     * 失败的时候回调
     */
    void onError();
}
