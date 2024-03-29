package www.joshmyapps.com.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp_Form extends AppCompatActivity {

    Button button;
    ProgressBar progressBar;
    EditText textEmail,textPassword,textConfirmPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__form);
        getSupportActionBar().setTitle("Sign up");


        button = (Button) findViewById(R.id.button_register);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textPassword = (EditText) findViewById(R.id.text_password);
        textEmail = (EditText) findViewById(R.id.text_email);
        textConfirmPassword = (EditText) findViewById(R.id.textConfirm_password);


        firebaseAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = textEmail.getText().toString().trim();
                String password = textPassword.getText().toString().trim();
                String confirmPassword = textConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){

                    Toast toast =  Toast.makeText(SignUp_Form.this, "Please enter your email", Toast.LENGTH_SHORT);
                    return;
                }
                if (TextUtils.isEmpty(password)){

                    Toast toast =  Toast.makeText(SignUp_Form.this, "Please enter your password", Toast.LENGTH_SHORT);
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword)){

                    Toast toast =  Toast.makeText(SignUp_Form.this, "Please confirm your password", Toast.LENGTH_SHORT);
                    return;
                }
                if (password.length()>6){

                    Toast toast =  Toast.makeText(SignUp_Form.this, "Password too short..", Toast.LENGTH_SHORT);


                }
                progressBar.setVisibility(view.VISIBLE);

                if (password.equals(confirmPassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUp_Form.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast toast =  Toast.makeText(SignUp_Form.this, "Registration Complete", Toast.LENGTH_SHORT);

                                    } else {

                                        Toast toast =  Toast.makeText(SignUp_Form.this, "Authentication Failed", Toast.LENGTH_SHORT);


                                    }

                                    // ...
                                }
                            });


                }


            }
        });


    }

    public void button_signUpForm(View view) {

        startActivity(new Intent(getApplicationContext(),SignUp_Form.class));
    }
}
