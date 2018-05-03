package br.com.curymorais.desafiosantander.util;

import android.content.Context;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import br.com.curymorais.desafiosantander.controller.FormActivity;
import br.com.curymorais.desafiosantander.domain.dto.FieldDTO;
import br.com.curymorais.desafiosantander.domain.model.EditTextSantander;

public class FieldBuilder {
    public static EditText getEditTextFromField(FieldDTO f, Context c){
        EditText et = new EditText(c);
        et.setText(f.getMessage());
        et.setId(f.getId());
        et.setWidth(1000);
        return et;
    }

    public static TextView getTextViewFromField(FieldDTO f, Context c){
        TextView t = new TextView(c);
        t.setId(f.getId());
        t.setText(f.getMessage());
        if (f.isHidden()){
            t.setVisibility(View.INVISIBLE);
        }
        return  t;
    }

    public static Button getButtonViewFromField(FieldDTO f, Context c) {
        Button b = new Button(c);
        b.setId(f.getId());
        b.setText(f.getMessage());
        if (f.isHidden()){
            b.setVisibility(View.INVISIBLE);
        }
        return b;
    }

    public static CheckBox getCheckBoxFromField(FieldDTO f, Context c) {
        CheckBox ch = new CheckBox(c);
        ch.setText(f.getMessage());
        ch.setId(f.getId());
        if (f.isHidden()){
            ch.setVisibility(View.INVISIBLE);
        }
        ch.setChecked(true);
        return  ch;
    }

    public static EditTextSantander getEditTextSantanderFromField(FieldDTO f, Context c){
        EditTextSantander et = new EditTextSantander(c);
        et.setHint(f.getMessage());
        et.setId(f.getId());
        et.setWidth(1000);
        if (f.isRequired()){
            et.setRequired(true);
        }else {
            et.setRequired(false);
        }
        if(f.getMessage().equalsIgnoreCase("telefone")) {
            et.setInputType(InputType.TYPE_CLASS_PHONE);
            BrPhoneNumberFormatter addLineNumberFormatter = new BrPhoneNumberFormatter(new WeakReference<EditText>(et));
            et.addTextChangedListener(addLineNumberFormatter);
        }else if (f.getMessage().equalsIgnoreCase("email")){
            et.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        }

        return et;
    }
}
