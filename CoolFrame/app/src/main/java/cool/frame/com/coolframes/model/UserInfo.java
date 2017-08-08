package cool.frame.com.coolframes.model;

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
    @Property(nameInDb = "sex")
    private String sex;
    @Property(nameInDb = "height")
    private int height;
    @Property(nameInDb = "goods")
    private String goods;
    @Property(nameInDb = "width")
    private String width;
    @Property(nameInDb ="number")
    private String number;
    @Property(nameInDb = "retur")
    private String retur;
    @Generated(hash = 1074454140)
    public UserInfo(String userName, int age, String sex, int height, String goods,
            String width, String number, String retur) {
        this.userName = userName;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.goods = goods;
        this.width = width;
        this.number = number;
        this.retur = retur;
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
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public String getGoods() {
        return this.goods;
    }
    public void setGoods(String goods) {
        this.goods = goods;
    }
    public String getWidth() {
        return this.width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getRetur() {
        return this.retur;
    }
    public void setRetur(String retur) {
        this.retur = retur;
    }
}
