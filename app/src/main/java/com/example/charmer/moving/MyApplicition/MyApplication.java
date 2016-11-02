package com.example.charmer.moving.MyApplicition;

import android.app.Application;
import android.content.Context;

import com.example.charmer.moving.BuildConfig;
import com.example.charmer.moving.pojo.User;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.BuildConfig;
import org.xutils.x;

import io.rong.imkit.RongIM;

/**
 * Created by Charmer on 2016/9/13.
 */
public class MyApplication extends Application{


    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        context = getApplicationContext();
        RongIM.init(this);
    }
    public static Context getContext(){return context;}
}


    private void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }
}