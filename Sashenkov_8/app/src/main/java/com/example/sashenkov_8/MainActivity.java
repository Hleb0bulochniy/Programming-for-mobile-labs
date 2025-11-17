package com.example.sashenkov_8;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jspecify.annotations.NonNull;

public class MainActivity extends AppCompatActivity {
    private static final int CALL_PHONE_PERMISSION_CODE = 100;
    private static final int READ_CONTACTS_PERMISSION_CODE = 101;
    Button testActivityButton;
    TextView logText;
    EditText phoneText;
    Button callButton;
    Button readContactsButton;
    TextView contactsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        testActivityButton = findViewById(R.id.button);
        phoneText = findViewById(R.id.editTextPhone);
        logText = findViewById(R.id.logText);
        callButton = findViewById(R.id.callButton);
        readContactsButton = findViewById(R.id.readContactsButton);
        contactsText = findViewById(R.id.contactsText);


        testActivityButton.setOnClickListener(this::SetTestAcitivty);
        callButton.setOnClickListener(v -> checkPermission(Manifest.permission.CALL_PHONE,CALL_PHONE_PERMISSION_CODE));
        readContactsButton.setOnClickListener(v -> checkContactsPermission());


        logText.append("onCreate" + "\n");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        logText.append("onStart" + "\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logText.append("onResume" + "\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logText.append("onPause" + "\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logText.append("onStop" + "\n");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logText.append("onRestart" + "\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logText.append("onDestroy" + "\n");
    }

    private void SetTestAcitivty(View v){
        //logText.append("SetTestAcitivty" + "\n");
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), TestActivity.class);
        startActivity(intent);
    }

    public void callPhone() {
        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +
                phoneText.getText()));
        startActivity(dialIntent);

    }
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]
                    { permission }, requestCode);
        }
        else {
            callPhone();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
        if (requestCode == CALL_PHONE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,"Access granted",Toast.LENGTH_SHORT).show();
                        callPhone();
            }
            else {
                Toast.makeText(MainActivity.this,"Access denied",Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == READ_CONTACTS_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read contacts granted", Toast.LENGTH_SHORT).show();
                readContacts();
            } else {
                Toast.makeText(this, "Read contacts denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkContactsPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED) {
            readContacts();
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{ Manifest.permission.READ_CONTACTS },
                    READ_CONTACTS_PERMISSION_CODE
            );
        }
    }

    private void readContacts() {
        StringBuilder sb = new StringBuilder();

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                },
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                        )
                );
                String number = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                );

                sb.append(name)
                        .append(" : ")
                        .append(number)
                        .append("\n");
            }
            cursor.close();
        }

        if (sb.length() == 0) {
            contactsText.setText("Error: no contacts or access to contacts");
        } else {
            contactsText.setText(sb.toString());
        }
    }
}