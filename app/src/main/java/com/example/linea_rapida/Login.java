package com.example.linea_rapida;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth auth;

    private EditText emailET;
    private EditText passwordET;
    private Button loginBtn;
    private TextView errorTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.loginETEmail);
        passwordET = findViewById(R.id.loginETPassword);
        loginBtn = findViewById(R.id.loginBtn);
        errorTV =  findViewById(R.id.loginErorTV);

        loginBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.loginBtn:

                errorTV.setText("");

                String email = emailET.getText().toString().trim();
                String password = passwordET.getText().toString().trim();


                if(checkLoginData(email, password))
                    login(email, password);
                
                break;

            case R.id.loginForgottenPasswordTV:


                break;

        }
    }

    private boolean checkLoginData(String email, String password){

        String errorMsg = "";

        if(email.equals("")){
            errorMsg += "" + getString(R.string.no_email_error) + "\n";
        }

        if(password.equals("")){
            errorMsg += getString(R.string.no_password_error);
        }

        errorTV.setText(errorMsg);

        if(errorMsg.equals(""))
            return true;
        else
            return false;

    }

    private void login(String email, String password) {

        SharedPreferences preferences = getSharedPreferences("bin", MODE_PRIVATE);
        preferences.edit().putString("email", email).apply();
        preferences.edit().putString("password", password).apply();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            goToHome();
                        }else{
                           showError(task);
                        }
                    }
                });

    }
    private void showError(Task<AuthResult> task){
        Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG);
    }

    private void goToHome(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}