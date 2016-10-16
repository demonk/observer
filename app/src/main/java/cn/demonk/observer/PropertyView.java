package cn.demonk.observer;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import cn.demonk.observer.view.CheckboxObserver;
import cn.demonk.observer.view.EditTextObserver;
import cn.demonk.observer.view.IViewObserver;
import cn.demonk.observer.view.TextViewObserver;

/**
 * 监视view
 * Created by ligs on 10/16/16.
 */
public class PropertyView<T extends View> extends Property<T> {
    private final TypeTranslator mTypeTranslator;

    public PropertyView() {
        mTypeTranslator = new TypeTranslator(this);
    }

    public PropertyView(T value) {
        attach(value);
        mTypeTranslator = new TypeTranslator(value, this);
    }

    public void bind(PropertyData property) {
        String action = property.getDefaultAction();

        //互相绑定，也就是说data去绑view时不能互相绑定
        property.bind(action, this);
        super.bind(action, property);
    }

    //多对多的关系，view可能一个属性对应一个PropertyData
    @Override
    public Object getValue(String action) {
        return mTypeTranslator.translate(mObserver, action);
    }

    @Override
    public void update(String action, Observable observable) {
//        Object observableValue = observable.getValue();
//        if (observableValue != null) {
        mTypeTranslator.update(mObserver, action, observable);
//        }
    }

    private static class TypeTranslator {
        private IViewObserver mViewObserver = null;
        private Property mProperty = null;

        public TypeTranslator(View view, Property property) {
            this(property);
            init(view);
        }

        public TypeTranslator(Property property) {
            this.mProperty = property;
        }

        private void init(View view) {
            if (EditText.class.isAssignableFrom(view.getClass())) {
                mViewObserver = new EditTextObserver((EditText) view, this.mProperty);
            } else if (CheckBox.class.isAssignableFrom(view.getClass())) {
                mViewObserver = new CheckboxObserver((CheckBox) view, this.mProperty);
            } else if (TextView.class.isAssignableFrom(view.getClass())) {
                mViewObserver = new TextViewObserver((TextView) view, this.mProperty);
            }
        }

        Object translate(View view, String action) {
            if (mViewObserver == null)
                init(view);
            return mViewObserver.getValue(action);
        }

        void update(View view, String action, Observable observable) {
            if (mViewObserver == null)
                init(view);
            mViewObserver.update(action, observable);
        }
    }
}
