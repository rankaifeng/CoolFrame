package cool.frame.com.coolframes.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cool.frame.com.coolframes.R;

/**
 * Created by rankaifeng on 2017/7/27.
 */

public class MyLoadDialog {
    private static MyLoadDialog myLoadDialog;
    private static Dialog mdDialog;

    public static MyLoadDialog getInstance() {
        if (myLoadDialog == null) {
            myLoadDialog = new MyLoadDialog();
        }
        return myLoadDialog;
    }

    public Dialog createDialog(Context context, String msg, boolean isOutside) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_dialog_load, null);//得到布局

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView mImageView = (ImageView) view.findViewById(R.id.img);
        TextView mTextView = (TextView) view.findViewById(R.id.tipTextView);// 提示文字
        // 加载动画

        Animation mAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        mImageView.startAnimation(mAnimation);
        mTextView.setText(msg);

        mdDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        mdDialog.setCancelable(false);// 不可以用“返回键”取消
        mdDialog.setCanceledOnTouchOutside(isOutside);//false 不可取消
        mdDialog.show();
        mdDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
        return mdDialog;
    }
}
