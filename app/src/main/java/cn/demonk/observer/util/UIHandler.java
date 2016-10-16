package cn.demonk.observer.util;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by ligs on 10/16/16.
 */
public class UIHandler {

    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());

    public static final void post(Runnable runnable) {
        if (isOnMainLooper()) {
            runnable.run();
        } else {
            sMainHandler.post(runnable);
        }
    }

    public static final void postDelayed(Runnable runnable, int delay) {
        sMainHandler.postDelayed(runnable, delay);
    }

    public static final boolean isOnMainLooper() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

}
