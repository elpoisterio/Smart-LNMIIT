package io.elpoisterio.smartlnmiit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.elpoisterio.smartlnmiit.R;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button loginButton;
    EditText email;
    EditText password;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        loginButton = (Button)findViewById(R.id.login);
        signUpButton = (Button) findViewById(R.id.sign_up);

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    private boolean checkEmptyFields(){
        if(email.getText().toString().length()==0) {
            Toast.makeText(Login.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.getText().toString().length() == 0 ){
            Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    public static boolean isEmailValid(CharSequence email) {

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        System.out.println(matcher);
        return matcher.matches();
    }

    @Override
    public void onClick(View v) {
        if (v == loginButton){
            checkEmptyFields();
            if(!isEmailValid(email.getText().toString())){
                Toast.makeText(Login.this, "Please use email id provided by college", Toast.LENGTH_SHORT).show();
            } else {
                //callApi()
                moveToHome();
            }

        } else if(v == signUpButton){
            moveToSignUpScreen();
        }
    }

    private void moveToSignUpScreen() {
        Intent intent = new Intent(Login.this , Home.class);
        startActivity(intent);
        finish();
    }

    private void moveToHome() {
        Intent intent = new Intent(Login.this , ChooseType.class);
        startActivity(intent);
        finish();
    }
}
