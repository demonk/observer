package cn.demonk.observer.view;

import cn.demonk.observer.Observable;

/**
 * Created by ligs on 10/16/16.
 */
public interface IViewObserver {

    Object getValue(String action);

    void update(String action, Observable value);

}
