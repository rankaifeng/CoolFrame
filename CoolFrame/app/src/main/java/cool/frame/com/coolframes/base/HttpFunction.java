package cool.frame.com.coolframes.base;

import cool.frame.com.coolframes.utils.TostUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by rankaifeng on 2017/8/2.
 */

public class HttpFunction<T> implements Function<BaseResponse<T>, T> {
    @Override
    public T apply(@NonNull BaseResponse<T> response) throws Exception {
        if (!response.getReason().equals("Success")) {
            TostUtils.showTost("请求失败");
        }
        return response.getResult();
    }
}
