package com.example.yang.musicplayer;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangCihang
 * @since 17/7/12.
 * email yangcihang@hrsoft.net
 */

public class MusicPlayerApplication extends Application {
    /** Application 实例 */
    private static MusicPlayerApplication instance;
    /** Activity栈 */
    private static List<Activity> activityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    /**
     * 获取Application实例
     *
     * @return Application
     */
    public static MusicPlayerApplication getInstance() {
        return instance;
    }


    /**
     * activity生命周期回调方法
     */
    private ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            removeActivity(activity);
        }
    };

    /**
     * 添加Activity
     *
     * @param activity activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 移除Activity
     *
     * @param activity activity
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 清除所有Activity
     */
    public void clearAllActivity() {
        for (Activity activity : activityList) {
            if (activity != null && !activity.isFinishing())
                activity.finish();
        }
    }

    public void exitApp() {
        clearAllActivity();
    }
}
