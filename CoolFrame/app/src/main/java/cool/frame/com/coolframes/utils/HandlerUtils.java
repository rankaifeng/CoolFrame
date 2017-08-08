package cool.frame.com.coolframes.utils;

import android.os.Handler;
import android.os.Message;

/**
 * Created by rankaifeng on 2017/7/24.
 */

public class HandlerUtils extends Handler {
    Handler handler;

    public HandlerUtils(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Message mMessage = new Message();
        if (mMessage != null) {
            handler.sendMessage(mMessage);
        }
    }
}
