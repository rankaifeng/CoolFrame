package cool.frame.com.coolframe.base;

/**
 * Created by rankaifeng on 2017/8/2.
 */

public class BaseResponse<T> {
    private String reason;
    private T result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
