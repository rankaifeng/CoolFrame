package cool.frame.com.coolframe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import cool.frame.com.coolframe.R;
import cool.frame.com.coolframe.model.EventMsg;

/**
 * Created by rankaifeng on 2017/7/28.
 */

public class EvenBusActivity extends Activity {
    private Button btnEvent;
    private TextView tvShow;
    private static final int TIME = 5000;
    private int content = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evenbus_layout);
//        EventBus.getDefault().register(this);
        btnEvent = (Button) findViewById(R.id.btn_event);
        tvShow = (TextView) findViewById(R.id.tv_show);
        mHandler.postDelayed(mRunnable, TIME);
        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EventBus.getDefault().post(new EventMsg("我是发送的消息"));
            }
        });
    }



    Handler mHandler = new Handler();

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            content++;
            mHandler.postDelayed(this, TIME);
            EventBus.getDefault().post(new EventMsg(content));
        }
    };
}
