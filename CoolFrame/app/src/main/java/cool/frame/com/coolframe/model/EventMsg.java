package cool.frame.com.coolframe.model;

/**
 * Created by rankaifeng on 2017/7/28.
 */

public class EventMsg {
    private String msg;
    private int content;
    public EventMsg(String msg) {
        this.msg = msg;
    }

    public EventMsg(int content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
