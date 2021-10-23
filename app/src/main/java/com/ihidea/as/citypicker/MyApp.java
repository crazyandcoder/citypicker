package com.ihidea.as.citypicker;
import android.app.Application;
import com.lljjcoder.style.citylist.utils.CityListLoader;

/**
 * 作者：liji on 2017/12/15 10:55
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 预先加载一级列表所有城市的数据
         */
        CityListLoader.getInstance().loadCityData(this);

        /**
         * 预先加载三级列表显示省市区的数据
         */
        CityListLoader.getInstance().loadProData(this);
    }
}
