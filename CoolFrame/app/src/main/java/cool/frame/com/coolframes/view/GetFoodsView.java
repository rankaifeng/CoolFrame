package cool.frame.com.coolframes.view;

import java.util.List;

import cool.frame.com.coolframes.model.JuHeOut;

/**
 * Created by rankaifeng on 2017/7/25.
 */

public interface GetFoodsView {
    void getDatas(List<JuHeOut.Data> dataList);

    void showError(String str);
}
