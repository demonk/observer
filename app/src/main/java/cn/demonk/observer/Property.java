package cn.demonk.observer;

/**
 * 用于接管view 或 data 的属性类
 * 因为双向绑定，同时作为观察者与被观察者
 * Created by ligs on 10/15/16.
 */
public abstract class Property<T> extends Observable<T> implements IObserver {

    //需要监视的对象
    T mObserver;

    public Property() {
    }

    public void attach(T value) {
        this.mObserver = value;
    }

    public void set(T value) {
        this.mObserver = value;
        notifyDataChange();
    }
}
