package com.example.a545;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        usernameEditText = findViewById(R.id.textView);
        passwordEditText = findViewById(R.id.textView2);
        loginButton = findViewById(R.id.button);
        signupButton = findViewById(R.id.button2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em =usernameEditText.getText().toString();
                String pass =passwordEditText.getText().toString();
                mAuth.signInWithEmailAndPassword(em,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(MainActivity.this, "logged in successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(MainActivity.this,Dash.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "log in failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em =usernameEditText.getText().toString();
                String pass =passwordEditText.getText().toString();
                mAuth.createUserWithEmailAndPassword(em,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(MainActivity.this, "signed up successfully", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "sign up failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }

}
