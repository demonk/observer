package cn.demonk.observer.view;

import android.widget.TextView;

import cn.demonk.observer.IObserverAction;
import cn.demonk.observer.Observable;
import cn.demonk.observer.ObserveConsts;
import cn.demonk.observer.Property;

/**
 * Created by ligs on 10/16/16.
 */
public class TextViewObserver extends ViewObserver {
    private TextView mView;

    public TextViewObserver(TextView view, Property property) {
        mView = view;

        bind(ObserveConsts.PROP_TEXT, new IObserverAction() {
            @Override
            public void update(Observable observable) {
                assert observable != null;
                String value = observable.getValue(ObserveConsts.PROP_TEXT).toString();
                mView.setText(value);
            }
        }, new IGetValue() {
            @Override
            public String getValue() {
                return (String) mView.getText();
            }
        })
                //以下冗余才能处理类型不匹配问题
                .bind(ObserveConsts.PROP_BOOLEAN, new IObserverAction() {
                    @Override
                    public void update(Observable observable) {
                        assert observable != null;
                        String value = observable.getValue(ObserveConsts.PROP_BOOLEAN).toString();
                        mView.setText(value);
                    }
                }, new IGetValue() {
                    @Override
                    public Boolean getValue() {
                        return Boolean.valueOf((String) mView.getText());
                    }
                });
    }

}
