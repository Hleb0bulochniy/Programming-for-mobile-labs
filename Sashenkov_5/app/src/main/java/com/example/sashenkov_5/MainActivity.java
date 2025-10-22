package com.example.sashenkov_5;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.FontRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity extends AppCompatActivity {
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.my_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void setFont(TextView text, @FontRes int fontId) {
        if (text != null) {
            text.setTypeface(ResourcesCompat.getFont(this, fontId));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final LinearLayout mylayout = findViewById(R.id.root);
        int itId = item.getItemId();

        if (itId == R.id.red) {
            mylayout.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            return true;
        } else if (itId == R.id.green) {
            mylayout.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
            return true;
        } else if (itId == R.id.blue) {
            mylayout.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
            return true;
        } else if (itId == R.id.exit) {
            finish();
            return true;
        } else if (itId == R.id.newFont1) {
            setFont(text, R.font.new_font1);
            return true;
        } else if (itId == R.id.newFont2) {
            setFont(text, R.font.new_font2);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
