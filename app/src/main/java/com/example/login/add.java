package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add extends AppCompatActivity {
Button add;
EditText user,mail,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        user = (EditText) findViewById(R.id.add_username);
        mail = (EditText) findViewById(R.id.add_mail);
        pass = (EditText) findViewById(R.id.add_password);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            db.add(user.getText().toString(),mail.getText().toString(),pass.getText().toString());
            }
        });
    }
}