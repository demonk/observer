package cn.demonk.observer;

/**
 * Created by ligs on 10/16/16.
 */
public class PropertyData<T> extends Property<T> {

    private static final ActionTranslator ACTION_TRANSLATOR = new ActionTranslator();

    /**
     * 顺带初始化
     *
     * @param value
     */
    public PropertyData(T value) {
        attach(value);
    }

    public String getDefaultAction() {
        return ACTION_TRANSLATOR.getType(mObserver);
    }

    @Override
    public T getValue(String action) {
        //Data没有多个action
        return mObserver;
    }

    @Override
    public void update(String action, Observable observable) {
        assert observable != null;

        Object value = observable.getValue(action);
        set((T) value);
    }

    private static final class ActionTranslator {

        public ActionTranslator() {
        }

        String getType(Object value) {
            assert value != null;
            String type = ObserveConsts.PROP_TEXT;
            if (value instanceof Integer) {
                type = ObserveConsts.PROP_INT;
            } else if (value instanceof Boolean) {
                type = ObserveConsts.PROP_BOOLEAN;
            }

            return type;
        }
    }
}
