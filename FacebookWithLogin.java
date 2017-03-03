package com.example.antop.practisesocialwithlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog pd;
    private CallbackManager mCallbackManager;
    private String email;
    String profilePicUrl = null;
    SessionManager sessionManager;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    private ProgressDialog mProgressDialog;
    private TextView btnSingoogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                  Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        initFacebook();
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
                        // App code
//                        progressBar.setVisibility(View.GONE);

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(false);
                        builder.setMessage("You have cancelled Facebook Dialog.")
                                .setPositiveButton(getString(R.string.ok), null);
                        // Create the AlertDialog object and return it
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code

//                        progressBar.setVisibility(View.GONE);
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(false);
                        builder.setMessage(exception.getMessage())
                                .setPositiveButton(getString(R.string.ok), null);
                        // Create the AlertDialog object and return it
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        exception.printStackTrace();
                    }
                });
    }
    private void loginFBCall() {


        pd = new ProgressDialog(MainActivity.this);
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


//                        Log.v("loginFBCall response", response.toString());

                        //show Error
                        if (response != null && response.getError() != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage(response.getError().getErrorMessage() != null ? response.getError().getErrorMessage() : "Could not get user data").setPositiveButton(getString(R.string.ok), null);
                            // Create the AlertDialog object and return it
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }


                        //get user detail


                        String id = object.optString("id", "0");
                        String gender = object.optString("gender", "Unknown");
                        String email = null, strProfile = null;
                        String first_name = object.optString("first_name", "Unknown");
                        String last_name = object.optString("last_name", "Unknown");



                        try
                            {
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


                            sessionManager.setName_(MainActivity.this , first_name);
                            sessionManager.setEmail(MainActivity.this, email);
                            sessionManager.setID_(MainActivity.this, id);
                            sessionManager.setProfile_(MainActivity.this, strProfile);

                        Log.e("ID Facebook :",sessionManager.getID(MainActivity.this));
                        }
                    }



        );

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,friends,first_name,last_name,gender,updated_time,link,email,cover,picture.width(500)");
        request.setParameters(parameters);
        request.executeAsync();
    }


    public void onFacebookLoginClick(View view) {

        //On FB button Click
        if (AccessToken.getCurrentAccessToken() != null && AccessToken.getCurrentAccessToken().getPermissions().contains("public_profile") && AccessToken.getCurrentAccessToken().getPermissions().contains("email")) {
            loginFBCall();
        } else {
            LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("email", "public_profile"));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }
}
