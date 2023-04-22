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

public class MainActivity extends AppCompatActivity {
    private EditText fnet,emet,pet,rpet;
    private Button rb;
    private TextView textView3;
    private ProgressBar pb;
    private FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        fnet=findViewById(R.id.fnet);
        emet=findViewById(R.id.emet);
        pet=findViewById(R.id.pet);
        rpet=findViewById(R.id.rpet);
        textView3=findViewById(R.id.textView3);
        rb=findViewById(R.id.rb);
        //rb=findViewById(R.id.rb);
        pb=findViewById(R.id.pb);
        fauth=FirebaseAuth.getInstance();

    /*if(fauth.getCurrentUser()!=null)
    {
        startActivities(new Intent[]{new Intent(getApplicationContext(), MainActivity2front.class)});
        finish();
    }*/

        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emet.getText().toString().trim();
                String password=pet.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emet.setError("email Require");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    pet.setError("password Require");
                    return;
                }
                if(password.length()<=8){
                    pet.setError("Password must be greater than 8");
                    return;
                }

                pb.setVisibility(View.VISIBLE);

                String full_name=fnet.getText().toString();

                String re_password=rpet.getText().toString();
                if (re_password.compareTo(password)!=0)
                {pet.setError("passwords are not same");
                    rpet.setError("passwords are not same");
                    return;
                }
                //startActivities(new Intent[]{new Intent(getApplicationContext(), MainActivity2log.class)});
                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                startActivities(new Intent[]{new Intent(getApplicationContext(), MainActivity2.class)});
                            }
                            pb.setVisibility(View.INVISIBLE); }
                        else{
                            Toast.makeText(MainActivity.this, " Error!!\n"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.INVISIBLE); }
                    }
                });




            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    startActivities(new Intent[]{new Intent(getApplicationContext(), MainActivity2.class)});
                }
                pb.setVisibility(View.INVISIBLE);
            }
        });
    }
}