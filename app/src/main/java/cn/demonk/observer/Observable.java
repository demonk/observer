package cn.demonk.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 被监视对象基类,可被观察
 * <p/>
 * Created by ligs on 10/15/16.
 */
public abstract class Observable<T> implements IObservable {

    private Map<String, List<IObserver>> mObserverMap = new HashMap<>();//用于存放添加到此观察对象的对象


    /**
     * 将适用于当前Observable的key及监听器绑定进来
     *
     * @param action   当前Observable可被观察的key
     * @param observer
     */
    public void bind(String action, IObserver observer) {
        List<IObserver> observerList;
        if (mObserverMap.containsKey(action)) {
            observerList = mObserverMap.get(action);
        } else {
            observerList = new ArrayList<>();
            mObserverMap.put(action, observerList);
        }
        observerList.add(observer);
    }

    /**
     * notifyChange的时候只能响应一种类型的，因为一个Property只有一个值
     */
    public void notifyDataChange() {
        Iterator<Map.Entry<String, List<IObserver>>> iterator = mObserverMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<IObserver>> entry = iterator.next();
            String key = entry.getKey();
            List<IObserver> observerList = entry.getValue();
            for (IObserver observer : observerList) {
                observer.update(key, this);
            }
        }
    }

    public abstract void set(T value);

}
