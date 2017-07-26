package cool.frame.com.coolframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import cool.frame.com.coolframe.R;
import cool.frame.com.coolframe.utils.StatusBarUtil;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        initView();
        initStatus();
    }

    public abstract int setLayoutId();//获取布局文件

    public abstract void initView(); //初始化

    private void initStatus() {
        //设置状态栏的颜色
        StatusBarUtil.setColor(this, getResources().getColor(R.color.main_color), 60);
    }
}
