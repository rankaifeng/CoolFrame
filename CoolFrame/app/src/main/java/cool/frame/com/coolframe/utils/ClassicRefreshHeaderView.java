package cool.frame.com.coolframe.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cool.frame.com.coolframe.R;
import cool.frame.com.library.adapter.recyclerview.RefreshTrigger;

/**
 * 下拉刷新
 */
public class ClassicRefreshHeaderView extends RelativeLayout implements RefreshTrigger {
    private ImageView ivArrow;

    private TextView tvRefresh;

    private ProgressBar progressBar;

    private Animation rotateUp;

    private Animation rotateDown;

    private boolean rotated = false;

    private int mHeight;

    public ClassicRefreshHeaderView(Context context) {
        this(context, null);
    }

    public ClassicRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassicRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.refresh_header_view, this);

        tvRefresh = (TextView) findViewById(R.id.tvRefresh);

        ivArrow = (ImageView) findViewById(R.id.ivArrow);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);

        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    public void onStart(boolean automatic, int headerHeight, int finalHeight) {
        this.mHeight = headerHeight;
        progressBar.setIndeterminate(false);
    }

    /**
     * @param isComplete
     * @param automatic
     * @param moved      手指向下滑的高度
     */
    @Override
    public void onMove(boolean isComplete, boolean automatic, int moved) {
        if (!isComplete) {
            ivArrow.setVisibility(VISIBLE);
            progressBar.setVisibility(GONE);
            if (moved <= mHeight) {
                if (rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateDown);
                    rotated = false;
                }
                tvRefresh.setText("下拉刷新");
            } else {
                tvRefresh.setText("释放刷新");
                if (!rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateUp);
                    rotated = true;
                }
            }
        }
    }

    @Override
    public void onRefresh() {
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText("正在刷新");
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        rotated = false;
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }

    @Override
    public void onReset() {
        rotated = false;
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }
}
