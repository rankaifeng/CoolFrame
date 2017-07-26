package cool.frame.com.coolframe.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cool.frame.com.coolframe.R;
import cool.frame.com.coolframe.adapter.MyNewsAdapter;
import cool.frame.com.coolframe.base.BaseListRefreshActivity;
import cool.frame.com.coolframe.model.JuHeOut;
import cool.frame.com.coolframe.presenter.IPresenter;
import cool.frame.com.coolframe.presenter.imp.PresenterImp;
import cool.frame.com.coolframe.view.GetFoodsView;
import cool.frame.com.coolframe.view.MClearEditText;
import cool.frame.com.library.adapter.adapter.MyBaseAdapter;

public class MainActivity extends BaseListRefreshActivity implements GetFoodsView {
    private List<JuHeOut.Data> resultList = new ArrayList<>();
    IPresenter iPresenter;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.editTextSearch)
    MClearEditText editTextSearch;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.rel_error)
    RelativeLayout relError;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPresenter = new PresenterImp(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searStr = editTextSearch.getText().toString().trim();
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(MainActivity.this.getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (searStr.equals("")) {
                    restoreDefault();
                    iPresenter.requestData(RN, PAGE_NUMBER, SEART_STR);
                } else {
                    SEART_STR = searStr;
                    iPresenter.requestData(RN, PAGE_NUMBER, searStr);
                }

            }
        });
    }

    @Override
    protected MyBaseAdapter<JuHeOut.Data> getListAdapter() {
        return new MyNewsAdapter(this, R.layout.recy_item, resultList);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadServerData(int rn, int pn, String searStr) {
        requestData(rn, pn, searStr);
    }


    public void requestData(int rn, int pn, String searStr) {
        iPresenter.requestData(rn, pn, searStr);
    }

    @Override
    public void getDatas(List<JuHeOut.Data> dataList) {
        resultList = dataList;
        relError.setVisibility(View.GONE);
        setState(resultList, "");
    }

    @Override
    public void showError(String str) {
        mAdapter.clearData();
        relError.setVisibility(View.VISIBLE);
    }
}
