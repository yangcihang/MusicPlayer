package com.example.yang.musicplayer.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.example.yang.musicplayer.MusicPlayerApplication;

/**
 * Toast工具类
 * @author YangCihang
 * @since 17/7/12.
 * email yangcihang@hrsoft.net
 */

public final class ToastUtil {
    private static final int duration = Toast.LENGTH_SHORT;
    private static final boolean isShowErrorCode = true;

    public static void showToast(final String msg) {
        Utility.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MusicPlayerApplication.getInstance(), msg, duration).show();
            }
        });
    }

    public static void showToast(@StringRes final int resId) {
        Utility.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MusicPlayerApplication.getInstance(), resId, duration).show();
            }
        });
    }

    public static void showToast(String msg, int... errorCode) {
        if (isShowErrorCode) {
            for (int anErrorCode : errorCode) {
                msg = msg + "-" + anErrorCode;
            }
        }
        showToast(msg);
    }

    public static void showToast(@StringRes int resId, int... errorCode) {
        String msg = MusicPlayerApplication.getInstance().getResources().getString(resId);
        if (isShowErrorCode) {
            for (int anErrorCode : errorCode) {
                msg = msg + "-" + anErrorCode;
            }
        }
        showToast(msg);
    }
}
