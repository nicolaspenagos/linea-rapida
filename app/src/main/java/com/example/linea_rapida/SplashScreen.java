package com.example.linea_rapida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.linea_rapida.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

public class SplashScreen extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser fbUser = auth.getCurrentUser();

        if(fbUser == null ){

            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();


        }else{
            Log.e(">>>", fbUser.getUid() + "hola");
            Log.e(">>>", "hola");
            db.collection("users").document(fbUser.getUid()).get().addOnCompleteListener(
                    task -> {
                        if(task.isSuccessful()){

                            DocumentSnapshot dataSnapshot = task.getResult();
                            User currentUser = dataSnapshot.toObject(User.class);
                            //while(currentUser != null);
                            Log.e(">>>", currentUser.getFullName());
                            Gson gson = new Gson();
                            String userJson = gson.toJson(currentUser);
                            Log.e(">>>", userJson);
                            Intent intent = new Intent(this, MainActivity.class);
                            intent.putExtra("user", userJson);

                            startActivity(intent);
                            finish();


                        }else{

                            Intent intent = new Intent(this, Login.class);
                            startActivity(intent);
                            finish();

                        }

                    }
            );

        }
    }
}