package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class curd extends AppCompatActivity {
    Button Update,Remove,View;
    EditText user,mail,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curd);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        user = (EditText) findViewById(R.id.update_username);
        mail = (EditText) findViewById(R.id.update_mail);
        pass = (EditText) findViewById(R.id.update_password);
        Update = (Button) findViewById(R.id.Update);
        Remove = (Button) findViewById(R.id.Remove);
        View = (Button) findViewById(R.id.View);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                boolean isPresent = db.isValuePresent(user.getText().toString(),pass.getText().toString());
                if(isPresent == true){
                db.update(user.getText().toString(),mail.getText().toString(),pass.getText().toString());
                Toast.makeText(curd.this, "Update Successful", Toast.LENGTH_SHORT).show();
            }
            else{
                    Toast.makeText(curd.this, "Username not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                boolean isPresent = db.isValuePresent(user.getText().toString(),pass.getText().toString());
                if(isPresent == true) {
                    db.remove(user.getText().toString());
                    Toast.makeText(curd.this, "Remove Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(curd.this, "Username not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(getApplicationContext(),viewdata.class);
                startActivity(i);
            }
        });
    }
}