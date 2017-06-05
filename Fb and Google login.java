package com.example.antop.loginproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antop.loginproject.HomeActivity;
import com.example.antop.loginproject.R;
import com.example.antop.loginproject.SessionManager;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private EditText etEmail , etPassword;
    private String strEmail , strPassword;
    private TextView btnLogin ;
    private TextView btnSignUp ;

    private ProgressDialog pd;
    private CallbackManager mCallbackManager;
    private String email;
    String profilePicUrl = null;
    SessionManager sessionManager;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private TextView btnSingoogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
              //  Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }

        sessionManager = new SessionManager();

        if(sessionManager.getEmail(LoginActivity.this).length()>0){
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        } else {
            etEmail = (EditText) findViewById(R.id.etEmail);
            etPassword = (EditText) findViewById(R.id.etPassword);
            btnLogin = (TextView) findViewById(R.id.btnLogin);
            btnSignUp = (TextView) findViewById(R.id.btnSignup);
            btnSingoogle = (TextView) findViewById(R.id.btngoogle);

            initFacebook();

            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                    startActivity(i);
                    finish();
                }


            });

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    strEmail = etEmail.getText().toString();
                    strPassword = etPassword.getText().toString();


                    if (checkValidation(strEmail, strPassword)) {

                        String email = sessionManager.getEmail(LoginActivity.this);
                        String password = sessionManager.getPassword(LoginActivity.this);

                        if ((strEmail.equals(email)) && (strPassword.equals(password) )) {

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Alertutility.SHOWTOAST(LoginActivity.this, "Login Not match");
                        }
                    }
                }
            });
        }
         btnSingoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    /*    // Customizing G+ button
        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setScopes(gso.getScopeArray());

    */}


    private boolean checkValidation(String strEmail, String strPassword) {
        if (strEmail.length() == 0) {
            Alertutility.SHOWTOAST(LoginActivity.this, "Please Enter Email Address");
            etEmail.requestFocus();
            return false;
        }  else if (strPassword.length() == 0) {
            Alertutility.SHOWTOAST(LoginActivity.this, "Please Enter Password");
            etPassword.requestFocus();
            return false;
        } else if (strPassword.length() < 6) {
            Alertutility.SHOWTOAST(LoginActivity.this, "Password should be of minimum 6 character");
            etPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }


    private void initFacebook() {

        FacebookSdk.sdkInitialize(getApplicationContext());

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {

                    @Override
                    public void onSuccess(com.facebook.login.LoginResult loginResult) {
                        loginFBCall();
                    }

                    @Override
                    public void onCancel() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setCancelable(false);
                        builder.setMessage("You have cancelled Facebook Dialog.")
                                .setPositiveButton(getString(R.string.ok), null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setCancelable(false);
                        builder.setMessage(exception.getMessage())
                                .setPositiveButton(getString(R.string.ok), null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        exception.printStackTrace();
                    }
                });

    }

    private void loginFBCall() {
        pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage(getString(R.string.progress_login));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();

        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
                        if (isFinishing())
                            return;
                        if (pd != null && pd.isShowing()) {
                            pd.dismiss();
                        }
                        if (response != null && response.getError() != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage(response.getError().getErrorMessage() != null ? response.getError().getErrorMessage() : "Could not get user data").setPositiveButton(getString(R.string.ok), null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                        String id = object.optString("id", "0");
                        String gender = object.optString("gender", "Unknown");
                        String email = null, strProfile = null;
                        String first_name = object.optString("first_name", "Unknown");
                        String last_name = object.optString("last_name", "Unknown");
                        try {
                            if (object.has("email")) {
                                email = object.getString("email");
                            }
                            if (object.has("picture")) {
                                strProfile = object.getJSONObject("picture").getJSONObject("data").getString("url");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        StringBuilder sb = new StringBuilder("");

                        if (id != null) {
                            sb.append("Id: " + id);
                        }
                        if (first_name != null) {
                            if (sb.length() > 0)
                                sb.append("\n");
                            sb.append("first_name: " + first_name);
                        }
                        if (last_name != null) {
                            if (sb.length() > 0)
                                sb.append("\n");
                            sb.append("last_name: " + last_name);
                        }
                        if (strProfile != null) {

                        }


                        sessionManager.setName_(LoginActivity.this , first_name);
                        sessionManager.setEmail(LoginActivity.this, email);
                        sessionManager.setID_(LoginActivity.this, id);
                        sessionManager.setProfile_(LoginActivity.this, strProfile);

                        Intent i = new Intent(LoginActivity.this , HomeActivity.class);
                        startActivity(i);
                        finish();

                    }
                }
        );

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,friends,first_name,last_name,gender,updated_time,link,email,cover,picture.width(500)");
        request.setParameters(parameters);
        request.executeAsync();
    }


    public void onFacebookLoginClick(View view) {
        if (AccessToken.getCurrentAccessToken() != null && AccessToken.getCurrentAccessToken().getPermissions().contains("public_profile") && AccessToken.getCurrentAccessToken().getPermissions().contains("email")) {
            loginFBCall();
        } else {
            LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this , requestCode , resultCode , data);
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            /*Log.e(TAG, "display name: " + acct.getDisplayName());*/

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();
            String id = acct.getId();


            sessionManager.setName_(LoginActivity.this , personName);
            sessionManager.setEmail(LoginActivity.this, email);
            sessionManager.setID_(LoginActivity.this, id);
            sessionManager.setProfile_(LoginActivity.this, personPhotoUrl);

            Intent i = new Intent(LoginActivity.this , HomeActivity.class);
            startActivity(i);
            finish();

        }
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading....");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
    public void login(View view){

        LISessionManager.getInstance(getApplicationContext())
                .init(this, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {

                        pd = new ProgressDialog(LoginActivity.this);
                        pd.setMessage("Loading....");
                        pd.setCancelable(false);
                        pd.setCanceledOnTouchOutside(false);
                        pd.show();

                        String host = "api.linkedin.com";
                        String url = "https://" + host + "/v1/people/~:" + "(email-address,formatted-name,phone-numbers,id,picture-urls::(original))";

                        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
                        apiHelper.getRequest(LoginActivity.this, url, new ApiListener() {
                            @Override
                            public void onApiSuccess(ApiResponse result) {
                                try {
                                    pd.dismiss();

                                    Log.e("Response @ LinkedIn: ", String.valueOf(result.getResponseDataAsJson()));
                                    String strName, strEmail, strProfile, id;
                                    strName = result.getResponseDataAsJson().optString("formattedName");
                                    strEmail = result.getResponseDataAsJson().optString("emailAddress");
                                    strProfile = result.getResponseDataAsJson().optString("pictureUrls");
                                    id = result.getResponseDataAsJson().optString("id");

                                    Log.e("Info: ", "Name: " + strName + " Email: " + strEmail + " ProfileModel: " + strProfile + " Id: " + id);

                                    sessionManager.setName_(LoginActivity.this , strName);
                                    sessionManager.setEmail(LoginActivity.this, strEmail);
                                    sessionManager.setID_(LoginActivity.this, id);
                                    sessionManager.setProfile_(LoginActivity.this, strProfile);

                                    Intent signInIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(signInIntent);
                                    finish();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onApiError(LIApiError error) {
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_LONG).show();
                                // AlertDialogUtility.SHOW_TOAST(LoginActivity.this, "Login Failed");
                            }
                        });
                    }

                    @Override
                    public void onAuthError(LIAuthError error) {
                        Toast.makeText(LoginActivity.this, "Please Try Again", Toast.LENGTH_LONG).show();
                    }
                }, true);

    }

    // set the permission to retrieve basic information of User's linkedIn account
    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS);
    }

}