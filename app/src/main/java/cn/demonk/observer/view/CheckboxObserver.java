package cn.demonk.observer.view;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import cn.demonk.observer.IObserverAction;
import cn.demonk.observer.Observable;
import cn.demonk.observer.ObserveConsts;
import cn.demonk.observer.Property;

/**
 * Created by ligs on 10/16/16.
 */
public class CheckboxObserver extends ViewObserver {
    private CheckBox mView;

    public CheckboxObserver(CheckBox view, final Property property) {
        this.mView = view;

        bind(ObserveConsts.PROP_BOOLEAN, new IObserverAction() {
            @Override
            public void update(Observable observable) {
                assert observable != null;
                Boolean value = (Boolean) observable.getValue(ObserveConsts.PROP_BOOLEAN);
                mView.setChecked(value);
            }
        }, new IGetValue() {
            @Override
            public Boolean getValue() {
                return mView.isChecked();
            }
        }).bind(ObserveConsts.PROP_TEXT, new IObserverAction() {
            @Override
            public void update(Observable observable) {
                assert observable != null;
                String value = (String) observable.getValue(ObserveConsts.PROP_TEXT);
                mView.setText(value);
            }
        }, new IGetValue() {
            @Override
            public String getValue() {
                return (String) mView.getText();
            }
        });

        //event
        this.mView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                property.notifyDataChange();
            }
        });
    }

//    @Override
//    public void update(final Object value) {
//        UIHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (value instanceof Boolean) {
//                    mView.setChecked((Boolean) value);
//                } else if (value instanceof String) {
//                    mView.setText((CharSequence) value);
//                }
//            }
//        });
//    }
}
