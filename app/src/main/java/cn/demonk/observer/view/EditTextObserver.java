package cn.demonk.observer.view;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import cn.demonk.observer.IObserverAction;
import cn.demonk.observer.Observable;
import cn.demonk.observer.ObserveConsts;
import cn.demonk.observer.Property;

/**
 * Created by ligs on 10/16/16.
 */
public class EditTextObserver extends ViewObserver {

    private EditText mView;

    public EditTextObserver(@NonNull EditText view, final Property property) {
        assert view != null;
        this.mView = view;

        //在接收到被监控对象的改变时触发，由于被监控对象可能有多种响应的类型，需要有一个map对应处理
        bind(ObserveConsts.PROP_TEXT, new IObserverAction() {
            @Override
            public void update(Observable observable) {
                assert observable != null;
                if (observable != null) {
                    String value = (String) observable.getValue(ObserveConsts.PROP_TEXT);
                    Object oldValue = mView.getText();

                    if (oldValue != value && !oldValue.toString().equals(value.toString())) {
                        mView.setText(value);
                    }
                }
            }
        }, new IGetValue() {
            @Override
            public String getValue() {
                return mView.getText().toString();
            }
        });

        view.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                property.notifyDataChange();
            }
        });
    }


}
