package cn.demonk.observer.view;

import java.util.HashMap;
import java.util.Map;

import cn.demonk.observer.IObserverAction;
import cn.demonk.observer.Observable;
import cn.demonk.observer.util.UIHandler;

/**
 * Created by ligs on 10/16/16.
 */
public abstract class ViewObserver implements IViewObserver {

    Map<String, IObserverAction> mBindActionMap = new HashMap<>();
    Map<String, IGetValue> mBindGetValueMap = new HashMap<>();

    /**
     * 绑定 Action需要做的处理
     *
     * @param action
     * @param obserAction
     * @return
     */
    ViewObserver bind(String action, IObserverAction obserAction, IGetValue getValue) {
        mBindActionMap.put(action, obserAction);
        mBindGetValueMap.put(action, getValue);
        return this;
    }

    /**
     * 根据 Action 来获取对应的处理
     *
     * @param action
     * @return
     */
    private IObserverAction getAction(String action) {
        if (mBindActionMap.containsKey(action)) {
            return mBindActionMap.get(action);
        }
        return null;
    }

    private IGetValue getGetValue(String action) {
        if (mBindGetValueMap.containsKey(action)) {
            return mBindGetValueMap.get(action);
        }
        return null;
    }

    /**
     * 执行控件的更新操作
     */
    public void update(String actionKey, final Observable observable) {
        final IObserverAction action = getAction(actionKey);
        if (action != null) {
            UIHandler.post(new Runnable() {

                @Override
                public void run() {
                    action.update(observable);
                }
            });
        }
    }

    @Override
    public Object getValue(String action) {
        final IGetValue getValue = getGetValue(action);
        if (getValue != null) {
            return getValue.getValue();
        }
        return null;
    }
}
