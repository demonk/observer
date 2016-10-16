package cn.demonk.observer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import cn.demonk.observertest.R;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    EditText mEditText;
    CheckBox mCheckbox;
    Button mButton;
    TextView mCheckBoxTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mEditText = (EditText) findViewById(R.id.editText);
        mCheckbox = (CheckBox) findViewById(R.id.checkBox);
        mButton = (Button) findViewById(R.id.button);
        mCheckBoxTv = (TextView) findViewById(R.id.chckbox_tv);

        PropertyView<TextView> textProperty = new PropertyView(mTextView);
        PropertyView<EditText> editTextProperty = new PropertyView(mEditText);
        PropertyView<CheckBox> checkBoxProperty = new PropertyView(mCheckbox);
        PropertyView<TextView> checkBoxTvProperty = new PropertyView<>(mCheckBoxTv);

        final PropertyData<String> strProperty = new PropertyData<>("");
        final PropertyData<Boolean> booleanProperty = new PropertyData<>(false);

        //由PropertyView去绑定type
        textProperty.bind(strProperty);
        editTextProperty.bind(strProperty);
        checkBoxProperty.bind(strProperty);
        checkBoxProperty.bind(booleanProperty);
        checkBoxTvProperty.bind(booleanProperty);//类型不匹配，不会发生作用,前者需要使用TEXT,后者是BOOLEAN

//        textProperty.bind(ObserveConsts.PROP_TEXT, strProperty);
//        strProperty.bind(ObserveConsts.PROP_TEXT, textProperty);
//
//        strProperty.bind(ObserveConsts.PROP_TEXT, editTextProperty);
//        editTextProperty.bind(ObserveConsts.PROP_TEXT, strProperty);
//
//        checkBoxProperty.bind(ObserveConsts.PROP_BOOLEAN, booleanProperty);
//        booleanProperty.bind(ObserveConsts.PROP_BOOLEAN, checkBoxProperty);
//
//        strProperty.bind(ObserveConsts.PROP_TEXT, checkBoxProperty);
//
//        booleanProperty.bind(ObserveConsts.PROP_TEXT, checkBoxTvProoertpy);//ACTION与第二个参数匹配


//        textProperty.addObserver(strProperty);
//        checkBoxProperty.addObserver(strProperty);
//        editTextProperty.addObserver(strProperty);
//
//        strProperty.addObserver(textProperty);
//        strProperty.addObserver(checkBoxProperty);
//        strProperty.addObserver(editTextProperty);
//
//        checkBoxProperty.addObserver(booleanProperty);
//        booleanProperty.addObserver(checkBoxProperty);

        strProperty.set("fsadfasdfasdf");
        booleanProperty.set(true);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strProperty.set("abcedfdfdfdf");
                booleanProperty.set(!mCheckbox.isChecked());
            }
        });
    }
}
