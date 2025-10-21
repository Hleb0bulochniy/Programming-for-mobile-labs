package com.example.sashenkov_4;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;

public class MainActivity extends AppCompatActivity {
    ToggleButton appOnOffToggle;
    CheckBox isImageClickableCheckBox;
    RadioButton selectCat1RadioButton;
    RadioButton selectCat2RadioButton;
    ImageButton catImageButton;

    int currentImageResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        appOnOffToggle = findViewById(R.id.appOnOffToggle);
        isImageClickableCheckBox = findViewById(R.id.isImageClickableCheckBox);
        selectCat1RadioButton = findViewById(R.id.selectCat1RadioButton);
        selectCat2RadioButton = findViewById(R.id.selectCat2RadioButton);
        catImageButton = findViewById(R.id.catImageButton);

        currentImageResId = R.drawable.screenshot_4_1;
        catImageButton.setImageResource(currentImageResId);

        appOnOffToggle.setOnCheckedChangeListener(this::setAppOnOffToggle);
        isImageClickableCheckBox.setOnCheckedChangeListener(this::setIsImageClickableCheckBox);
        selectCat1RadioButton.setOnCheckedChangeListener(this::setSelectCat1RadioButton);
        selectCat2RadioButton.setOnCheckedChangeListener(this::setSelectCat2RadioButton);

        catImageButton.setOnClickListener(this::setCatImageButton);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setAppOnOffToggle(CompoundButton buttonView, boolean isChecked)
    {
        if (buttonView==appOnOffToggle) {
            if (isChecked){
                isImageClickableCheckBox.setVisibility(View.VISIBLE);
                selectCat1RadioButton.setVisibility(View.VISIBLE);
                selectCat2RadioButton.setVisibility(View.VISIBLE);
                catImageButton.setVisibility(View.VISIBLE);
            }
            else{
                isImageClickableCheckBox.setVisibility(View.INVISIBLE);
                selectCat1RadioButton.setVisibility(View.INVISIBLE);
                selectCat2RadioButton.setVisibility(View.INVISIBLE);
                catImageButton.setVisibility(View.INVISIBLE);
            }

        }
    }

    public void setIsImageClickableCheckBox(CompoundButton buttonView, boolean isChecked)
    {
        if (buttonView==isImageClickableCheckBox)
        {
            if (isChecked)
            {
                catImageButton.setClickable(true);
            }
            else{
                catImageButton.setClickable(false);
            }
        }
    }

    public void setSelectCat1RadioButton(CompoundButton b, boolean isChecked) {
        if (b == selectCat1RadioButton && isChecked) {
            selectCat2RadioButton.setChecked(false);
            currentImageResId = R.drawable.screenshot_4_1;
            catImageButton.setImageResource(currentImageResId);
        }
    }

    public void setSelectCat2RadioButton(CompoundButton b, boolean isChecked) {
        if (b == selectCat2RadioButton && isChecked) {
            selectCat1RadioButton.setChecked(false);
            currentImageResId = R.drawable.screenshot_5_1;
            catImageButton.setImageResource(currentImageResId);
        }
    }

    public void setCatImageButton(View v) {
        if (!catImageButton.isClickable()) return;

        if (currentImageResId == R.drawable.screenshot_4_1) {
            currentImageResId = R.drawable.screenshot_4_2;
        } else if (currentImageResId == R.drawable.screenshot_4_2) {
            currentImageResId = R.drawable.screenshot_4_1;
        } else if (currentImageResId == R.drawable.screenshot_5_1) {
            currentImageResId = R.drawable.screenshot_5_2;
        } else if (currentImageResId == R.drawable.screenshot_5_2) {
            currentImageResId = R.drawable.screenshot_5_1;
        }
        catImageButton.setImageResource(currentImageResId);
    }
}