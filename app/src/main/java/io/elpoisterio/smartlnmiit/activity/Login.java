package io.elpoisterio.smartlnmiit.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.RequestParams;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.elpoisterio.smartlnmiit.R;
import io.elpoisterio.smartlnmiit.models.ModelUser;
import io.elpoisterio.smartlnmiit.restClient.RestManager;
import io.elpoisterio.smartlnmiit.utilities.CheckInternetConnection;
import io.elpoisterio.smartlnmiit.utilities.HandlerConstant;
import io.elpoisterio.smartlnmiit.utilities.HelperConstants;

public class Login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    Button loginButton;
    EditText email;
    EditText password;
    SignInButton googleSignInButton;
    int RC_SIGN_IN = 1;
    GoogleApiClient googleApiClient;
    Button signUpButtonFaculty, signUpButtonStudent;

    static Handler handler = new Handler();
    ProgressDialog dialog;
    private Context context = Login.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initView();
        updateUi();

    }

    private void initView() {

        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.login);
        signUpButtonFaculty = (Button) findViewById(R.id.sign_up_as_faculty);
        signUpButtonStudent = (Button) findViewById(R.id.sign_up_as_student);
        googleSignInButton = (SignInButton) findViewById(R.id.btn_sign_in_google);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        loginButton.setOnClickListener(this);
        signUpButtonFaculty.setOnClickListener(this);
        signUpButtonStudent.setOnClickListener(this);
        googleSignInButton.setOnClickListener(this);
    }

    private void updateUi() {

        handler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hideDialog(dialog);
                if (msg.what == HandlerConstant.FAILURE) {
                    Toast.makeText(Login.this, "Could not log in", Toast.LENGTH_SHORT).show();
                } else {
                    saveToDB(email.getText().toString(), false, "!");
                    moveToHome();
                }

            }
        };
    }

    private boolean checkEmptyFields() {
        if (email.getText().toString().length() == 0) {
            Toast.makeText(Login.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().length() == 0) {
            Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private void saveToDB(String email, boolean isGoogleAuth, String token) {
        ModelUser modelUser = new ModelUser();
        modelUser.setEmail(email);
        if (isGoogleAuth)
            modelUser.setGoogleIdToken(token);
    }


    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            moveToHome();
            checkEmptyFields();
            if (!HelperConstants.isEmailValid(email.getText().toString())) {
                Toast.makeText(Login.this, "Please use email id provided by college", Toast.LENGTH_SHORT).show();
            } else {

                if (!new CheckInternetConnection(Login.this).isConnectedToInternet()) {
                    Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
                } else {
                    callApi();
                }

            }

        } else if (v == signUpButtonStudent) {
            moveToSignUpScreen(StudentSignUp.class);
        } else if (v == signUpButtonFaculty) {
            moveToSignUpScreen(StaffSignUp.class);
        } else if (v == googleSignInButton) {
            if (!new CheckInternetConnection(Login.this).isConnectedToInternet()) {
                Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
            } else {
                callGoogleAuth();
            }

        }
    }

    private void callGoogleAuth() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            assert acct != null;
            String name = acct.getDisplayName();
            String email = acct.getEmail();
            String googleIdToken = acct.getIdToken();

            saveToDB(email, true, googleIdToken);
            moveToHome();

        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }


    }

    private void callApi() {

        final RequestParams params = new RequestParams();
        params.put("email", email.getText().toString());
        params.put("password", password.getText().toString());

        dialog = new ProgressDialog(Login.this);
        dialog.setCancelable(false);
        dialog.setTitle("Logging in....");
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                new RestManager().getInstance().login(Login.this, params, handler);
                Looper.loop();

            }
        });

    }

    private void moveToSignUpScreen(Class signUp) {
        Intent intent = new Intent(Login.this, signUp);
        startActivity(intent);
        finish();
    }


    private void moveToHome() {
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
        finish();
    }

    private void hideDialog(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            Log.i("HeyThere ", "hide dialog is running !!!");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
