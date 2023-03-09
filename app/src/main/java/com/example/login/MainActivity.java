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

public class MainActivity extends AppCompatActivity {
    Button signup;
    EditText mail, user,pass;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail = findViewById(R.id.signup_mail);
        user = findViewById(R.id.signup_username);
        pass = findViewById(R.id.signup_password);
        signup = findViewById(R.id.signup);
        login=(TextView) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });


        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getText().toString().length() != 0) {
                    if(mail.getText().toString().length() != 0){
                    if (mail.getText().toString().contains("@")) {
                        if (mail.getText().toString().contains(".")) {
                            if (mail.getText().toString().indexOf(".") > mail.getText().toString().indexOf("@")) {
                                if (pass.getText().toString().length() != 0) {
                                    if (pass.getText().toString().length() > 4) {
                                        db.add(user.getText().toString(),mail.getText().toString(),pass.getText().toString());
                                        Toast.makeText(MainActivity.this, "!!!!! Signup Successful !!!!!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), Login.class);
                                        startActivity(i);
                                    }
                                else {
                                    Toast.makeText(MainActivity.this, "Length of password should be greater than 5", Toast.LENGTH_SHORT).show();
                                }
                                }else {
                                    Toast.makeText(MainActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "In email '@' must be before '.'", Toast.LENGTH_SHORT).show();
                                }

                        } else {
                            Toast.makeText(MainActivity.this, "Email must Contain '.'", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Email must Contain '@'", Toast.LENGTH_SHORT).show();
                    }
                }
                    else{
                        Toast.makeText(MainActivity.this, "Mail Id cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                }
                    else {
                    Toast.makeText(MainActivity.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
