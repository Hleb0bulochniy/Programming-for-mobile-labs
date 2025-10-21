package com.example.a3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    private float mTextSize = 20;
    private EditText mEdit;
    private TextView tSize;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdit = findViewById(R.id.editTextText);
        tSize = findViewById(R.id.textView);
        ToggleButton buttonB = findViewById(R.id.toggleButton2);
        ToggleButton buttonI = findViewById(R.id.toggleButton3);
        RadioButton buttonSans = findViewById(R.id.radioButton);
        RadioButton buttonSerif = findViewById(R.id.radioButton2);
        RadioButton buttonMono = findViewById(R.id.radioButton3);
        Button buttonPlus = findViewById(R.id.button);
        Button buttonMinus = findViewById(R.id.button2);

        buttonB.setOnClickListener(this);
        buttonI.setOnClickListener(this);
        buttonSans.setOnClickListener(this);
        buttonSerif.setOnClickListener(this);
        buttonMono.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        int but = v.getId();
        if (but == R.id.toggleButton2) {
            if (mEdit.getTypeface().getStyle() == Typeface.ITALIC)
                mEdit.setTypeface(mEdit.getTypeface(),
                        Typeface.BOLD_ITALIC);
            else if (mEdit.getTypeface().getStyle() ==
                    Typeface.BOLD_ITALIC)
                mEdit.setTypeface(mEdit.getTypeface(), Typeface.ITALIC);
            else if (mEdit.getTypeface().getStyle() == Typeface.BOLD)
                mEdit.setTypeface(Typeface.create(mEdit.getTypeface(),
                        Typeface.NORMAL));
            else mEdit.setTypeface(mEdit.getTypeface(), Typeface.BOLD);
        }
        else if (but == R.id.toggleButton3) {
            if (mEdit.getTypeface().getStyle() == Typeface.BOLD)
                mEdit.setTypeface(mEdit.getTypeface(),
                        Typeface.BOLD_ITALIC);
            else if (mEdit.getTypeface().getStyle() ==
                    Typeface.BOLD_ITALIC)
                mEdit.setTypeface(mEdit.getTypeface(), Typeface.BOLD);
            else if (mEdit.getTypeface().getStyle() == Typeface.ITALIC)
                mEdit.setTypeface(Typeface.create(mEdit.getTypeface(),
                        Typeface.NORMAL));
            else
                mEdit.setTypeface(mEdit.getTypeface(), Typeface.ITALIC);
        }
        else if (but == R.id.radioButton) {
            ((RadioButton) findViewById(R.id.radioButton2)).setChecked(false);
            ((RadioButton) findViewById(R.id.radioButton3)).setChecked(false);
            mEdit.setTypeface(Typeface.SANS_SERIF, mEdit.getTypeface().getStyle());
        }
        else if (but == R.id.radioButton2) {
            ((RadioButton) findViewById(R.id.radioButton)).setChecked(false);
            ((RadioButton) findViewById(R.id.radioButton3)).setChecked(false);
            mEdit.setTypeface(Typeface.SERIF, mEdit.getTypeface().getStyle());
        }
        else if (but == R.id.radioButton3) {
            ((RadioButton) findViewById(R.id.radioButton)).setChecked(false);
            ((RadioButton) findViewById(R.id.radioButton2)).setChecked(false);
            mEdit.setTypeface(Typeface.MONOSPACE, mEdit.getTypeface().getStyle());
        }
        else if (but == R.id.button) {
            if (mTextSize <= 72)
                mTextSize += 2;
            mEdit.setTextSize(mTextSize);
            tSize.setText(Integer.toString((int) mTextSize));
        }
        else if (but == R.id.button2) {
            if (mTextSize >= 20)
                mTextSize -= 2;
            mEdit.setTextSize(mTextSize);
            tSize.setText(Integer.toString((int) mTextSize));
        }
    }
}