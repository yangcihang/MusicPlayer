package com.example.yang.musicplayer.base.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.yang.musicplayer.base.fragment.BaseFragment;
import com.example.yang.musicplayer.constant.Constant;

import butterknife.ButterKnife;

/**
 * @author YangCihang
 * @since 17/7/12.
 * email yangcihang@hrsoft.net
 */

public abstract class BaseActivity extends AppCompatActivity implements Constant{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 禁止APP横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 页面初始化操作.
     */
    protected void init() {
        ButterKnife.bind(this);
        initVariable();
        initView();
        loadData();
    }

    /**
     * 获取LayoutId.
     *
     * @return LayoutId 布局文件Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化变量.
     */
    protected abstract void initVariable();

    /**
     * 初始化View的状态，挂载各种监听事件.
     */
    protected abstract void initView();

    /**
     * 初始化加载页面数据.
     */
    protected abstract void loadData();

    /**
     * 添加Fragment
     *
     * @param fragment fragment
     * @param bundle   传递的数据
     */
    protected void addFragment(@IdRes int containerViewId, BaseFragment fragment, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.add(containerViewId, fragment).commit();
    }

    /**
     * 替换Fragment
     *
     * @param fragment fragment
     * @param bundle   传递的数据
     */
    protected void replaceFragment(@IdRes int containerViewId, BaseFragment fragment, @Nullable Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.replace(containerViewId, fragment).addToBackStack(null).commit();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
