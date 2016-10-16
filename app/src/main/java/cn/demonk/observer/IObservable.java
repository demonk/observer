package cn.demonk.observer;

/**
 * Created by ligs on 10/15/16.
 */
public interface IObservable {

    void notifyDataChange();

    void bind(String action, IObserver observer);

    Object getValue(String action);//获取被观察对象的值
}
