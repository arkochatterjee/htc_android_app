package com.arkochatterjee.hindtowardschange;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

/**
 * Created by Arko Chatterjee on 12-08-2017.
 */

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthstatelistener;
    EditText email1,password1,first,last,phone,password2;
    Button register;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;
    private ProgressDialog mprogressdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = getInstance();

        /*
        email1 = (EditText) findViewById(R.id.et_email);
        password1 = (EditText) findViewById(R.id.et_pass);
        password2 = (EditText) findViewById(R.id.et_cpass);
        phone = (EditText) findViewById(R.id.et_phone);
        first = (EditText) findViewById(R.id.et_fname);
        last = (EditText) findViewById(R.id.et_lname); */

        email1 = (EditText) findViewById(R.id.et_email);
        password1 = (EditText) findViewById(R.id.et_pass);
        //password2 = (EditText) findViewById(R.id.et_cpass);
        phone = (EditText) findViewById(R.id.et_phone);
        first = (EditText) findViewById(R.id.et_firstname);
        last = (EditText) findViewById(R.id.et_lastname);


        mprogressdialog = new ProgressDialog(this);

        mAuthstatelistener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(Register.this, MainActivity.class));
                }
            }
        };



        register = (Button)findViewById(R.id.btn_sign_up);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnSignup();
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthstatelistener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthstatelistener != null) {
            mAuth.removeAuthStateListener(mAuthstatelistener);
        }
    }

    public void OnSignup(){
        final String email = email1.getText().toString();
        final String password = password1.getText().toString();
        // final String cpassword = password2.getText().toString();
        final String fn = first.getText().toString();
        final String ln = last.getText().toString();
        final String pn = phone.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || /*TextUtils.isEmpty(cpassword) ||*/ TextUtils.isEmpty(fn) || TextUtils.isEmpty(ln) || TextUtils.isEmpty(pn)){
            Toast.makeText(Register.this,"Fields are Empty",Toast.LENGTH_LONG).show();
        }
      /* else if(!password.equals(cpassword)) {
            Toast.makeText(Register.this, "Password and Confirm Password don't match!", Toast.LENGTH_LONG).show();
        }*/
        else {

            mprogressdialog.setMessage("Signing Up");
            mprogressdialog.show();
            mprogressdialog.setCanceledOnTouchOutside(false);

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "Successfully Signed Up", Toast.LENGTH_LONG).show();
                        upload();
                    } else if (!task.isSuccessful()) {
                        Toast.makeText(Register.this, "Signing Up Failed", Toast.LENGTH_LONG).show();
                        mprogressdialog.dismiss();
                    }
                }
            });
        }
    }

    private void upload() {
        final String email = email1.getText().toString();
        final String fn = first.getText().toString();
        final String ln = last.getText().toString();
        final String pn = phone.getText().toString();
        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        String curr = current.getUid();

        mDatabase = database.getReference().child("Users").child(curr);

        HashMap<String,String> data = new HashMap<>();
        data.put("First Name",fn);
        data.put("Last Name",ln);
        data.put("Email",email);
        data.put("Phone",pn);
       // data.put("Image","https://firebasestorage.googleapis.com/v0/b/aaruush-17-17533.appspot.com/o/Profile_pictures%2Fnavlogo.png?alt=media&token=770c9bed-b94d-4f55-9aca-e153efcaf1c1");
        mDatabase.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, "Data Updated Succesfully", Toast.LENGTH_LONG).show();
                    mprogressdialog.dismiss();
                }
                else {
                    Toast.makeText(Register.this, "Data Update Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
