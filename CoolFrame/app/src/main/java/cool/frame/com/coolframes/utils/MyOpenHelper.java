package cool.frame.com.coolframes.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import cool.frame.com.coolframes.model.DaoMaster;
import cool.frame.com.coolframes.model.UserInfoDao;

/**
 * Created by rankaifeng on 2017/8/4.
 */

public class MyOpenHelper extends DaoMaster.DevOpenHelper {
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        switch (oldVersion) {
            case 6: //不能先删除表，否则数据都木了//
                UserInfoDao.createTable(db, true);
                // 加入新字段
                try {
                    db.execSQL("ALTER TABLE 'USER_INFO' ADD 'retur' TEXT;");
                } catch (Exception e) {
                    Log.i("ddddddd", e.toString());
                }

                break;
        }
    }
}
