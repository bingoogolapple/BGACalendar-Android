package cn.bingoogolapple.calendar.demo;

import android.app.Application;

import cn.bingoogolapple.calendar.demo.engine.Engine;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/11/15 上午10:51
 * 描述:
 */
public class App extends Application {
    private static App sInstance;
    private Engine mEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mEngine = new Retrofit.Builder()
                .baseUrl("http://7xk9dj.com1.z0.glb.clouddn.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
    }

    public static App getInstance() {
        return sInstance;
    }

    public Engine getEngine() {
        return mEngine;
    }

}