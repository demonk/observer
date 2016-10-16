package cn.demonk.observer;

/**
 * 监视者需要实现
 * Created by ligs on 10/15/16.
 */
public interface IObserver {

    void update(String action, Observable observable);
}
