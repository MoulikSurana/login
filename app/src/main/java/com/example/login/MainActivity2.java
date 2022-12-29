package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    EditText lemet,lpet;
    Button lrb;
    TextView ltextView3;
    ProgressBar lpb;
    FirebaseAuth lfauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        lemet=findViewById(R.id.lemet);
        lpet=findViewById(R.id.lpet);
        ltextView3=findViewById(R.id.ltextView3);
        lrb=findViewById(R.id.lrb);
        lpb=findViewById(R.id.lpb);
        lfauth=FirebaseAuth.getInstance();

        lrb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=lemet.getText().toString().trim();
                String password=lpet.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    lemet.setError("email Require");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    lpet.setError("password Require");
                    return;
                }
                if(password.length()<=8){
                    lpet.setError("Password must be greater than 8");
                    return;
                }

                lpb.setVisibility(View.VISIBLE);

                lfauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity2.this, "logged in", Toast.LENGTH_SHORT).show();
                            startActivities(new Intent[]{new Intent(getApplicationContext(), MainActivity3.class)});
                        }
                        else{
                            Toast.makeText(MainActivity2.this, " Error!!\n"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            lpb.setVisibility(View.INVISIBLE);}
                    }
                });
                lpb.setVisibility(View.INVISIBLE); }
        });

        ltextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lpb.setVisibility(View.VISIBLE);
                startActivities(new Intent[]{new Intent(getApplicationContext(), MainActivity.class)});
                lpb.setVisibility(View.INVISIBLE);
            }
        });
    }
}