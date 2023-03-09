package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
Button login;
EditText user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        user = (EditText) findViewById(R.id.login_username);
        pass = (EditText) findViewById(R.id.login_password);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Show();
            }


     public void Show() {
         if (user.getText().toString().length() != 0) {
             if (pass.getText().toString().length() != 0) {
                 boolean isPresent = db.isValuePresent(user.getText().toString(),pass.getText().toString());
                 if(isPresent == true){
                     Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                     Intent i = new Intent(getApplicationContext(),curd.class);
                     startActivity(i);
                 }
                 else {
                     Toast.makeText(Login.this, "Username and Password do not match", Toast.LENGTH_SHORT).show();
                 }
             }
             else {
                 Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
             }
         }
         else{
             Toast.makeText(getApplicationContext(), "Enter Username" , Toast.LENGTH_SHORT).show();
         }
     }
        });
    }
}