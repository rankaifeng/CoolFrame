package cool.frame.com.coolframe.base;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import butterknife.ButterKnife;
import cool.frame.com.coolframe.utils.MyLoadDialog;
import cool.frame.com.coolframe.utils.StatusBarCompat;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public abstract class BaseActivity extends AppCompatActivity {
    Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(setLayoutId());
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }
        //第二个参数是想要设置的颜色
        StatusBarCompat.compat(this, Color.parseColor("#54aee6"));
        ButterKnife.bind(this);
        initView();
    }

    public abstract int setLayoutId();//获取布局文件

    public abstract void initView(); //初始化


    public void showDialog(String msg, boolean isOutSide) {
        dialog = MyLoadDialog.getInstance().createDialog(BaseActivity.this, msg, isOutSide);
    }

    public void hideDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
