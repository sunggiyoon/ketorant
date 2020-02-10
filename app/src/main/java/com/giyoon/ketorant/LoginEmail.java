package com.giyoon.ketorant;

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
import com.google.firebase.auth.FirebaseUser;

import static com.giyoon.ketorant.util.StatusCode.SIGN_IN_SUCCESS;

public class LoginEmail extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText etx_email;
    private EditText etx_pass;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        mAuth = FirebaseAuth.getInstance();

        etx_email = findViewById(R.id.etx_email);
        etx_pass = findViewById(R.id.etx_pass);
        button = findViewById(R.id.bt_generate_otp);
        button.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        final String email = etx_email.getText().toString();
        final String password = etx_pass.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            currentUser = mAuth.getCurrentUser();
                            setResult(SIGN_IN_SUCCESS);
                            finish();
                        } else {
                            createNewEmailAccount(email, password);
                        }

                    }
                });
    }

    private void createNewEmailAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"입력한 메일주소로 계정을 생성하였습니다",Toast.LENGTH_SHORT).show();
                            currentUser = mAuth.getCurrentUser();
                            setResult(SIGN_IN_SUCCESS);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"메일주소와 비밀번호를 다시 확인해주세요.",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
