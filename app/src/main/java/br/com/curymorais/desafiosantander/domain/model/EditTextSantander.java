package br.com.curymorais.desafiosantander.domain.model;

import android.content.Context;
import android.widget.EditText;

public class EditTextSantander extends android.support.v7.widget.AppCompatEditText {
    private boolean required;

    public EditTextSantander(Context context) {
        super(context);
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
