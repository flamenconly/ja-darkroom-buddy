package com.thomasringhofer.jadarkroombuddy.common;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Thomas on 22.03.2018.
 */

public abstract class TextValidator implements TextWatcher {

    private final EditText editText;

    public TextValidator(final EditText editText){
        this.editText = editText;
    }

    public abstract void validate(EditText editText,String text);

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Not implemented
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Not implemented
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editText.getText().toString();
        validate(editText,text);
    }
}
