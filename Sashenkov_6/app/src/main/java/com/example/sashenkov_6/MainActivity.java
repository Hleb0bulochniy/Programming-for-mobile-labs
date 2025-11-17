package com.example.sashenkov_6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity implements MyDialogFragment.MyDialogFragmentListener {

    Button alertButton;
    TextView myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        alertButton = findViewById(R.id.button);
        alertButton.setOnClickListener(this::setAlertButton);
        myText = findViewById(R.id.textView3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    @Override
    public void onReturnValue(String value) {
        myText.setText(value);
    }

    public void setAlertButton(View v)
    {
        new MyDialogFragment().show(getSupportFragmentManager(), "confirm_exit");
    }
}