package com.example.antop.icanvolleydemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private Button btnDate, btnSave;
    private EditText etRegName, etRegLastName, etRegEmail, etRegPass, etRegNum;
    private TextView txtOutputdate;
    private int year;
    private int month;
    private int day;
    private SessionManager sessionManager;
    private Date date;
    static final int DATE_PICKER_ID = 1111;
    private String deviceId;
    private String regEmail;

    //private UserRegister userBasicDetail;
    //private RegistrationDetail registrationDetail = new RegistrationDetail();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etRegName = (EditText) findViewById(R.id.etRegName);
        etRegLastName = (EditText) findViewById(R.id.etRegLastName);
        etRegEmail = (EditText) findViewById(R.id.etRegEmail);
        etRegPass = (EditText) findViewById(R.id.etRegPass);
        etRegNum = (EditText) findViewById(R.id.etRegNum);
        btnDate = (Button) findViewById(R.id.btnDate);
        btnSave = (Button) findViewById(R.id.btnSave);
        txtOutputdate = (TextView) findViewById(R.id.txtOutputdate);
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        deviceId = deviceUuid.toString();
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        txtOutputdate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String strName = etRegName.getText().toString().trim();
                    final String strMobile = etRegNum.getText().toString().trim();
                    final String strEmail = etRegEmail.getText().toString().trim();
                    final String strPassword = etRegPass.getText().toString().trim();
                    final String strLastName = etRegLastName.getText().toString();
                    final Map<String, String> params_catcher_reg = new HashMap<>();
                    params_catcher_reg.put("nCollege", String.valueOf(1));
                    //params_catcher_reg.put("cFirstNa", strName);
                    //params_catcher_reg.put("cLastNa", strLastName);
                    params_catcher_reg.put("cUserEm", strEmail);
                    params_catcher_reg.put("cPasswo", strPassword);
                    //params_catcher_reg.put("cUUI", deviceId);
                    //params_catcher_reg.put("cMileNo", strMobile);
                    params_catcher_reg.put("cReType", "mentor");
                    //params_catcher_reg.put("isdroid", String.valueOf(true));
                    params_catcher_reg.put("dtB", String.valueOf(date));
                    //JSONObject para=new JSONObject(params_catcher_reg);
                    JSONObject para=new JSONObject();
                    para.put("nCollegeId", "1");
                    para.put("cUserEmail", strEmail);
                    para.put("cPassword", strPassword);
                    para.put("dtDOB", String.valueOf(date));
                    para.put("cRoleType", "mentor");
                    Log.e("Register Request: ", "" + para);
                    String URL = "http://52.59.5.17/mentorme/index.php/users/addusers";
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    sessionManager=new SessionManager();
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, URL, para,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.e("ResponseJson:", "" + response);
                                    try {
                                        JSONObject jsonObject=new JSONObject(response.toString());
                                        sessionManager.setKEY_ID(MainActivity.this,jsonObject.getString("UserId"));
                                        Log.e("UserId :",jsonObject.getString("UserId"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(jsonObjectRequest);
                    /*JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.e("ResponseJson:", "" + response);
                                    try {
                                        JSONObject jsonObject = new JSONObject(response.toString());
                                        regEmail = jsonObject.getString("UserId");
                                        Log.e("regEmail:", regEmail);
                                        if (response.getString("success").equals(1)) {
                                            Toast.makeText(MainActivity.this, response.optString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Toast.makeText(MainActivity.this, response.optString("message"), Toast.LENGTH_LONG).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(jsonObjectRequest);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            date = new Date(year, month, day);
            // Show selected date
            txtOutputdate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };
}