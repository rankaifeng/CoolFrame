package cool.frame.com.coolframe.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by rankaifeng on 2017/8/1.
 */
@Entity
public class UserInfo {
    @Property(nameInDb = "user_name")
    private String userName;
    @Property(nameInDb = "age")
    private int age;
    @Generated(hash = 1807657987)
    public UserInfo(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
